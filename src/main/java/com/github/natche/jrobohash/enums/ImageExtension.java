package com.github.natche.jrobohash.enums;

import com.google.common.base.Preconditions;

/**
 * The image extensions supported by the RoboHash API. If, for some reason, an invalid image
 * extension is provided in the request URL, RoboHash will return an image named with the provided
 * invalid extension but the underlying image will be of {@link #PNG} format.
 */
public enum ImageExtension {
    /**
     * The JPEG shorthand extension.
     */
    JPG("jpg"),

    /**
     * The standard JPEG extension.
     */
    JPEG("jpeg"),

    /**
     * The PNG extension for lossless compression.
     */
    PNG("png"),

    /**
     * The Bitmap extension. This can take a little longer for the RoboHash server to generate and return.
     */
    BITMAP("bmp");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Returns this image extension such as "jpg" for {@link #JPG}.
     *
     * @return this image extension such as "jpg" for {@link #JPG}
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Returns this image extension with a prefixed period such as ".jpg" for {@link #JPG}.
     *
     * @return this image extension with a prefixed period such as ".jpg" for {@link #JPG}
     */
    public String getExtensionWithPeriod() {
        return "." + getExtension();
    }

    /**
     * Adds this extension and period as a suffix to the provided string.
     * For example, providing "BartSimpson" and {@link #JPG} will return "BartSimpson.jpg".
     *
     * @param prefix the prefix for this image extension
     * @return the prefix followed by a period and image extension
     * @throws NullPointerException if the provided prefix is null
     * @throws IllegalArgumentException if the provided prefix is empty
     */
    public String addAsSuffix(String prefix) {
        Preconditions.checkNotNull(prefix);
        Preconditions.checkArgument(!prefix.trim().isEmpty());

        return prefix + getExtensionWithPeriod();
    }

    /**
     * Sets the extension of the provided filename to this extension.
     * Note, if a period is contained in the filename provided, the text will be split at the last
     * period and the text from the last period all the way to the end will be replaced with this extension.
     *
     * @param filename the filename possibly containing an extension which will be stripped away
     * @return the provided filename with this extension as the extension for the filename
     * @throws NullPointerException if the provided filename is null
     * @throws IllegalArgumentException if the provided filename is empty
     */
    public String setAsImageExtension(String filename) {
        Preconditions.checkNotNull(filename);
        Preconditions.checkArgument(!filename.trim().isEmpty());

        int lastPeriodIndex = filename.lastIndexOf(".");
        boolean containsPeriod = lastPeriodIndex != -1;

        return containsPeriod
                ? filename.substring(0, lastPeriodIndex) + getExtensionWithPeriod()
                : filename + getExtensionWithPeriod();
    }
}
