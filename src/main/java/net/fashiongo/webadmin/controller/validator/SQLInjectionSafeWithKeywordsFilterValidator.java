package net.fashiongo.webadmin.controller.validator;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SQLInjectionSafeWithKeywordsFilterValidator implements ConstraintValidator<SQLInjectionSafeWithKeywordsFilter, String> {

    @Override
    public void initialize(SQLInjectionSafeWithKeywordsFilter sqlInjectionSafeWithKeywordsFilter) {
        // Do nothing
    }

    @Override
    public boolean isValid(String dataString, ConstraintValidatorContext context) {
        if (SqlSafeUtil.isSqlInjectionSafe(dataString)) {
            return SqlKeywordsFilterUtil.isSqlKeywordsFilterSafe(dataString);
        }
        return false;
    }

}
