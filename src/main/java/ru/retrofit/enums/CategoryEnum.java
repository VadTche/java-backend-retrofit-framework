package ru.retrofit.enums;

import lombok.Getter;

public enum CategoryEnum {
    FOOD(1, "Food"),
    ELECTRONIC(2,"Electronic"),
    FURNITURE(3, "Furniture"),
    UN_EXIST_CATEGORY(12, "Woods");

    @Getter
    private final Integer id;
    @Getter
    private final String title;


    CategoryEnum(Integer id, String title) {
        this.id = getId();
        this.title = title;
    }
}
