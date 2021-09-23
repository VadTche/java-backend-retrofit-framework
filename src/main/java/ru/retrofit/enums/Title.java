package ru.retrofit.enums;

import lombok.Setter;

public enum Title {

    LATIN("Latin", 999, "Electronic"),
    CYRILLIC("Кириллица", 999, "Electronic"),
    CHINESE("汉字", 999, "Electronic"),
    NON_LETTERS("#$%@^&", 999, "Electronic"),
    ONE_SYMBOL("F", 999, "Electronic"),
    FIFTY_SYMBOLS("50symbols50symbols50symbols50symbols50symbols", 999, "Electronic"),
    ONE_HUNDRED_SIXTY_SYMBOLS("160symbols160symbols160symbols160symbols160symbols160symbols160symbols160symbols" +
            "160symbols160symbols160symbols160symbols160symbols160symbols160symbols160symbols", 999, "Electronic"),
    SPACE(" ", 999, "Electronic"),
    EMPTY("", 999, "Electronic"),
    NULL(null, 999, "Electronic");

    @Setter
    private String title;
    @Setter
    private Integer price;
    @Setter
    private String categoryTitle;

    Title(String title, Integer price, String categoryTitle) {
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
