package ru.nsu.dunaev;

import ru.nsu.dunaev.enums.Language;

public class Localization {
    Language language;
    public Localization(Language language) {
        this.language = language;
    }

    public String get(){
        return "hello";
    }
}
