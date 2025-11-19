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

    public double getCityPercentage() {
        return (totalPopulation == 0) ? 0 : (cityPopulation * 100.0 / totalPopulation); // this is a helper function for the percentage of people living in the area but in cities
    }

    public double getNonCityPercentage() {
        return (totalPopulation == 0) ? 0 : (nonCityPopulation * 100.0 / totalPopulation); // same as aboove just outside of city areas
    }

    public void displayReport() {
        System.out.println("---------------------------------------");
        System.out.println("Population Report for: " + name);
        System.out.println("Total Population: " + totalPopulation);
        System.out.println("Living in Cities: " + cityPopulation +
                " (" + String.format("%.2f", getCityPercentage()) + "%)");
        System.out.println("Not Living in Cities: " + nonCityPopulation +
                " (" + String.format("%.2f", getNonCityPercentage()) + "%)");
        System.out.println("---------------------------------------");
    }

    @Override
    public String toString() {
        return String.format(
                "Population{Name='%s', Total=%d, In Cities=%d (%.2f%%), Outside Cities=%d (%.2f%%)}",
                name,
                totalPopulation,
                cityPopulation,
                getCityPercentage(),
                nonCityPopulation,
                getNonCityPercentage()
        );
    }
}
