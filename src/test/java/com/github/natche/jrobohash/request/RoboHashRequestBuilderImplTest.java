package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
        RoboHashRequestBuilderImpl builderOne = new RoboHashRequestBuilderImpl("robot", false);
        assertEquals("robot", builderOne.getAvatarKey());
        assertFalse(builderOne.isSafeUrlMode());
        assertEquals(ImmutableList.of(ImageSet.ANY), builderOne.getImageSets());
        assertEquals(BackgroundSet.ANY, builderOne.getBackgroundSet());
        assertEquals(300, builderOne.getWidth());
        assertEquals(300, builderOne.getHeight());
        assertEquals(UseGravatar.NO, builderOne.getUseGravatar());
        assertTrue(builderOne.shouldIgnoreExtension());
        assertEquals(ImageExtension.PNG, builderOne.getImageExtension());
        assertFalse(builderOne.isSafeUrlMode());

        RoboHashRequestBuilderImpl builderTwo = new RoboHashRequestBuilderImpl("robot", true);

        builderTwo.setWidth(200);
        assertEquals(200, builderTwo.getWidth());

        builderTwo.setHeight(200);
        assertEquals(200, builderTwo.getHeight());

        builderTwo.addImageSet(ImageSet.DEFAULT);
        builderTwo.addImageSet(ImageSet.MONSTERS);
        builderTwo.addImageSet(ImageSet.SEXY_ROBOTS);
        builderTwo.addImageSet(ImageSet.KITTENS);
        builderTwo.addImageSet(ImageSet.HUMANS);
        assertEquals(ImmutableList.of(
                ImageSet.DEFAULT,
                ImageSet.MONSTERS,
                ImageSet.SEXY_ROBOTS,
                ImageSet.KITTENS,
                ImageSet.HUMANS
        ), builderTwo.getImageSets());

        builderTwo.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        assertEquals(BackgroundSet.SPIRAL_AND_PATTERNS, builderTwo.getBackgroundSet());

        builderTwo.setUseGravatar(UseGravatar.HASHED);
        assertEquals(UseGravatar.HASHED, builderTwo.getUseGravatar());

        builderTwo.setIgnoreExtension(false);
        assertFalse(builderTwo.shouldIgnoreExtension());

        builderTwo.setImageExtension(ImageExtension.JPEG);
        assertEquals(ImageExtension.JPEG, builderTwo.getImageExtension());
    }

    /**
     * Tests for the mutator methods which reset states.
     */
    @Test
    void testResettingMutators() {
        RoboHashRequestBuilderImpl implementationOne = new RoboHashRequestBuilderImpl("key");

        implementationOne.addImageSet(ImageSet.HUMANS);
        assertEquals(ImmutableList.of(ImageSet.HUMANS), implementationOne.getImageSets());
        implementationOne.resetImageSets();
        assertEquals(ImmutableList.of(ImageSet.ANY), implementationOne.getImageSets());

        implementationOne.setBackgroundSet(BackgroundSet.OUTSIDE);
        assertEquals(BackgroundSet.OUTSIDE, implementationOne.getBackgroundSet());
        implementationOne.resetBackgroundSet();
        assertEquals(BackgroundSet.ANY, implementationOne.getBackgroundSet());

        implementationOne.setImageExtension(ImageExtension.BITMAP);
        assertEquals(ImageExtension.BITMAP, implementationOne.getImageExtension());
        implementationOne.resetImageExtension();
        assertEquals(ImageExtension.PNG, implementationOne.getImageExtension());

        implementationOne.setIgnoreExtension(true);
        assertTrue(implementationOne.shouldIgnoreExtension());
        implementationOne.resetIgnoreExtension();
        assertTrue(implementationOne.shouldIgnoreExtension());

        implementationOne.setUseGravatar(UseGravatar.HASHED);
        assertEquals(UseGravatar.HASHED, implementationOne.getUseGravatar());
        implementationOne.resetUseGravatar();
        assertEquals(UseGravatar.NO, implementationOne.getUseGravatar());

        implementationOne.setWidth(5150);
        assertEquals(5150, implementationOne.getWidth());
        implementationOne.resetWidth();
        assertEquals(RoboHashRequestBuilderImpl.DEFAULT_WIDTH, implementationOne.getWidth());

        implementationOne.setHeight(5150);
        assertEquals(5150, implementationOne.getHeight());
        implementationOne.resetHeight();
        assertEquals(RoboHashRequestBuilderImpl.DEFAULT_HEIGHT, implementationOne.getHeight());

        implementationOne.setSize(new Dimension(5150, 5150));
        assertEquals(5150, implementationOne.getWidth());
        assertEquals(5150, implementationOne.getHeight());
        implementationOne.resetSize();
        assertEquals(RoboHashRequestBuilderImpl.DEFAULT_WIDTH, implementationOne.getWidth());
        assertEquals(RoboHashRequestBuilderImpl.DEFAULT_HEIGHT, implementationOne.getHeight());
    }

    /**
     * Tests for the to string method.
     */
    @Test
    void testToString() {
        RoboHashRequestBuilderImpl implementationOne = new RoboHashRequestBuilderImpl("key");
        RoboHashRequestBuilderImpl differentOne = new RoboHashRequestBuilderImpl("other key");

        assertEquals("RoboHashRequestBuilderImpl{avatarKey=\"key\", imageSets=[ANY],"
                + " backgroundImageSet=ANY, width=300, height=300, useGravatar=NO,"
                + " ignoreExtension=true, imageExtension=PNG, safeUrlMode=true}",
                implementationOne.toString());
        assertEquals("RoboHashRequestBuilderImpl{avatarKey=\"other key\","
                + " imageSets=[ANY], backgroundImageSet=ANY, width=300, height=300,"
                + " useGravatar=NO, ignoreExtension=true, imageExtension=PNG, safeUrlMode=true}",
                differentOne.toString());
    }

    /**
     * Tests for the equals method to ensure an equivalence relation is maintained.
     */
    @Test
    void testEquals() {
        RoboHashRequestBuilderImpl implementationOne = new RoboHashRequestBuilderImpl("key");
        RoboHashRequestBuilderImpl implementationOneEqual = new RoboHashRequestBuilderImpl("key");
        RoboHashRequestBuilderImpl differentOne = new RoboHashRequestBuilderImpl("other key");

        assertEquals(implementationOne, implementationOne);
        assertEquals(implementationOne, implementationOneEqual);
        assertNotEquals(implementationOne, differentOne);
        assertNotEquals(implementationOne, new Object());
    }

    /**
     * Tests for the hashcode method to ensure an equivalence relation is maintained.
     */
    @Test
    void testHashCode() {
        RoboHashRequestBuilderImpl implementationOne = new RoboHashRequestBuilderImpl("key");
        RoboHashRequestBuilderImpl implementationOneEqual = new RoboHashRequestBuilderImpl("key");
        RoboHashRequestBuilderImpl differentOne = new RoboHashRequestBuilderImpl("other key");

        assertEquals(implementationOne.hashCode(), implementationOne.hashCode());
        assertEquals(implementationOne.hashCode(), implementationOneEqual.hashCode());
        assertNotEquals(implementationOne.hashCode(), differentOne.hashCode());
        assertNotEquals(implementationOne.hashCode(), new Object().hashCode());
    }
}
