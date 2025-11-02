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
     * @param code
     * @param name
     * @param continent
     * @param region
     * @param population
     * @param capital
     */
    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }


    // Getters & Setters
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
