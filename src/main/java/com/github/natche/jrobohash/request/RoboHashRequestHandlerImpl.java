package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UrlParameter;
import com.github.natche.jrobohash.util.GeneralUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The default implementation of a {@link RoboHashRequestHandler}.
 */
public class RoboHashRequestHandlerImpl implements RoboHashRequestHandler {
    private static final String PROTOCOL = "https";
    private static final String DOMAIN = "robohash.org";

    private String getImageSetsUrlParameter(RoboHashRequestBuilder builder) {
        ImmutableList<ImageSet> imageSets = ImmutableList.copyOf(builder.getImageSets());
        if (imageSets.size() > 1) {
            return UrlParameter.IMAGE_SETS.encodeUrlParameter(
                    imageSets.stream().map(ImageSet::getUrlParameterName).collect(
                            Collectors.joining(",")), true);
        } else {
            return imageSets.get(0).constructUrlParameter(true);
        }
    }

    /**
     * Builds and returns the request URL based on the current state of a {@link RoboHashRequestBuilder}.
     *
     * @param builder the builder to construct the url from
     * @return the built url
     * @throws NullPointerException if the provided builder is null
     */
    @Override
    public String buildRequestUrl(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(PROTOCOL);
        urlBuilder.append("://");
        urlBuilder.append(DOMAIN);
        urlBuilder.append(getImageSetsUrlParameter(builder));
        urlBuilder.append(UrlParameter.BACKGROUND_SET.encodeUrlParameter(builder.getBackgroundSet().getSetName(), false));

        return urlBuilder.toString();
    }

    /**
     * Builds the URL based on the current state of a {@link RoboHashRequestBuilder}
     * and reads and returns the image from the URL.
     *
     * @param builder the builder to construct the url from
     * @return the {@link Image} read from the URL
     * @throws NullPointerException if the provided builder is null
     */
    @Override
    public BufferedImage getImage(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        return GeneralUtils.readBufferedImage(buildRequestUrl(builder));
    }

    /**
     * Constructs a URL from the provided builder and saves the image to the provided file.
     *
     * @param builder the builder to construct the URL from
     * @param file    the file to save the resulting image to
     * @throws NullPointerException     if the provided builder or file are null
     * @throws IllegalArgumentException if the provided file exists or is a directory
     */
    public void saveToFile(RoboHashRequestBuilder builder, File file) {
        Preconditions.checkNotNull(builder);
        Preconditions.checkNotNull(file);
        Preconditions.checkArgument(file.isFile());
        Preconditions.checkArgument(!file.exists());

        BufferedImage image = getImage(builder);

        // todo save image to

        throw new RuntimeException("Not Implemented");
    }
}
