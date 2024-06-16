package org.example.immigration_stats_java;

/**
 * The ImmigrationData class represents immigration statistics for a specific year in Canada.
 * It holds data for the year and the number of immigrants for that year in Canada.
 */
public class ImmigrationData {
    // The year for which the immigration data is recorded
    private final String year;

    // The number of immigrants for the specified year in canada
    private final int number;

    /**
     * Constructs a new ImmigrationData object with the specified year and number of immigrants.
     *
     * @param year the year for which the immigration data is recorded
     * @param number the number of immigrants for the specified year
     */
    public ImmigrationData(String year, int number) {
        this.year = year;
        this.number = number;
    }

    /**
     * Returns the year for which the immigration data is recorded.
     *
     * @return the year as a String
     */
    public String getYear() {
        return year;
    }

    /**
     * Returns the number of immigrants for the specified year.
     *
     * @return the number of immigrants as an int
     */
    public int getNumber() {
        return number;
    }
}
