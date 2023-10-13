package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link RoboHashRequestHandler}.
 */
public class RoboHashRequestHandlerTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    RoboHashRequestHandlerTest() {}

    /**
     * Tests for for the build request url method.
     */
    @Test
    void testBuildRequestUrl() {
        RoboHashRequestBuilderImpl builder = new RoboHashRequestBuilderImpl("2bf1b7a19bcad06a8e894d7373a4cfc7");
        builder.setSize(new Dimension(500, 500));
        builder.setUseGravatar(UseGravatar.HASHED);
        builder.addImageSet(ImageSet.HUMANS);
        builder.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        builder.setImageExtension(ImageExtension.JPEG);
        assertEquals("https://robohash.org/2bf1b7a19bcad06a8e894d7373a4cfc7.jpeg"
                + "?set=set5&bgset=bg2&size=500x500&gravatar=hashed",
                RoboHashRequestHandler.buildRequestUrl(builder));

        RoboHashRequestBuilderImpl builder2 = new RoboHashRequestBuilderImpl("2bf1b7a19bcad06a8e894d7373a4cfc7");
        builder2.setSize(new Dimension(500, 500));
        builder2.setUseGravatar(UseGravatar.HASHED);
        builder2.addImageSet(ImageSet.HUMANS);
        builder2.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        builder2.setImageExtension(ImageExtension.JPEG);
        builder2.setIgnoreExtension(false);
        assertEquals("https://robohash.org/2bf1b7a19bcad06a8e894d7373a4cfc7.jpeg"
                + "?set=set5&bgset=bg2&size=500x500&gravatar=hashed&ignoreext=false",
                RoboHashRequestHandler.buildRequestUrl(builder2));
    }
}
