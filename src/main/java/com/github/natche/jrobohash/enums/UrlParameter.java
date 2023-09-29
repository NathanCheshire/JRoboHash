package com.github.natche.jrobohash.enums;

public enum UrlParameter {
    IMAGE_TYPE("", false),
    USE_GRAVATAR("gravatar", false),
    IGNORE_EXTENSION("ignoreext", false),
    SET("set", false),
    BACKGROUND_SET("bgset", false),
    SIZE("size");

    private final String urlParameter;
    private final boolean required;
    UrlParameter(String urlParameter) {
        this(urlParameter, false);
    }

    UrlParameter(String urlParameter, boolean required) {
        this.urlParameter = urlParameter;
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public String getUrlParameter() {
        return urlParameter;
    }
}
