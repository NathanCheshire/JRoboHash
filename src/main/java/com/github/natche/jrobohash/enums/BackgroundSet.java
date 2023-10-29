package com.github.natche.jrobohash.enums;

/**
 * The background image sets supported by RoboHash.
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

    private final String backgroundSetName;

    BackgroundSet(String setName) {
        this.backgroundSetName = setName;
    }

    /**
     * Returns the set name for this background set.
     *
     * @return the set name for this background set
     */
    public String getBackgroundSetName() {
        return backgroundSetName;
    }

    /**
     * Constructs the URL parameter for this background set.
     *
     * @param firstParameter whether this parameter is the first in the list of URL parameters
     * @return the URL parameter for this background set
     */
    public String constructUrlParameter(boolean firstParameter) {
        return UrlParameter.BACKGROUND_SET.encodeUrlParameter(backgroundSetName, firstParameter);
    }
}
