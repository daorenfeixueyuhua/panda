package com.daoren.dbagent.validator;

import com.daoren.dbagent.annotation.SqlValidation;
import com.daoren.dbagent.model.dto.RequestParams;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 参数校验
 * @author peng_da
 * @date  2022/8/11 11:29
 */
@Slf4j
public class SqlValidator implements ConstraintValidator<SqlValidation, RequestParams> {
    @Override
    public void initialize(SqlValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RequestParams value, ConstraintValidatorContext context) {
        return true;
    }
}
