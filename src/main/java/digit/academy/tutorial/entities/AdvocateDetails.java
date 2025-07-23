package digit.academy.tutorial.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.egov.common.contract.models.AuditDetails;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvocateDetails {

    private UUID id;
    @NotNull
    private String registrationId;
    private String barRegistrationNum;
    private String barRegistrationDocUrl;
    private String designation;
    private AuditDetails auditDetails;
}
