package io.zensoft.auditing.envers.repository

import io.zensoft.auditing.envers.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : JpaRepository<Person, Long>