package com.github.natche.jrobohash.enums;

import com.google.common.base.Preconditions;

/**
 * The URL parameters a RoboHash request URL may contain.
 */
public enum UrlParameter {
    /**
     * The {@link ImageExtension} URL parameter.
     */
    IMAGE_EXTENSION(""),

    /**
     * Whether the request should attempt to find a Gravatar image for the provided avatar key.
     * The avatar key can be the Gravatar email hashed or non-hashed via
     * {@link UseGravatar#HASHED} and {@link UseGravatar#YES} respectively.
     */
    USE_GRAVATAR("gravatar"),

    /**
     * Whether the extension of the requested image should be ignored in the computed hash.
     * Setting this to false will result in different images for equal avatar keys with different image extensions.
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

    private final String urlParameterName;

    UrlParameter(String urlParameterName) {
        this.urlParameterName = urlParameterName;
    }

    /**
     * The URL parameter such as "bgset" for {@link #BACKGROUND_SET}.
     *
     * @return the URL parameter such as "bgset" for {@link #BACKGROUND_SET}
     */
    public String getUrlParameterName() {
        return urlParameterName;
    }

    /**
     * Returns an encoded URL parameter for the provided text and this URL parameter.
     * For example, if "1,2,3" is provided and this {@link UrlParameter} is
     * of type {@link #IMAGE_SETS} then "&sets=1,2,3" would be returned.
     *
     * @param parameter the parameter to encode
     * @return the encoded URL parameter
     * @throws NullPointerException if the provided parameter is null
     * @throws IllegalArgumentException if the provided parameter is empty
     */
    public String encodeUrlParameter(String parameter) {
        Preconditions.checkNotNull(parameter);
        Preconditions.checkArgument(!parameter.trim().isEmpty());

        return encodeUrlParameter(parameter, false);
    }

    /**
     * Returns an encoded URL parameter for the provided text and this URL parameter.
     * For example, if "1,2,3" and {@code true} are provided, and this {@link UrlParameter} is
     * of type {@link #IMAGE_SETS} then "?sets=1,2,3" would be returned.
     *
     * @param parameter        the parameter to encode
     * @param isFirstParameter whether this parameter is the first URL parameter in the query string
     * @return the encoded URL parameter
     * @throws NullPointerException if the provided parameter is null
     * @throws IllegalArgumentException if the provided parameter is empty
     */
    public String encodeUrlParameter(String parameter, boolean isFirstParameter) {
        Preconditions.checkNotNull(parameter);
        Preconditions.checkArgument(!parameter.trim().isEmpty());

        String prefix = isFirstParameter ? "?" : "&";
        return prefix + urlParameterName + "=" + parameter;
    }
}
