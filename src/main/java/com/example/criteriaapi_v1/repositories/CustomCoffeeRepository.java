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
// Criteria API được sử dụng qua lớp này
@Repository
public class CustomCoffeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Coffee getCoffeeById(long id) {
        // Đầu tiên lấy đối tượng CriteriaBuilder từ EntityManager
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Tạo đối tượng CriteriaQuery để khai báo kiểu dữ liệu để truy vấn
        CriteriaQuery<Coffee> criteriaQuery = criteriaBuilder.createQuery(Coffee.class);
        // Đối tượng Root được tạo để chỉ định đối tượng cơ sở của truy vấn
        // khai báo đối tượng sẽ sử dụng trong query
        Root<Coffee> root = criteriaQuery.from(Coffee.class);

        // Xây dựng truy vấn bằng cách sử dụng các phương thức của criteriaBuilder
        // và các biểu thức như equal, like, and, or và nhiều phép toán khác
        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);

        // Câu lệnh select được dùng để lấy đối tượng đã khai báo
        // và truyền vào đối tượng root
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
