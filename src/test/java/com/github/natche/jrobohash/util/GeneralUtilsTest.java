package com.github.natche.jrobohash.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralUtilsTest {
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

    @Test
    void testReadBufferedImage() {

    }

    @Test
    void testIsValidFilename() {

    }

    @Test
    void testIsValidUrlChars() {

    }

    @Test
    void testEncodeUrl() {

    }
}
