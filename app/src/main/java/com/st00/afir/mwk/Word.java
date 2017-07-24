package com.st00.afir.mwk;

/**
 * Created by zakaria_afir on 25/07/2017.
 */

public class Word {
    private String englishTranslation, miwokTranslation;

    public Word(String englishTranslation, String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }
}
