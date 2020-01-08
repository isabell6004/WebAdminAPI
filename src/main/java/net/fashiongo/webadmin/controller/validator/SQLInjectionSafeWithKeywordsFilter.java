package net.fashiongo.webadmin.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {SQLInjectionSafeWithKeywordsFilterValidator.class}
)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInjectionSafeWithKeywordsFilter {
    String message() default "{SQLInjectionSafe With KeywordsFilter}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
