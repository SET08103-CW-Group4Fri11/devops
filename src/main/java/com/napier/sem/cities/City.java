package com.napier.sem.cities;

/**
 * A class to contain cities
 */
public class City {
    // Attributes
    private final String name;
    private final String country;
    private final String district;
    private final int population;

    /**
     * @param name the city's name
     * @param country the country the city is located in
     * @param district the district the city is located in
     * @param population the population of the city
     */

    public City(String name, String country, String district, int population) {
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

    @Override
    public String toString() {
        return String.format("City{name='%s', country='%s', district='%s', population=%d}", name, country, district, population);
    }
}
