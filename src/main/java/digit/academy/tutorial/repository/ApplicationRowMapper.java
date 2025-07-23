package digit.academy.tutorial.repository;

import digit.academy.tutorial.entities.AdvocateRegistrationApplication;
import org.egov.common.contract.models.AuditDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ApplicationRowMapper implements ResultSetExtractor<AdvocateRegistrationApplication> {

    @Override
    public AdvocateRegistrationApplication extractData(ResultSet rs) throws SQLException, DataAccessException {
        AdvocateRegistrationApplication application = null;
        if(rs.next()) {
            Long lastModifiedTime = rs.getLong("lastModifiedTime");
            if(rs.wasNull()) {
                lastModifiedTime = null;
            }

            AuditDetails auditDetails = AuditDetails.builder()
                    .createdBy(rs.getString("createdBy")).lastModifiedBy(rs.getString("lastModifiedBy"))
                    .createdTime(rs.getLong("createdTime")).lastModifiedTime(lastModifiedTime)
                    .build();

            return AdvocateRegistrationApplication.builder()
                    .applicationId(rs.getString("applicationId"))
                    .id(UUID.fromString(rs.getString("id")))
                    .firstName(rs.getString("firstName"))
                    .middleName(rs.getString("middleName"))
                    .lastName(rs.getString("lastName"))
                    .phone(rs.getString("phone"))
                    .auditDetails(auditDetails)
                    .state(rs.getString("state"))
                    .district(rs.getString("district"))
                    .city(rs.getString("city"))
                    .tenantId(rs.getString("tenantId"))
                    .build();
        }
        return application;
    }
}
