package com.github.natche.jrobohash.request;

import java.awt.*;

/**
 * A handler for building URLs from {@link RoboHashRequestBuilder}s and getting the resulting {@link Image}.
 */
public interface RoboHashRequestHandler {
    /**
     * Builds and returns the request URL based on the current state of a {@link RoboHashRequestBuilder}.
     *
     * @param builder the builder to construct the url from
     * @return the built url
     */
    String buildRequestUrl(RoboHashRequestBuilder builder);

    /**
     * Builds the URL based on the current state of a {@link RoboHashRequestBuilder}
     * and reads and returns the image from the URL.
     *
     * @param builder the builder to construct the url from
     * @return the {@link Image} read from the URL
     */
    Image getImage(RoboHashRequestBuilder builder);
}
