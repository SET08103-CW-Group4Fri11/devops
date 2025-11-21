package com.napier.sem.capital;

/**
 * A class to contain Capital cities
 */
public class Capital {
    // Attributes
    private String name;
    private String country;
    private int population;

    /**
     * @param name the capital city's name
     * @param country the country the capital city is located in
     * @param population the population of the capital city
     */

    public Capital(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    // Getters
    public String getName() {return  name;}

    public String getCountry() {return country;}

    public int getPopulation() {return population;}

    @Override
    public String toString() {
        return String.format("Capital{name='%s', country='%s', population=%d}", name, country, population);
    }
}
