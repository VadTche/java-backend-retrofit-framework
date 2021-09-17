package ru.retrofit.enums;

import lombok.Getter;

public enum Price {

    VALID_INT(999),
    VALID_MAX_INT(2147483647),
    VALID_DOUBLE(999/5),
    INVALID_MINUS(-999),
    INVALID_ZERO(0);

    @Getter
    private Integer price;


    Price(Integer price) {
        this.price = price;
    }
}
