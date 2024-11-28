package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(long id){
        return findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }
}
