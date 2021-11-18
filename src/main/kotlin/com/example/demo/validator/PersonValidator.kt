package com.example.demo.validator

import com.example.demo.domain.Person
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class PersonValidator : Validator {

    /**
     * 오직 Person 클래스만 받아들이겠다는 뜻
     */
    override fun supports(clazz: Class<*>): Boolean {
        return Person::class.java == clazz;
    }

    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "이름이 비어있습니다.");
        val person = target as Person;

        if (person.age < 0 ) {
            errors.rejectValue("age", "잘못된 나이 값입니다.");
        }
        if (person.age > 110) {
            errors.rejectValue("age", "나이가 너무 많아서 가입이 불가능합니다.");
        }
    }
}