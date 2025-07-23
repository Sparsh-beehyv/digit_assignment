package digit.academy.tutorial.repository;

import digit.academy.tutorial.querybuilder.RegistrationApplicationQueryBuilder;
import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import digit.academy.tutorial.web.models.AdvocateSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrationApplicationRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApplicationRowMapper rowMapper;

    public AdvocateRegistrationApplication getRegistrationApplication(AdvocateSearchCriteria criteria) {
        List<Object> preparedStmt = new ArrayList<>();
        String query = RegistrationApplicationQueryBuilder.getApplicationSearchQuery(criteria, preparedStmt);
        return jdbcTemplate.query(query, rowMapper);
    }
}
