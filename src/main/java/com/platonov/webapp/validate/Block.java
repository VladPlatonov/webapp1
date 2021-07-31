package com.platonov.webapp.validate;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BlockValidate.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Block {
    String message() default "{Block}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}