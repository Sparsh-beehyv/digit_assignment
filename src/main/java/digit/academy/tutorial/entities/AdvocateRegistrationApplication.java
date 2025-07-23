package digit.academy.tutorial.entities;

import digit.academy.tutorial.config.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.egov.common.contract.models.AuditDetails;
import org.egov.common.contract.models.Workflow;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvocateRegistrationApplication {

    private UUID id;
    private String applicationId;

    @NotNull
    private String tenantId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String state;
    private String district;
    private String city;
    private String phone;
    private UserType userType;
    private AuditDetails auditDetails;
    private AdvocateDetails advocateDetails;

    private Workflow workflow;
}
