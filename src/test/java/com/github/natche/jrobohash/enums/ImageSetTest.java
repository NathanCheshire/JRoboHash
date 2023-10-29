package com.github.natche.jrobohash.enums;

import com.github.natche.jrobohash.exceptions.JRoboHashException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link ImageSet}s supported by RoboHash.
 */
public class ImageSetTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    ImageSetTest() {}

    /**
     * Tests for the get URL parameter name method.
     */
    @Test
    void testGetUrlParameterName() {
        assertEquals("set1", ImageSet.DEFAULT.getUrlParameterName());
        assertEquals("set2", ImageSet.MONSTERS.getUrlParameterName());
        assertEquals("set3", ImageSet.SEXY_ROBOTS.getUrlParameterName());
        assertEquals("set4", ImageSet.KITTENS.getUrlParameterName());
        assertEquals("set5", ImageSet.HUMANS.getUrlParameterName());
        assertEquals("any", ImageSet.ANY.getUrlParameterName());
    }

    /**
     * Tests for the construct URL parameter method.
     */
    @Test
    void testConstructUrlParameter() {
        assertEquals("&set=set1", ImageSet.DEFAULT.constructUrlParameter(false));
        assertEquals("&set=set2", ImageSet.MONSTERS.constructUrlParameter(false));
        assertEquals("&set=set3", ImageSet.SEXY_ROBOTS.constructUrlParameter(false));
        assertEquals("&set=set4", ImageSet.KITTENS.constructUrlParameter(false));
        assertEquals("&set=set5", ImageSet.HUMANS.constructUrlParameter(false));
        assertEquals("&set=any", ImageSet.ANY.constructUrlParameter(false));

        assertEquals("?set=set1", ImageSet.DEFAULT.constructUrlParameter(true));
        assertEquals("?set=set2", ImageSet.MONSTERS.constructUrlParameter(true));
        assertEquals("?set=set3", ImageSet.SEXY_ROBOTS.constructUrlParameter(true));
        assertEquals("?set=set4", ImageSet.KITTENS.constructUrlParameter(true));
        assertEquals("?set=set5", ImageSet.HUMANS.constructUrlParameter(true));
        assertEquals("?set=any", ImageSet.ANY.constructUrlParameter(true));
    }

    @Test
    void testGetListUrlParameterName() {
        assertEquals("1", ImageSet.DEFAULT.getListUrlParameterName());
        assertEquals("2", ImageSet.MONSTERS.getListUrlParameterName());
        assertEquals("3", ImageSet.SEXY_ROBOTS.getListUrlParameterName());
        assertEquals("4", ImageSet.KITTENS.getListUrlParameterName());
        assertEquals("5", ImageSet.HUMANS.getListUrlParameterName());
        assertThrows(JRoboHashException.class, ImageSet.ANY::getListUrlParameterName);
    }
}
