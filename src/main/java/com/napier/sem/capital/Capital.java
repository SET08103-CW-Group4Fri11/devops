package com.napier.sem.capital;

/**
 * A class to contain Capital cities
 */
public class Capital {
    // Attributes
    private String name;
    private String country;
    private String district;
    private int population;

    /**
     * @param name the capital city's name
     * @param country the country the capital city is located in
     * @param district the district the capital city is located in
     * @param population the population of the capital city
     */

    public Capital(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    // Getters
    public String getName() {return  name;}

    public String getCountry() {return country;}

    public String getDistrict() {return district;}

    public int getPopulation() {return population;}
}
