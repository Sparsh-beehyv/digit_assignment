package digit.academy.tutorial.web.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import digit.academy.tutorial.service.AdvocateRegistrationService;
import digit.academy.tutorial.util.ResponseInfoFactory;
import digit.academy.tutorial.web.models.AdvocateRequest;
import digit.academy.tutorial.web.models.AdvocateResponse;
import digit.academy.tutorial.web.models.AdvocateSearchRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.egov.common.contract.response.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2025-07-17T16:18:28.406185974+05:30[Asia/Kolkata]")
@Controller
@RequestMapping("/advocate/v1")
@RequiredArgsConstructor
public class AdvocateApiController{

    private final ObjectMapper objectMapper;

    private final AdvocateRegistrationService registrationService;

    private final ResponseInfoFactory responseInfoFactory;

    @RequestMapping(value="/_create", method = RequestMethod.POST)
    public ResponseEntity<AdvocateResponse> advocateCreatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Details for the user registration + RequestInfo meta data.", required=true, schema=@Schema()) @Valid @RequestBody AdvocateRequest body) {
        AdvocateRegistrationApplication application = registrationService.processRegistrationApplication(body);
        ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(body.getRequestInfo(), true);
        AdvocateResponse response = AdvocateResponse.builder().responseInfo(responseInfo).advocates(application).build();

        return new ResponseEntity<AdvocateResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/_search", method = RequestMethod.POST)
    public ResponseEntity<AdvocateResponse> advocateSearchPost(@Parameter(in = ParameterIn.DEFAULT, description = "Search criteria + RequestInfo meta data.", required=true, schema=@Schema()) @Valid @RequestBody AdvocateSearchRequest body) {
        AdvocateRegistrationApplication application = registrationService.searchRegistrationApplication(body.getRequestInfo(), body.getCriteria());
        ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(body.getRequestInfo(), true);
        AdvocateResponse response = AdvocateResponse.builder().responseInfo(responseInfo).advocates(application).build();

        return new ResponseEntity<AdvocateResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/_update", method = RequestMethod.POST)
    public ResponseEntity<AdvocateResponse> advocateUpdatePost(@Parameter(in = ParameterIn.DEFAULT, description = "Details of the registered advocate + RequestInfo meta data.", required=true, schema=@Schema()) @Valid @RequestBody AdvocateRequest body) {
        AdvocateRegistrationApplication application = registrationService.updateRegistrationApplication(body);
        ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(body.getRequestInfo(), true);
        AdvocateResponse response = AdvocateResponse.builder().responseInfo(responseInfo).advocates(application).build();

        return new ResponseEntity<AdvocateResponse>(response, HttpStatus.OK);
    }
}
