package com.github.natche.jrobohash.enums;

/**
 * The image extensions supported by the RoboHash API. If, for some reason, an invalid image
 * extension makes its way to the RoboHash servers, an image named with the provided invalid
 * extension will be returned but the underlying image will be of {@link #PNG} format.
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
     * The PNG extension.
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
     * For example, providing "BartSimpson" will return "BartSimpson.jpg" for {@link #JPG}.
     *
     * @param prefix the prefix for this image extension
     * @return the prefix followed by a period and image extension
     */
    public String addAsSuffix(String prefix) {
        return prefix + getExtensionWithPeriod();
    }

    public String setAsImageExtension(String filename) {
        return filename.lastIndexOf(".") != -1
                ? filename.substring(0, filename.lastIndexOf(".")) + getExtensionWithPeriod()
                : filename + getExtensionWithPeriod();
    }
}
