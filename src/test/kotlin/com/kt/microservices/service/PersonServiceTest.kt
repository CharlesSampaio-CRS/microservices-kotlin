package com.kt.microservices.service

import com.kt.microservices.mapper.MockPerson
import com.kt.microservices.repository.PersonRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class PersonServiceTest {

    private lateinit var input: MockPerson

    @InjectMocks
    private lateinit var service: PersonService

    @Mock
    private lateinit var repository: PersonRepository


    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        input = MockPerson()
    }

    @Test
    fun create(){
        val entity = input.mockEntity(1)
        val personPersisted = entity.copy()
        personPersisted.id = 1L
        val personVO = input.mockVO(1)
        `when`(repository.save(entity)).thenReturn(personPersisted)
        var res = service.create(personVO)
        assertNotNull(res)
        assertNotNull(res.links)
        assertEquals(res.lastName, personVO.lastName)
        assertTrue(res.links.toString().contains("person/v1/"))
    }

    @Test
    fun findById(){
        val person = input.mockEntity(1)
        person.id = 1L
        `when`(repository.findById(person.id)).thenReturn(Optional.of(person))
        var res = service.findById(person.id)
        assertNotNull(res)
        assertNotNull(res.key)
        assertNotNull(res.links)
        assertTrue(res.links.toString().contains("person/v1/"))
    }

    @Test
    fun update(){
        val entity = input.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = input.mockVO(1)
        val result = service.update(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("person/v1/"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun delete(){
        val entity = input.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        service.delete(1)
    }

    @Test
    fun findAll(){
        val list = input.mockEntityList()
        `when`(repository.findAll()).thenReturn(list)

        val persons = service.findAll()

        assertNotNull(persons)
    }

}