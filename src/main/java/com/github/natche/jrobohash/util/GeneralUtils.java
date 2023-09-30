package com.github.natche.jrobohash.util;

import com.github.natche.jrobohash.exceptions.JRoboHashException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * General utility methods used throughout the GravatarJavaClient.
 */
public final class GeneralUtils {
    /**
     * The invalid filename characters for Windows and Unix based systems.
     */
    private static final ImmutableList<Character> INVALID_FILENAME_CHARS = ImmutableList.of(
            '<', '>', ':', '\\', '|', '?', '*', '/', '\'', '"', '\u0000'
    );

    /**
     * The buffer size used by the {@link BufferedReader} when reading the contents of a URL.
     */
    private static final int READ_URL_BUFFER_SIZE = 1024;

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
     * @throws NullPointerException        if the provided URL is null
     * @throws IllegalArgumentException    if the provided URL is empty
     * @throws JRoboHashException if an image cannot be read from the provided URL
     */
    public static BufferedImage readBufferedImage(String url) {
        Preconditions.checkNotNull(url);
        Preconditions.checkArgument(!url.isEmpty());

        try {
            return ImageIO.read(new URL(url));
        } catch (Exception e) {
            throw new JRoboHashException("Failed to get image from url: "
                    + url + ", error: " + e.getMessage());
        }
    }

    /**
     * Reads from the provided URL and returns the response data.
     *
     * @param url the URL to ping and read data from
     * @return the contents of the provided URL
     * @throws NullPointerException        if the provided URL is null
     * @throws IllegalArgumentException    if the provided URL is empty
     * @throws JRoboHashException if an exception occurs when reading from the provided URL
     */
    public static String readUrl(String url) {
        Preconditions.checkNotNull(url);
        Preconditions.checkArgument(!url.isEmpty());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            StringBuilder builder = new StringBuilder();

            char[] chars = new char[READ_URL_BUFFER_SIZE];
            int read;
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }

            return builder.toString();
        } catch (IOException e) {
            throw new JRoboHashException("Failed to read contents of URL: " + url);
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
        Preconditions.checkArgument(!filename.isEmpty());

        for (char c : filename.toCharArray()) {
            if (INVALID_FILENAME_CHARS.contains(c)) return false;
        }

        return true;
    }
}
