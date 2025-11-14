package com.napier.sem.countries;

/**
 * A class to contain countries
 */
public class Country {
    // Attributes
    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;
    private String capital;

    /**
     * @param code the country's code
     * @param name the country's name
     * @param continent the continent the country is located in
     * @param region the region the country is located in
     * @param population the population of the country
     * @param capital the capital city of the country
     */
    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }


    // Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }


    public String getContinent() {
        return continent;
    }


    public int getPopulation() {
        return population;
    }


    public String getCapital() {
        return capital;
    }
}
