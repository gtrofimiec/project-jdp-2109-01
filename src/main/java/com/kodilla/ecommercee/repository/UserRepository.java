package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll ();

    @Override
    User save(User user);

    @Override
    void deleteById(Long userId);

    User findUserByKeyAccessKey(String accessKey);

    User findUserById(Long userId);
}