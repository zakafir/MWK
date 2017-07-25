package com.st00.afir.mwk;

/**
 * Created by zakaria_afir on 25/07/2017.
 */

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String englishTranslation, miwokTranslation;
    private int image = NO_IMAGE_PROVIDED;

    public Word(String englishTranslation, String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    public Word(String englishTranslation, String miwokTranslation, int image) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.image = image;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getImage() {
        return image;
    }

    public boolean hasImage() {
        if (image == NO_IMAGE_PROVIDED) return false;
        return true;
    }
}
