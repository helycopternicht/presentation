package io.zensoft.auditing.envers.controller

import io.zensoft.auditing.envers.repository.Repository
import io.zensoft.auditing.envers.entity.Person
import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class EntityController(private val repository: Repository) {

    @GetMapping
    fun getAll(): List<Person> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): Person {
        return repository.findById(id).orElseThrow { NotFoundException("Person with id: $id not found") }
    }

    @PostMapping
    fun add(@RequestBody person: Person): Person {
        return repository.save(person)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: Person): Person {
        val person = repository.findById(id).orElseThrow { NotFoundException("Person with id: $id not found") }
        person.apply {
            this.name = dto.name
            this.age = dto.age
        }
        return repository.save(person)
    }
}