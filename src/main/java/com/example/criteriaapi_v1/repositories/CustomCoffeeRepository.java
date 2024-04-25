package com.example.criteriaapi_v1.repositories;

import com.example.criteriaapi_v1.entities.Coffee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CustomCoffeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Coffee getCoffeeById(long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Coffee> criteriaQuery = criteriaBuilder.createQuery(Coffee.class);
        Root<Coffee> root = criteriaQuery.from(Coffee.class);

        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);

        criteriaQuery.select(root).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Collection<Coffee> getCoffeeByComplexConditions(String name, Coffee.CoffeeType type) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Coffee> criteriaQuery = criteriaBuilder.createQuery(Coffee.class);
        Root<Coffee> root = criteriaQuery.from(Coffee.class);

        Predicate hasNameLike = criteriaBuilder.like(root.get("name"), name);
        Predicate hasTypeLike = criteriaBuilder.equal(root.get("type"), type);

        Predicate predicate = criteriaBuilder.and(hasNameLike, hasTypeLike);

        criteriaQuery.select(root).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
