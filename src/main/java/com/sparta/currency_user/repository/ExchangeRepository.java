package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<UserCurrency, Long> {
    default UserCurrency findByIdOrThrow(long id) {
        return findById(id).orElseThrow();
    }

    List<UserCurrency> findAllByUserId(long userId);
}
