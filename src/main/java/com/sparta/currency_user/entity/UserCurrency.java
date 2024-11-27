package com.sparta.currency_user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class UserCurrency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="currency_id")
    private Currency currency;

    private int amountInKwr;
    private double amountAfterExchange;
    private String status;

    public UserCurrency(Long id, User user, Currency currency, int amountInKwr, double amountAfterExchange, String status) {
        this.id = id;
        this.user = user;
        this.currency = currency;
        this.amountInKwr = amountInKwr;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public UserCurrency() {}
}
