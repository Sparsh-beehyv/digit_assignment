package digit.academy.tutorial.entities.enrichers;

import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import digit.academy.tutorial.util.IdgenUtil;
import digit.academy.tutorial.web.models.AdvocateRequest;
import org.egov.common.contract.models.AuditDetails;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static digit.academy.tutorial.config.ServiceConstants.REGISTRATION_SEQ_FORMAT;
import static digit.academy.tutorial.config.ServiceConstants.REGISTRATION_SEQ_ID;
import static digit.academy.tutorial.config.UserType.ADVOCATE;
import static digit.academy.tutorial.config.UserType.ADVOCATE_CLERK;

@Component
public class RegistrationRequestEnricher {

    @Autowired
    private IdgenUtil idgenUtil;

    public void enrichNewApplication(AdvocateRequest request) {
        enrichUniqueId(request);
        enrichAuditDetails(request);
        enrichApplicationId(request);
    }

    private void enrichApplicationId(AdvocateRequest request) {
        String generatedId = idgenUtil.getIdList(request.getRequestInfo(), request.getApplication().getTenantId(),
                                REGISTRATION_SEQ_ID, REGISTRATION_SEQ_FORMAT, 1).get(0);

        String applicationId = getApplicationIdPrefix(request.getApplication()) + "_" + generatedId;
        request.getApplication().setApplicationId(applicationId);
    }

    private String getApplicationIdPrefix(AdvocateRegistrationApplication application) {
        return ADVOCATE.equals(application.getUserType()) ? "ADVOC" :
                ADVOCATE_CLERK.equals(application.getUserType()) ? "ADVOC_CLERK" : "UNKNOWN";
    }

    private void enrichUniqueId(AdvocateRequest request) {
        AdvocateRegistrationApplication application = request.getApplication();
        application.setId(UUID.randomUUID());

        if(request.getApplication().getAdvocateDetails() != null) {
            request.getApplication().getAdvocateDetails().setId(UUID.randomUUID());
            request.getApplication().getAdvocateDetails().setRegistrationId(
                    request.getApplication().getId().toString()
            );
        }
    }

    private void enrichAuditDetails(AdvocateRequest request) {
        RequestInfo requestInfo = request.getRequestInfo();
        AdvocateRegistrationApplication application = request.getApplication();

        AuditDetails auditDetails = AuditDetails.builder()
                .createdBy(requestInfo.getUserInfo().getUuid())
                .createdTime(System.currentTimeMillis())
                .lastModifiedTime(System.currentTimeMillis())
                .lastModifiedBy(requestInfo.getUserInfo().getUuid())
                .build();

        application.setAuditDetails(auditDetails);
    }

    public void enrichOnApplicationUpdate(AdvocateRequest request) {
        enrichAuditChanges(request);
    }

    private void enrichAuditChanges(AdvocateRequest request) {
        RequestInfo requestInfo = request.getRequestInfo();
        AdvocateRegistrationApplication application = request.getApplication();

        AuditDetails auditDetails = application.getAuditDetails();
        auditDetails.setLastModifiedBy(requestInfo.getUserInfo().getUuid());
        auditDetails.setLastModifiedTime(System.currentTimeMillis());
        application.setAuditDetails(auditDetails);
    }
}
