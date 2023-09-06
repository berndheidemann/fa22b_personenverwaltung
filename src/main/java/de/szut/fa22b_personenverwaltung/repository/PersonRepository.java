package de.szut.fa22b_personenverwaltung.repository;

import de.szut.fa22b_personenverwaltung.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public Collection<Person> getPersonByFirstnameOrderBySurname(String firstname);
}
