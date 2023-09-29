package com.github.natche.jrobohash.enums;

/**
 * The possible background sets for RoboHash.
 */
public enum BackgroundSet {
    /**
     * The standard outside/outdoor backgrounds.
     */
    OUTSIDE("bg1"),

    /**
     * Mostly spiral backgrounds with a few other patterns.
     */
    SPIRAL_AND_PATTERNS("bg2"),

    /**
     * Any background is valid and RoboHash may choose.
     */
    ANY("any");

    private final String setName;

    BackgroundSet(String setName) {
        this.setName = setName;
    }

    /**
     * Returns the set name for this background set.
     *
     * @return the set name for this background set
     */
    public String getSetName() {
        return setName;
    }

    /**
     * Constructs the url parameter for this background set.
     *
     * @param firstParameter whether this parameter is the first in the list of url parameters
     * @return the url parameter for this background set
     */
    public String constructUrlParameter(boolean firstParameter) {
        String prefix = firstParameter ? "?" : "&";
        return prefix + "bgset=" + setName;
    }
}
