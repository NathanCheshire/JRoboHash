package com.github.natche.jrobohash.enums;

/**
 * The possible RoboHash avatar construction sets.
 */
public enum Set {
    /**
     * The default set.
     */
    DEFAULT("set1"),

    /**
     * The monsters set.
     */
    MONSTERS("set2"),

    /**
     * The sexy robots set (not my name choice).
     */
    SEXY_ROBOTS("set3"),

    /**
     * The kittens set.
     */
    KITTENS("set4"),

    /**
     * The human set for those afraid of the uprising.
     */
    HUMANS("set5");

    private final String urlParameterName;

    Set(String urlParameterName) {
        this.urlParameterName = urlParameterName;
    }

    public String getUrlParameterName() {
        return urlParameterName;
    }

    public String constructUrlParameter(boolean firstParameter) {
        String prefix = firstParameter ? "?" : "&";
        return prefix + "set=" + urlParameterName;
    }
}
