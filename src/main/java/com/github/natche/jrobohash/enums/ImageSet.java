package com.github.natche.jrobohash.enums;

/**
 * The possible RoboHash avatar image sets.
 */
public enum ImageSet {
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
    HUMANS("set5"),

    /**
     * Any set is valid and RoboHash may choose.
     */
    ANY("any");

    private final String urlParameterName;

    ImageSet(String urlParameterName) {
        this.urlParameterName = urlParameterName;
    }

    public String getUrlParameterName() {
        return urlParameterName;
    }

    public String constructUrlParameter(boolean firstParameter) {
        return UrlParameter.IMAGE_SET.encodeUrlParameter(urlParameterName, firstParameter);
    }
}
