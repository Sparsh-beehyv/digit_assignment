package digit.academy.tutorial.web.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * AdvocateSearchCriteria
 */
@Validated
@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2025-07-17T16:18:28.406185974+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvocateSearchCriteria   {
        @JsonProperty("id")
        private String id = null;

        @JsonProperty("barRegistrationNumber")
        private String barRegistrationNumber = null;

        @JsonProperty("applicationNumber")
        private String applicationNumber = null;

        @JsonProperty("phone")
        private String phone;
}
