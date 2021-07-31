package com.platonov.webapp.validate;

import com.platonov.webapp.domain.Status;
import com.platonov.webapp.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlockValidate implements ConstraintValidator<Block, String> {

    @Override
    public void initialize(Block block) {

    }
    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
        boolean result=status.contains("Unblock");
        return result;
    }
}
