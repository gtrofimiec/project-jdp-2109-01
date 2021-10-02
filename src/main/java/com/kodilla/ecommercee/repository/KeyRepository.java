package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends CrudRepository<Key,Long> {
    Key findByUserId(Long userId);
}
