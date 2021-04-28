package io.zipcoder.persistenceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/people")
@RestController
public class PersonController {
    PersonService personService;

    @Autowired
    public PersonController(PersonService p){this.personService=p;}


    @GetMapping
    public List<Person> getPeeps(){
        return personService.list();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable int id){
        return personService.get(id).get();
    }

    @GetMapping(path="reverselookup/{mobile}")
    public List<Person> getPersonByPhone(@PathVariable String mobile){
        return personService.phoneLookup(mobile);
    }

    @GetMapping(path="surname/{last}")
    public List<Person> getPersonByLastName(@PathVariable String last){
        return personService.lastLookup(last);
    }

    @GetMapping(path="firstname/stats")
    public Map<String, Long> firstNameStats(){
        return personService.firstNameFrequencies();
    }

    @GetMapping(path="byhomes/{id}")
    public List<Person> getPeopleByResidence(@PathVariable int id){return personService.listByHome(id);}


    @PostMapping
    public void addPerson(@RequestBody Person p){
        personService.create(p);
    }

    @DeleteMapping(path="{id}")
    public void deleteById(@PathVariable int id){
        personService.delete(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@RequestBody Person p, @PathVariable int id){
        personService.update(p,id);
    }

}