package ru.retrofit.enums;

import lombok.Getter;

public enum Title {

    LATIN("Latin"),
    CYRILLIC("Кириллица"),
    CHINESE("汉字"),
    NON_LETTERS("#$%@^&"),
    ONE_SYMBOL("F"),
    ONE_HUNDRED_SYMBOLS("100symbols100symbols100symbols100symbols100symbols" +
            "100symbols100symbols100symbols 100symbols100symbols"),
    ONE_HUNDRED_SIXTY_SYMBOLS("160symbols160symbols160symbols160symbols160symbols160symbols160symbols160symbols" +
            "160symbols160symbols160symbols160symbols160symbols160symbols160symbols160symbols"),
    EMPTY(""),
    SPACE(" "),
    NULL(null);

    @Getter
    private String title;


    Title(String title) {
        this.title = title;
    }
}
