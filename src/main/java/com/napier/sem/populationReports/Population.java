package com.napier.sem.populationReports;

public class Population {
    private String name;
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;

    public Population(String name, long totalPopulation, long cityPopulation, long nonCityPopulation) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
    }

    public String getName() {
        return name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    public void displayInfo() {
        System.out.println("Region/Country/ Cont: " + name);
        System.out.println("Total Population: " + totalPopulation);
        System.out.println("City Population: " + cityPopulation);
        System.out.println("Outside of Cities Population: " + nonCityPopulation);
    }

    @Override
    public String toString()
    {
        return String.format("Population{Name ='%s', Total Population =%d, In Cities =%d, Outside Of Cities =%d}", name, totalPopulation, cityPopulation, nonCityPopulation);
    }
}
