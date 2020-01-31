package net.fashiongo.webadmin.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlKeywordsFilterUtil {

    private static final String SQL_KEYWORDS
            = "^(?!.*(\\'|--|\\#|\\=|\\(|\\)|\\*\\/|\\/\\*|\\+|\\<|\\>|&&|%|\\|\\||\\bunion\\b|\\bselect\\b|\\binsert\\b|\\bfrom\\b|\\bwhere\\b|\\bupdate\\b|\\bdrop\\b|\\bif\\b|\\bjoin\\b|\\bdeclare\\b|\\band\\b|\\bor\\b|\\bcolumn_name\\b|\\btable_name\\b|\\bopenrowset\\b|\\bsubstr\\b|\\bsubstring\\b|\\bxp_\\b|\\bsysobjects\\b|\\bsyscolumns\\b)).*$";
    private static final Pattern validationPattern;

    private SqlKeywordsFilterUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isSqlKeywordsFilterSafe(String dataString) {
        if (isEmpty(dataString)) {
            return true;
        }

        return matches(validationPattern, dataString);
    }

    private static boolean matches(Pattern pattern, String dataString) {
        Matcher matcher = pattern.matcher(dataString);
        return matcher.matches();
    }

    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    static {
        validationPattern = Pattern.compile(SQL_KEYWORDS, Pattern.CASE_INSENSITIVE);
    }

}
