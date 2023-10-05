package com.github.natche.jrobohash.enums;

/**
 * The URL parameters a RoboHash request URL may contain.
 */
public enum UrlParameter {
    /**
     * The {@link ImageExtension}.
     */
    IMAGE_EXTENSION(""),

    /**
     * Whether the request should attempt to find a Gravatar image.
     */
    USE_GRAVATAR("gravatar"),

    /**
     * Whether the extension of the requested image should be ignored in the computed hash.
     */
    IGNORE_EXTENSION("ignoreext"),

    /**
     * The image set the request should use.
     */
    IMAGE_SET("set"),

    /**
     * The image sets the request should use.
     */
    IMAGE_SETS("sets"),

    /**
     * The background set the request should use.
     */
    BACKGROUND_SET("bgset"),

    /**
     * The size of the requested image.
     */
    SIZE("size");

    private final String urlParameter;

    UrlParameter(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    /**
     * The url encoded parameter such as "bgset" for {@link #BACKGROUND_SET}.
     *
     * @return url encoded parameter such as "bgset" for {@link #BACKGROUND_SET}
     */
    public String getUrlParameter() {
        return urlParameter;
    }

    /**
     * Returns an encoded URL parameter for the provided text and this URL parameter.
     * For example, if "1,2,3" and true are provided, and this {@link UrlParameter} is
     * of type {@link #IMAGE_SETS} then "&sets=1,2,3" would be returned.
     *
     * @param parameter the parameter to encode
     * @return the encoded URL parameter
     */
    public String encodeUrlParameter(String parameter) {
        return encodeUrlParameter(parameter, false);
    }

    /**
     * Returns an encoded URL parameter for the provided text and this URL parameter.
     * For example, if "1,2,3" and true are provided, and this {@link UrlParameter} is
     * of type {@link #IMAGE_SETS} then "?sets=1,2,3" or "&sets=1,2,3" would be returned.
     *
     * @param parameter        the parameter to encode
     * @param isFirstParameter whether this parameter is the first URL parameter in the query string section of the URL.
     * @return the encoded URL parameter
     */
    public String encodeUrlParameter(String parameter, boolean isFirstParameter) {
        String prefix = isFirstParameter ? "?" : "&";
        return prefix + urlParameter + "=" + parameter;
    }
}
