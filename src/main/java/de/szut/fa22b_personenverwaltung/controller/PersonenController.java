package de.szut.fa22b_personenverwaltung.controller;


import de.szut.fa22b_personenverwaltung.model.Person;
import de.szut.fa22b_personenverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@RestController
public class PersonenController {

    private PersonRepository repository;

    public PersonenController(PersonRepository repository) {
        this.repository=repository;


        Person p=new Person();
        p.setFirstname("Bernd");
        p.setSurname("Heidemann");
        this.repository.save(p);

        Person p2=new Person();
        p2.setSurname("Foo");
        p2.setFirstname("Bernd");
        this.repository.save(p2);

        Person p3=new Person();
        p3.setSurname("Bar");
        p3.setFirstname("Foo");
        this.repository.save(p3);


        this.repository.getPersonByFirstnameOrderBySurname("Bernd").forEach(System.out::println);
    }

    @GetMapping(value = "/foo")
    public String foo() {
        return "foobar";
    }


    @RequestMapping(
            value= "/person",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Person createPerson(@RequestBody Person newPerson) {
        Person savedPerson=this.repository.save(newPerson);
        return savedPerson;
    }

    @RequestMapping(
            value="/person/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Person getById(@PathVariable long id) {
//        Optional<Person> p=this.repository.findById(id);
//        if(p.isPresent()) {
//            return p.get();
//        }
//        return null;
        return this.repository.findById(id).orElseThrow();
    }

    @RequestMapping(
            value="/person",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Collection<Person> getAllPersons() {
        return this.repository.findAll();
    }

    @RequestMapping(
            value="/person/{id}",
            method=RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Person update(@PathVariable Long id, @RequestBody Person toUpdate) {
        Person p = this.repository.findById(id).orElseThrow();
        p.setFirstname(toUpdate.getFirstname());
        p.setSurname(toUpdate.getSurname());
        Person saved=this.repository.save(p);
        return saved;
    }

    @RequestMapping(
            value="/person/{id}",
            method=RequestMethod.DELETE
    )
    public void delete(@PathVariable Long id) {
        Person toDelete=this.repository.findById(id).orElseThrow();
        this.repository.delete(toDelete);
    }





}
