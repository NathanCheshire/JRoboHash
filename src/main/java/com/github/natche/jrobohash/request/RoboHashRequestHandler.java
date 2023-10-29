package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UrlParameter;
import com.github.natche.jrobohash.exceptions.JRoboHashException;
import com.github.natche.jrobohash.util.GeneralUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * A handler for accepting {@link RoboHashRequestBuilder}s.
 */
public class RoboHashRequestHandler {
    /**
     * The domain URL header for RoboHash.
     */
    private static final String DOMAIN_HEADER = "https://robohash.org/";

    /**
     * The separation character for width and height.
     */
    private static final String WIDTH_HEIGHT_SEPARATOR = "x";

    /**
     * Suppress default constructor to prevent instantiation via reflection.
     *
     * @throws AssertionError if invoked
     */
    private RoboHashRequestHandler() {
        throw new AssertionError("Cannot create instances of RoboHashRequestHandler");
    }

    /**
     * Builds and returns the request URL based on the current state of a {@link RoboHashRequestBuilder}.
     *
     * @param builder the builder to construct the URL from
     * @return the built URL
     * @throws NullPointerException if the provided builder is null
     */
    public static String buildRequestUrl(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        StringBuilder urlBuilder = initializeUrlBuilder(builder);
        addUrlParameters(builder, urlBuilder);
        return urlBuilder.toString();
    }

    /**
     * Builds the URL based on the current state of a {@link RoboHashRequestBuilder}
     * and reads and returns the image from the URL.
     *
     * @param builder the builder to construct the URL from
     * @return the {@link Image} read from the URL
     * @throws NullPointerException if the provided builder is null
     */
    public static BufferedImage getImage(RoboHashRequestBuilder builder) {
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
     * @throws JRoboHashException if the downloaded image cannot be saved
     */
    public static void saveToFile(RoboHashRequestBuilder builder, File file) {
        Preconditions.checkNotNull(builder);
        Preconditions.checkNotNull(file);
        Preconditions.checkArgument(!file.isDirectory());
        Preconditions.checkArgument(!file.exists());

        BufferedImage image = getImage(builder);

        try {
            ImageIO.write(image, builder.getImageExtension().getExtension(), file);
        } catch (IOException e) {
            throw new JRoboHashException(
                    "Failed to write image to file: " + file.getName() + ", error: " + e.getMessage());
        }
    }

    /**
     * Initializes and returns a {@link StringBuilder} with the RoboHash
     * domain header and avatar key already set up for the request.
     *
     * @param builder the builder
     * @return the initialized string builder
     * @throws NullPointerException if the provided builder is null
     */
    private static StringBuilder initializeUrlBuilder(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        StringBuilder urlBuilder = new StringBuilder(DOMAIN_HEADER);
        String avatarKey = builder.getImageExtension().setAsImageExtension(builder.getAvatarKey());
        urlBuilder.append(avatarKey);
        return urlBuilder;
    }

    /**
     * Adds the URL parameters from the provided builder to the provided existing URl string builder.
     *
     * @param builder    the RoboHash request builder
     * @param urlBuilder the string builder initialized with the RoboHash domain and avatar key
     * @throws NullPointerException if either the provided builder or string builder is null
     */
    private static void addUrlParameters(RoboHashRequestBuilder builder, StringBuilder urlBuilder) {
        Preconditions.checkNotNull(builder);
        Preconditions.checkNotNull(urlBuilder);

        urlBuilder.append(getImageSetsUrlParameter(builder));
        urlBuilder.append(UrlParameter.BACKGROUND_SET.encodeUrlParameter(builder.getBackgroundSet().getBackgroundSetName()));
        urlBuilder.append(constructSizeParameter(builder));
        urlBuilder.append(builder.getUseGravatar().constructUrlParameter(false));
        if (!builder.shouldIgnoreExtension()) {
            urlBuilder.append(UrlParameter.IGNORE_EXTENSION.encodeUrlParameter("false"));
        }
    }

    /**
     * Constructs the image sets URL parameter part for the image sets the provided builder can use.
     *
     * @param builder the builder
     * @return the URL parameter string for the image sets
     * @throws IllegalArgumentException if the provided builder is null
     */
    private static String getImageSetsUrlParameter(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        ImmutableList<ImageSet> imageSets = ImmutableList.copyOf(builder.getImageSets());
        if (imageSets.contains(ImageSet.ANY)) return ImageSet.ANY.constructUrlParameter(true);

        return switch (imageSets.size()) {
            case 0:
                throw new IllegalStateException("Builder has no images sets");
            case 1:
                yield imageSets.get(0).constructUrlParameter(true);
            default:
                yield UrlParameter.IMAGE_SETS.encodeUrlParameter(imageSets.stream()
                        .map(ImageSet::getListUrlParameterName)
                        .collect(Collectors.joining(",")), true);
        };
    }

    /**
     * Constructs the size parameter for the provided builder.
     *
     * @param builder the builder
     * @return the size parameter for the provided builder
     * @throws IllegalArgumentException if the provided builder is null
     */
    private static String constructSizeParameter(RoboHashRequestBuilder builder) {
        Preconditions.checkNotNull(builder);

        String sizeQueryStringParamValue = builder.getWidth() + WIDTH_HEIGHT_SEPARATOR + builder.getHeight();
        return UrlParameter.SIZE.encodeUrlParameter(sizeQueryStringParamValue);
    }
}
