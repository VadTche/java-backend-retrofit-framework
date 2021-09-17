package ru.retrofit.enums;

import lombok.Getter;

public enum Title {

    LATIN("Latin"),
    CYRILLIC("Cyrillic"),
    CHINESE("Chinese"),
    ONE_SYMBOL("1 symbol"),
    TEN_SYMBOLS("10 symbols"),
    ONE_HUNDRED_SYMBOLS("100 symbols"),
    BILLION_SYMBOLS("Billion symbols");

    @Getter
    private String title;


    Title(String title) {
        this.title = title;
    }
}
