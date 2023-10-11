package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for the default implementation of {@link RoboHashRequestBuilder}.
 */
public class RoboHashRequestBuilderImplTest {
    /**
     * Creates a new instance of this class for testing purposes.
     */
    RoboHashRequestBuilderImplTest() {}

    /**
     * Tests construction of a new object.
     */
    @Test
    void testConstruction() {
        assertThrows(NullPointerException.class, () -> new RoboHashRequestBuilderImpl(null));
        assertThrows(NullPointerException.class, () -> new RoboHashRequestBuilderImpl(null, false));
        assertThrows(NullPointerException.class, () -> new RoboHashRequestBuilderImpl(null, true));
        assertThrows(IllegalArgumentException.class, () -> new RoboHashRequestBuilderImpl(""));
        assertThrows(IllegalArgumentException.class, () -> new RoboHashRequestBuilderImpl("     "));
        assertThrows(IllegalArgumentException.class, () -> new RoboHashRequestBuilderImpl("]}", false));

        assertDoesNotThrow(() -> new RoboHashRequestBuilderImpl("key"));
        assertDoesNotThrow(() -> new RoboHashRequestBuilderImpl("key", false));
    }

    /**
     * Tests for the accessor and mutator methods.
     */
    @Test
    void testAccessorsMutators() {
        RoboHashRequestBuilderImpl impl = new RoboHashRequestBuilderImpl("robot", false);
        assertEquals("robot", impl.getAvatarKey());
        assertFalse(impl.isSafeUrlMode());
        assertEquals(ImmutableList.of(ImageSet.ANY), impl.getImageSets());
        assertEquals(BackgroundSet.ANY, impl.getBackgroundSet());
        assertEquals(300, impl.getWidth());
        assertEquals(300, impl.getHeight());
        assertEquals(UseGravatar.NO, impl.getUseGravatar());
        assertTrue(impl.shouldIgnoreExtension());
        assertEquals(ImageExtension.PNG, impl.getImageExtension());
        assertFalse(impl.isSafeUrlMode());

        RoboHashRequestBuilderImpl impl2 = new RoboHashRequestBuilderImpl("robot", true);

        impl2.setWidth(200);
        assertEquals(200, impl2.getWidth());

        impl2.setHeight(200);
        assertEquals(200, impl2.getHeight());

        impl2.addImageSet(ImageSet.DEFAULT);
        impl2.addImageSet(ImageSet.MONSTERS);
        impl2.addImageSet(ImageSet.SEXY_ROBOTS);
        impl2.addImageSet(ImageSet.KITTENS);
        impl2.addImageSet(ImageSet.HUMANS);
        assertEquals(ImmutableList.of(
                ImageSet.DEFAULT,
                ImageSet.MONSTERS,
                ImageSet.SEXY_ROBOTS,
                ImageSet.KITTENS,
                ImageSet.HUMANS
        ), impl2.getImageSets());

        impl2.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        assertEquals(BackgroundSet.SPIRAL_AND_PATTERNS, impl2.getBackgroundSet());

        impl2.setUseGravatar(UseGravatar.HASHED);
        assertEquals(UseGravatar.HASHED, impl2.getUseGravatar());

        impl2.setIgnoreExtension(false);
        assertFalse(impl2.shouldIgnoreExtension());

        impl2.setImageExtension(ImageExtension.JPEG);
        assertEquals(ImageExtension.JPEG, impl2.getImageExtension());
    }
}
