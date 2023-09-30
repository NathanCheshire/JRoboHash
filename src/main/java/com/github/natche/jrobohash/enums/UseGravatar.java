package com.github.natche.jrobohash.enums;

/**
 * The enums for instruction RoboHash to possibly use Gravatar as the image
 * remote if a provided sha or pre-sha string exists.
 */
public enum UseGravatar {
    /**
     * Gravatar should be used.
     */
    YES,

    /**
     * Gravatar should not be used.
     */
    NO,

    /**
     * Gravatar should be used AND the provided string has already been MD5 hashed.
     */
    HASHED;

    public String constructUrlParameter(boolean firstParameter) {
        if (this == UseGravatar.NO) return "";
        return UrlParameter.USE_GRAVATAR.encodeUrlParameter(toString(), firstParameter);
    }

    @Override
    public String toString() {
        return switch (this) {
            case NO -> "no";
            case YES -> "yes";
            case HASHED ->  "hashed";
        };
    }
}
