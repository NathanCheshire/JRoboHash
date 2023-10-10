package com.github.natche.jrobohash.util;

import com.github.natche.jrobohash.exceptions.JRoboHashException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * General utility methods used throughout JRoboHash.
 */
public final class GeneralUtils {
    private static final Pattern ROBOHASH_SAFE_STRING_PATTERN = Pattern.compile("^[a-zA-Z0-9-._~]+$");

    /**
     * The invalid filename characters for Windows and Unix based systems.
     */
    private static final ImmutableList<Character> INVALID_FILENAME_CHARS = ImmutableList.of(
            '<', '>', ':', '\\', '|', '?', '*', '/', '\'', '"', '\u0000'
    );

    /**
     * Suppress default constructor.
     *
     * @throws AssertionError if invoked
     */
    private GeneralUtils() {
        throw new AssertionError("Cannot create instances of GeneralUtils");
    }

    /**
     * Returns a buffered image read from the provided URL.
     *
     * @param url the URL
     * @return the URL from the provided image
     * @throws NullPointerException     if the provided URL is null
     * @throws IllegalArgumentException if the provided URL is empty
     * @throws JRoboHashException       if an image cannot be read from the provided URL
     */
    public static BufferedImage readBufferedImage(String url) {
        Preconditions.checkNotNull(url);
        Preconditions.checkArgument(!url.trim().isEmpty());

        try {
            return ImageIO.read(new URL(url));
        } catch (Exception e) {
            throw new JRoboHashException("Failed to get image from url: "
                    + url + ", error: " + e.getMessage());
        }
    }

    /**
     * Returns whether the provided filename is valid for this operating system.
     *
     * @param filename the filename
     * @return whether the provided filename is valid for this operating system
     * @throws NullPointerException     if the provided filename is null
     * @throws IllegalArgumentException if the provided filename is empty
     */
    public static boolean isValidFilename(String filename) {
        Preconditions.checkNotNull(filename);
        Preconditions.checkArgument(!filename.trim().isEmpty());

        return filename.chars()
                .mapToObj(c -> (char) c)
                .noneMatch(INVALID_FILENAME_CHARS::contains);
    }

    /**
     * Returns whether the provided input is valid to pass to RoboHash.
     *
     * @param input the input string to test for validity
     * @return whether the provided input is valid to pass to RoboHash
     * @throws NullPointerException     if the provided input is null
     * @throws IllegalArgumentException if the provided input is empty
     */
    public static boolean isValidUrlChars(String input) {
        Preconditions.checkNotNull(input);
        Preconditions.checkArgument(!input.trim().isEmpty());

        return ROBOHASH_SAFE_STRING_PATTERN.matcher(input).matches();
    }

    /**
     * Encodes the provided input to be provided in a URL.
     *
     * @param input the input to be encoded in a URL
     * @return the provided input after encoding for a URL
     * @throws NullPointerException     if the provided input is null
     * @throws IllegalArgumentException if the provided input is empty
     */
    public static String encodeUrl(String input) {
        Preconditions.checkNotNull(input);
        Preconditions.checkArgument(!input.trim().isEmpty());

        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }
}
