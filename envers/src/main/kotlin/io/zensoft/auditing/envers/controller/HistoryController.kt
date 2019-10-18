package io.zensoft.auditing.envers.controller

import io.zensoft.auditing.envers.entity.Person
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@RestController
@RequestMapping("/persons/{id}")
class HistoryController(private val entityManager: EntityManager) {

    @GetMapping("/field-changes/{fieldName}")
    fun getChangedField(@PathVariable id: Long, @PathVariable fieldName: String): MutableList<Any?>? {
        return AuditReaderFactory.get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(Person::class.java, false, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property(fieldName).hasChanged())
                .resultList
    }

    @GetMapping("/all-changes")
    fun getAnyChange(@PathVariable id: Long): MutableList<Any?>? {
        return AuditReaderFactory.get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(Person::class.java, false, true)
                .add(AuditEntity.id().eq(id))
                .resultList
    }

    @GetMapping("/at-revision/{revision}")
    fun getStateAtGivenRevision(@PathVariable id: Long, @PathVariable revision: Int): Any? {
        return AuditReaderFactory.get(entityManager)
                .createQuery()
                .forEntitiesAtRevision(Person::class.java, revision)
                .add(AuditEntity.id().eq(id))
                .resultList
    }

//    @PutMapping("/{name}")
//    @Transactional
//    fun update(@RequestBody ids: List<Long>, @PathVariable name: String) {
//        val queryFactory = JPAQueryFactory(entityManager)
//        val lease: QLease  = QLease.lease
//        queryFactory.update(lease)
//                .where(lease.id.`in`(ids))
//                .set(lease.name, name)
//                .execute()
//
//        val threadPool: ExecutorService = Executors.newFixedThreadPool(5)
//
//        val author = "admin@mail.com"
//        leaseRepository.findAllById(ids).forEach { lease ->
//            javers.commitAsync(author, lease, threadPool)
//        }
//    }
//
//    @GetMapping("/changes/{id}")
//    fun getChanges(@PathVariable id: Long): String {
//        val jqlQuery = QueryBuilder.byInstanceId(id, Lease::class.java)
//        val changes = javers.findChanges(jqlQuery.build())
//        val prettyPrint = changes.prettyPrint()
//        return prettyPrint
//    }
//
//    @GetMapping("/shadows/{id}")
//    fun getShadows(@PathVariable id: Long): String {
//        val jqlQuery = QueryBuilder.byInstanceId(id, Lease::class.java)
//        val shadows = javers.findShadows<Lease>(jqlQuery.build())
//        return javers.jsonConverter.toJson(shadows)
//    }
}