package ru.elchueva.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.elchueva.springcourse.dao.PersonDAO;
import ru.elchueva.springcourse.models.Person;

@Component
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;
    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
     public String adminPage(Model model, @ModelAttribute("person") Person person){ //возвращает страницу с выадающим списком
        model.addAttribute("people", personDAO.index());
        return "adminPage";
    }
    @PatchMapping("/add")
    public String  makeAdmin(@ModelAttribute("person") Person person){ //опять создаем пустой объект класса Person с помощью @ModelAttribute
        //но в данном случае аннотация увидит выбранном набраузере id и назначит его пустому человеку
        System.out.println(person.getId());
        //другие поля null так как с выпадающего списка приходит только id
        return "redirect:/people";
    }

}
