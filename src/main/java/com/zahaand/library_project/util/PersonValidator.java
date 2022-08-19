package com.zahaand.library_project.util;

import com.zahaand.library_project.dao.PersonDAO;
import com.zahaand.library_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDAO.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("full_name", "", "человек с таким именем уже существует");
        }
    }
}
