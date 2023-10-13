package com.github.natche.jrobohash.enums;

/**
 * The supported RoboHash avatar image sets.
 */
public enum ImageSet {
    /**
     * The default set of robots.
     */
    DEFAULT("set1", "1"),

    /**
     * The monsters set.
     */
    MONSTERS("set2", "2"),

    /**
     * The "sexy" robots set (not my name choice).
     * @see <a href="https://prnt.sc/8rY4U-QfmEau">Proof the official RoboHash documentation says sexy robot</a>
     */
    SEXY_ROBOTS("set3", "3"),

    /**
     * The kittens set.
     */
    KITTENS("set4", "4"),

    /**
     * The human set for "those afraid of the uprising".
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

    /**
     * Returns the URL parameter name for this set, such as "set1" for {@link ImageSet#DEFAULT}.
     *
     * @return the URL parameter name for this set, such as "set1" for {@link ImageSet#DEFAULT}
     */
    public String getUrlParameterName() {
        return urlParameterName;
    }

    /**
     * Returns the list URL parameter name for this set such as "1" for {@link ImageSet#DEFAULT}.
     *
     * @return the list URL parameter name for this set such as "1" for {@link ImageSet#DEFAULT}
     */
    public String getListUrlParameterName() {
        return listUrlParameterName;
    }

    /**
     * Constructs a URL parameter from this image set.
     *
     * @param firstParameter whether this URL parameter is the first following the request URL
     * @return a URL parameter for this image set.
     */
    public String constructUrlParameter(boolean firstParameter) {
        return UrlParameter.IMAGE_SET.encodeUrlParameter(urlParameterName, firstParameter);
    }
}
