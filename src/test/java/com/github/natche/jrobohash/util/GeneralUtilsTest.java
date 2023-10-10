package com.github.natche.jrobohash.util;

import com.github.natche.jrobohash.exceptions.JRoboHashException;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the utility methods exposed by {@link GeneralUtils}.
 */
public class GeneralUtilsTest {
    /**
     * Creates a new instance of this class for testing purposes.
     */
    GeneralUtilsTest() {}

    /**
     * Test to ensure the utility class cannot be instantiated.
     */
    @Test
    void testInstantiation() {
        try {
            Constructor<GeneralUtils> constructor =
                    GeneralUtils.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            assertTrue(e instanceof InvocationTargetException);
            Throwable target = ((InvocationTargetException) e).getTargetException();
            assertTrue(target instanceof AssertionError);
            assertEquals("Cannot create instances of GeneralUtils", target.getMessage());
        }
    }

    /**
     * Test to ensure reading a buffered image from a particular url works.
     */
    @Test
    void testReadBufferedImage() {
        assertThrows(NullPointerException.class, () -> GeneralUtils.readBufferedImage(null));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.readBufferedImage(""));
        assertThrows(JRoboHashException.class, () -> GeneralUtils.readBufferedImage("url"));

        AtomicReference<BufferedImage> bi = new AtomicReference<>();
        assertDoesNotThrow(() -> bi.set(GeneralUtils.readBufferedImage(
                "https://upload.wikimedia.org/wikipedia/en/5/51/Minecraft_cover.png")));
        assertEquals(220, bi.get().getWidth());
        assertEquals(280, bi.get().getHeight());
    }

    /**
     * Test to ensure valid and invalid filenames are properly categorized.
     */
    @Test
    void testIsValidFilename() {
        assertThrows(NullPointerException.class, () -> GeneralUtils.isValidFilename(null));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.isValidFilename(""));
        assertDoesNotThrow(() -> GeneralUtils.isValidFilename("filename"));

        assertTrue(GeneralUtils.isValidFilename("filename"));
        assertTrue(GeneralUtils.isValidFilename("my_filename_123456789"));
        assertTrue(GeneralUtils.isValidFilename("my_filename_123456789.txt"));
        assertTrue(GeneralUtils.isValidFilename("my_filename_123456789...txt"));

        assertFalse(GeneralUtils.isValidFilename("$%^&*()"));
        assertFalse(GeneralUtils.isValidFilename("<>:/"));
    }

    /**
     * Test that a url containing invalid characters is properly categorized.
     */
    @Test
    void testIsValidUrlChars() {
        assertThrows(NullPointerException.class, () -> GeneralUtils.isValidUrlChars(null));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.isValidUrlChars(""));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.isValidUrlChars("   "));
        assertTrue(GeneralUtils.isValidUrlChars("validURL123-._~"), "Expected input to be valid, but was considered invalid.");
        assertFalse(GeneralUtils.isValidUrlChars("invalidURL$#@"), "Expected input to be invalid, but was considered valid.");
    }

    /**
     * Test url encoding works as expected.
     */
    @Test
    void testEncodeUrl() {
        assertThrows(NullPointerException.class, () -> GeneralUtils.encodeUrl(null));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.encodeUrl(""));
        assertThrows(IllegalArgumentException.class, () -> GeneralUtils.encodeUrl("   "));
        assertEquals("asdf", GeneralUtils.encodeUrl("asdf"));
        assertEquals("two+words", GeneralUtils.encodeUrl("two words"));
    }
}
