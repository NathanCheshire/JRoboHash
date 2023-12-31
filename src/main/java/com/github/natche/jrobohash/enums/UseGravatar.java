package com.github.natche.jrobohash.enums;

import com.github.natche.jrobohash.request.RoboHashRequestBuilder;

/**
 * The possible states for dictating whether a Gravatar image should be queried for
 * the avatar key provided to a {@link RoboHashRequestBuilder}.
 */
public enum UseGravatar {
    /**
     * Gravatar should be used.
     */
    YES("yes"),

    /**
     * Gravatar should not be used.
     */
    NO("no"),

    /**
     * Gravatar should be used AND the provided string has already been hashed used MD5.
     */
    HASHED("hashed");

    private final String urlParameterName;

    UseGravatar(String urlParameterName) {
        this.urlParameterName = urlParameterName;
    }

    /**
     * Constructs a URL parameter for this Gravatar state.
     * For example, if {@link true} is provided and this state is {@link #HASHED},
     * then "?gravatar=hashed" would be returned.
     *
     * @param firstParameter whether this is the first URL parameter in the query string
     * @return a URL parameter for this Gravatar state
     */
    public String constructUrlParameter(boolean firstParameter) {
        if (this == UseGravatar.NO) return "";
        return UrlParameter.USE_GRAVATAR.encodeUrlParameter(getUrlParameterRepresentation(), firstParameter);
    }

    /**
     * Returns the URL parameter representation for this use Gravatar.
     *
     * @return the URL parameter representation for this use Gravatar
     */
    public String getUrlParameterRepresentation() {
        return urlParameterName;
    }
}
