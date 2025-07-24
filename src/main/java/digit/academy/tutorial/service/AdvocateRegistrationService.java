package digit.academy.tutorial.service;

import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import digit.academy.tutorial.entities.enrichers.RegistrationRequestEnricher;
import digit.academy.tutorial.entities.validators.RegistrationRequestValidator;
import digit.academy.tutorial.kafka.Producer;
import digit.academy.tutorial.repository.RegistrationApplicationRepo;
import digit.academy.tutorial.util.WorkflowUtil;
import digit.academy.tutorial.web.models.AdvocateRequest;
import digit.academy.tutorial.web.models.AdvocateSearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdvocateRegistrationService {

    @Autowired
    private Producer producer;

    @Autowired
    private RegistrationRequestValidator validator;

    @Autowired
    private RegistrationRequestEnricher enricher;

    @Autowired
    private RegistrationApplicationRepo applicationRepository;

    @Autowired
    private WorkflowUtil workflowService;

    @Value("${ar.kafka.save.registration.topic}")
    private String saveApplicationTopic;

    @Value("${ar.kafka.update.registration.topic}")
    private String updateApplicationTopic;

    public AdvocateRegistrationApplication processRegistrationApplication(AdvocateRequest request) {
        validator.performDuplicateValidation(request.getApplication());

        enricher.enrichNewApplication(request);

        workflowService.updateWorkflowStatus(request.getRequestInfo(), request.getApplication().getTenantId(),
                request.getApplication().getApplicationId(), "ADV", null, "NA");

        producer.push(saveApplicationTopic, request);

        return request.getApplication();
    }

    public AdvocateRegistrationApplication searchRegistrationApplication(RequestInfo requestInfo, AdvocateSearchCriteria criteria) {
        return applicationRepository.getRegistrationApplication(criteria);
    }

    public AdvocateRegistrationApplication updateRegistrationApplication(AdvocateRequest request) {
        AdvocateRegistrationApplication existingApplication = validator.applicationExists(request.getApplication());
        existingApplication.setWorkflow(request.getApplication().getWorkflow());

        enricher.enrichOnApplicationUpdate(request);

        workflowService.updateWorkflowStatus(request.getRequestInfo(), request.getApplication().getTenantId(),
                request.getApplication().getApplicationId(), "ADV", null, "NA");

        producer.push(updateApplicationTopic, request);
        return request.getApplication();
    }
}
