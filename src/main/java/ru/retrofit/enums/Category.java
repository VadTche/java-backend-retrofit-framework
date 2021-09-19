package ru.retrofit.enums;

import lombok.Getter;

public enum Category {

    FOOD(1, "Food"),
    ELECTRONIC(2,"Electronic"),
    FURNITURE(3, "Furniture"),
    UN_EXIST_CATEGORY(12, "Woods");

    @Getter
    private final Integer id;
    @Getter
    private final String name;


    Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
