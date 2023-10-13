package com.github.natche.jrobohash.enums;

/**
 * The possible RoboHash avatar image sets.
 */
public enum ImageSet {
    /**
     * The default set.
     */
    DEFAULT("set1", "1"),

    /**
     * The monsters set.
     */
    MONSTERS("set2", "2"),

    /**
     * The sexy robots set (not my name choice).
     */
    SEXY_ROBOTS("set3", "3"),

    /**
     * The kittens set.
     */
    KITTENS("set4", "4"),

    /**
     * The human set for those afraid of the uprising.
     */
    HUMANS("set5", "5"),

    /**
     * Any set is valid and RoboHash may choose.
     */
    ANY("any", null);

    private final String urlParameterName;
    private final String listUrlParameterName;

    ImageSet(String urlParameterName, String listUrlParameterName) {
        this.urlParameterName = urlParameterName;
        this.listUrlParameterName = listUrlParameterName;
    }

    public String getUrlParameterName() {
        return urlParameterName;
    }

    public String getListUrlParameterName() {
        return listUrlParameterName;
    }

    public String constructUrlParameter(boolean firstParameter) {
        return UrlParameter.IMAGE_SET.encodeUrlParameter(urlParameterName, firstParameter);
    }
}
