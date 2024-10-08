package com.kt.microservices.controller

import com.kt.microservices.model.Person
import com.kt.microservices.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping("/{id}")
    fun getPerson(@PathVariable(value = "id")id: Long): Person{
        return service.findById(id);
    }

    @PostMapping("/create")
    fun create(@RequestBody person: Person): Person{
        return service.create(person);
    }

    @PutMapping("/update")
    fun update(@RequestBody person: Person): Person{
        return service.update(person);
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable(value = "id") id: Long){
        return service.delete(id);
    }

    @RequestMapping("/all")
    fun findAll(): List<Person>{
        return service.findAll();
    }
}