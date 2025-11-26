package com.napier.sem.languages;

/**
 * A class to contain languages
 */
public class Language {
    // Attributes
    private final String language;
    private final int speakers;
    private final double percentage;

    /**
     * @param language the language name
     * @param speakers the number of speakers
     * @param percentage the percentage of speakers
     */
    public Language(String language, int speakers, double percentage) {
        this.language = language;
        this.speakers = speakers;
        this.percentage = percentage;
    }

    // Getters
    public String getLanguage() {return language;}

    public int getSpeakers() {return speakers;}

    public double getPercentage() {return percentage;}

    @Override
    public String toString() {
        return String.format("Language{language='%s', speakers=%d, percentage=%.2f}", language, speakers, percentage);
    }
}
