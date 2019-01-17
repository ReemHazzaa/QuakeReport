package com.example.reem.quakereport;


/**
 * Created by reemHazzaa on 11/12/2018.
 *
 * {@link Earthquake} represents a single Earthquake.
 *  Each object has 4 properties: magnitude, location, date and url .
 */
public class Earthquake {

    /** Magnitude of the earthquake object */
    private double mEarthquakeMagnitude;

    /** Location where the earthquake took place */
    private String mEarthquakeLocation;

    /** Date in which the earthquake occurred */
    private long mTimeInMilliseconds;

    /** Website URL of the earthquake */
    private String mUrl;


    /**
     * Create a new Earthquake object.
     *
     * @param earthquakeMagnitude is the magnitude of the earthquake (e.g. 4.5)
     * @param earthquakeLocation is the location of the earthquake (e.g. Cairo)
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the earthquake happened
     * @param url is the website URL to find more details about the earthquake
     */
    public Earthquake(double earthquakeMagnitude, String earthquakeLocation, long timeInMilliseconds, String url) {
        mEarthquakeMagnitude = earthquakeMagnitude;
        mEarthquakeLocation = earthquakeLocation;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    /**
     * Get the Magnitude of the earthquake
     */
    public double getEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    /**
     * Get the Location of the earthquake
     */
    public String getEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    /**
     * Get the Date of the earthquake
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
