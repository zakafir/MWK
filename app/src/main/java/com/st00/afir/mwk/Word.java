package com.st00.afir.mwk;

/**
 * Created by zakaria_afir on 25/07/2017.
 */

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String englishTranslation, miwokTranslation;
    private int image = NO_IMAGE_PROVIDED;
    private int audio;


    public Word(String englishTranslation, String miwokTranslation, int audio) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audio = audio;
    }

    public Word(String englishTranslation, String miwokTranslation, int image, int audio) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.image = image;
        this.audio = audio;
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

    public int getAudio() {
        return audio;
    }

    @Override
    public String toString() {
        return "Word{" +
                "englishTranslation='" + englishTranslation + '\'' +
                ", miwokTranslation='" + miwokTranslation + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                '}';
    }
}
