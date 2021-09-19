package ru.retrofit.enums;

import lombok.Getter;

public enum Price {

    VALID_INT("Latin",999, "Electronic"),
    VALID_MAX_INT("Latin",2147483647, "Electronic"),
    VALID_DOUBLE("Latin",999/5, "Electronic"),
    INVALID_MINUS("Latin",-999, "Electronic"),
    INVALID_ZERO("Latin",0, "Electronic");

    @Getter
    private String title;
    private final Integer price;
    private final String categoryTitle;

    Price(String title, Integer price, String categoryTitle) {
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public String getTitle() {
        return title;
    }
    public Integer getPrice() {
        return price;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }
}
