package com.github.natche.jrobohash.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link ImageExtension}s supported by RoboHash.
 */
public class ImageExtensionTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    ImageExtensionTest() {}

    /**
     * Tests for the get extension method.
     */
    @Test
    void testGetExtension() {
        assertEquals("png", ImageExtension.PNG.getExtension());
        assertEquals("jpg", ImageExtension.JPG.getExtension());
        assertEquals("jpeg", ImageExtension.JPEG.getExtension());
        assertEquals("bmp", ImageExtension.BITMAP.getExtension());
    }

    /**
     * Tests for the get extension with period method.
     */
    @Test
    void testGetExtensionWithPeriod() {
        assertEquals(".png", ImageExtension.PNG.getExtensionWithPeriod());
        assertEquals(".jpg", ImageExtension.JPG.getExtensionWithPeriod());
        assertEquals(".jpeg", ImageExtension.JPEG.getExtensionWithPeriod());
        assertEquals(".bmp", ImageExtension.BITMAP.getExtensionWithPeriod());
    }

    /**
     * Tests for the add as suffix method.
     */
    @Test
    void testAddAsSuffix() {
        assertThrows(NullPointerException.class, () -> ImageExtension.PNG.addAsSuffix(null));
        assertThrows(IllegalArgumentException.class, () -> ImageExtension.PNG.addAsSuffix(""));
        assertThrows(IllegalArgumentException.class, () -> ImageExtension.PNG.addAsSuffix("    "));

        assertEquals("my-image.png", ImageExtension.PNG.addAsSuffix("my-image"));
        assertEquals("my-image.jpg", ImageExtension.JPG.addAsSuffix("my-image"));
        assertEquals("my-image.jpeg", ImageExtension.JPEG.addAsSuffix("my-image"));
        assertEquals("my-image.bmp", ImageExtension.BITMAP.addAsSuffix("my-image"));
    }

    /**
     * Tests for the set as image extension method.
     */
    @Test
    void testSetAsImageExtension() {
        assertThrows(NullPointerException.class, () -> ImageExtension.PNG.setAsImageExtension(null));
        assertThrows(IllegalArgumentException.class, () -> ImageExtension.PNG.setAsImageExtension(""));
        assertThrows(IllegalArgumentException.class, () -> ImageExtension.PNG.setAsImageExtension("    "));
        assertEquals("my-image.png", ImageExtension.PNG.setAsImageExtension("my-image"));
        assertEquals("my-image.png", ImageExtension.PNG.setAsImageExtension("my-image.jpeg"));
        assertEquals("my-image.png", ImageExtension.PNG.setAsImageExtension("my-image.jpg"));
        assertEquals("my-image.png", ImageExtension.PNG.setAsImageExtension("my-image.bmp"));
        assertEquals("my-image.png", ImageExtension.PNG.setAsImageExtension("my-image.loop"));
        assertEquals("my-image.loop.png", ImageExtension.PNG.setAsImageExtension("my-image.loop.something"));
        assertEquals("my-image.png.jpeg.jpeg", ImageExtension.JPEG.setAsImageExtension("my-image.png.jpeg.jpeg"));
    }
}
