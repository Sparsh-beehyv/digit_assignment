package digit.academy.tutorial.querybuilder;

import digit.academy.tutorial.web.models.AdvocateSearchCriteria;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class RegistrationApplicationQueryBuilder {

    private static final String BASE_QUERY = "select ar.id, ar.applicationId, ar.firstName, ar.middleName, ar.lastName, ar.state, ar.district, ar.city, ar.phone, ar.tenantId, ar.createdTime, ar.lastModifiedTime, ar.createdBy, ar.lastModifiedBy, ad.barRegistrationNum, ad.barRegistrationDocUrl, ad.designation ";

    private static final String FROM_QUERY = " from eg_adv_registration ar inner join eg_adv_details ad on ar.id = ad.registrationId ";

    private static final String ORDER_QUERY = " order by createdTime DESC";

    public static String getApplicationSearchQuery(AdvocateSearchCriteria criteria, List<Object> preparedStmt) {
        StringBuilder query = new StringBuilder(BASE_QUERY);
        query.append(FROM_QUERY);

        if(ObjectUtils.isNotEmpty(criteria.getId())) {
            andOrWhereClause(query, preparedStmt);
            query.append(" id = ? ");
            preparedStmt.add(criteria.getId());
        }

        if(ObjectUtils.isNotEmpty(criteria.getApplicationNumber())) {
            andOrWhereClause(query, preparedStmt);
            query.append(" applicationId = ? ");
            preparedStmt.add(criteria.getApplicationNumber());
        }

        if(ObjectUtils.isNotEmpty(criteria.getBarRegistrationNumber())) {
            andOrWhereClause(query, preparedStmt);
            query.append(" barRegistrationNum = ? ");
            preparedStmt.add(criteria.getBarRegistrationNumber());
        }

        if(ObjectUtils.isNotEmpty(criteria.getPhone())) {
            andOrWhereClause(query, preparedStmt);
            query.append(" phone = ? ");
            preparedStmt.add(criteria.getPhone());
        }

        query.append(ORDER_QUERY);
        return query.toString();
    }

    private static void andOrWhereClause(StringBuilder query, List<Object> preparedStmt) {
        if(preparedStmt == null || preparedStmt.size() == 0) {
            query.append(" where ");
        } else {
            query.append(" and ");
        }
    }
}
