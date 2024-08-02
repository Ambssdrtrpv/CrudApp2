package ru.elchueva.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.elchueva.springcourse.dao.PersonDAO;
import ru.elchueva.springcourse.models.Person;
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    @Override
    public boolean supports(Class<?> aClass) { //в этом методе даем понять какому классу относится валидатор(к каким объектам применяем)
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        //посмотреть есть ли человек с таким же email'ом в БД, поэтому обращаемся к БД
        if(personDAO.show(person.getEmail()).isPresent()){
            errors.rejectValue("email", null, "This email is already taken"); // (1) поле на котором ошибка (2) код ошибки пока не указываем (3) сообщение ошибки
        }
    }
}
