package digit.academy.tutorial.entities.validators;

import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import digit.academy.tutorial.repository.RegistrationApplicationRepo;
import digit.academy.tutorial.web.models.AdvocateSearchCriteria;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static digit.academy.tutorial.config.ServiceConstants.DUPLICATE_ERROR;
import static digit.academy.tutorial.config.ServiceConstants.DUPLICATE_ERROR_MSG;
import static digit.academy.tutorial.config.UserType.ADVOCATE;

@Component
public class RegistrationRequestValidator {

    @Autowired
    private RegistrationApplicationRepo applicationRepository;

    public void duplicateValidation(AdvocateRegistrationApplication application) {
        boolean duplicateExists = false;
        AdvocateRegistrationApplication existingByPhone = searchForPhone(application);

        duplicateExists = existingByPhone != null || (searchForBarId(application) != null);
        if(duplicateExists) {
            throw new CustomException(DUPLICATE_ERROR, DUPLICATE_ERROR_MSG);
        }
    }

    private AdvocateRegistrationApplication searchForPhone(AdvocateRegistrationApplication application) {
        return applicationRepository.getRegistrationApplication(AdvocateSearchCriteria.builder()
                .phone(application.getPhone()).build());
    }

    private AdvocateRegistrationApplication searchForBarId(AdvocateRegistrationApplication application) {
        if(!ADVOCATE.equals(application.getUserType())) {
            return null;
        }
        return applicationRepository.getRegistrationApplication(AdvocateSearchCriteria.builder()
                .barRegistrationNumber(application.getAdvocateDetails().getBarRegistrationNum()).build());
    }

    public AdvocateRegistrationApplication applicationExists(AdvocateRegistrationApplication application) {
        return applicationRepository.getRegistrationApplication(AdvocateSearchCriteria.builder()
                .applicationNumber(application.getApplicationId()).build());
    }
}
