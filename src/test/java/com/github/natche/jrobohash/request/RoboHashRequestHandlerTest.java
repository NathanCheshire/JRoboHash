package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.github.natche.jrobohash.exceptions.JRoboHashException;
import com.google.common.base.Preconditions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link RoboHashRequestHandler}.
 */
@SuppressWarnings("SpellCheckingInspection") /* MD5 digests */
public class RoboHashRequestHandlerTest {
    private final RoboHashRequestBuilderImpl builder = new RoboHashRequestBuilderImpl("2bf1b7a19bcad06a8e894d7373a4cfc7");
    private final RoboHashRequestBuilderImpl builder2 = new RoboHashRequestBuilderImpl("2bf1b7a19bcad06a8e894d7373a4cfc7");
    private final RoboHashRequestBuilderImpl builder3 = new RoboHashRequestBuilderImpl("nathan-cheshire");
    private final RoboHashRequestBuilderImpl builder4 = new RoboHashRequestBuilderImpl("nathan-v-cheshire");
    private final RoboHashRequestBuilderImpl builder5 = new RoboHashRequestBuilderImpl("minimal");


    /**
     * Constructs a new instance of this class for testing purposes.
     */
    RoboHashRequestHandlerTest() {}

    @BeforeEach
    void setup() {
        builder.setSize(new Dimension(500, 500));
        builder.setUseGravatar(UseGravatar.HASHED);
        builder.addImageSet(ImageSet.HUMANS);
        builder.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        builder.setImageExtension(ImageExtension.JPEG);

        builder2.setSize(new Dimension(500, 500));
        builder2.setUseGravatar(UseGravatar.HASHED);
        builder2.addImageSet(ImageSet.HUMANS);
        builder2.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        builder2.setImageExtension(ImageExtension.JPEG);
        builder2.setIgnoreExtension(false);

        builder3.setSize(new Dimension(600, 600));
        builder3.addImageSet(ImageSet.MONSTERS);
        builder3.setBackgroundSet(BackgroundSet.OUTSIDE);
        builder3.setImageExtension(ImageExtension.JPG);

        builder4.setSize(new Dimension(701, 701));
        builder4.addImageSet(ImageSet.MONSTERS);
        builder4.addImageSet(ImageSet.HUMANS);
        builder4.setBackgroundSet(BackgroundSet.SPIRAL_AND_PATTERNS);
        builder4.setImageExtension(ImageExtension.PNG);
    }

    /**
     * Tests for construction, attempted construction that is, of a request handler.
     */
    @Test
    void testConstruction() {
        try {
            Constructor<RoboHashRequestHandler> constructor =
                    RoboHashRequestHandler.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            assertTrue(e instanceof InvocationTargetException);
            Throwable target = ((InvocationTargetException) e).getTargetException();
            assertTrue(target instanceof AssertionError);
            assertEquals("Cannot create instances of RobohashRequestHandler", target.getMessage());
        }
    }

    /**
     * Tests for the build request URL method.
     */
    @Test
    void testBuildRequestUrl() {
        assertThrows(NullPointerException.class, ()-> RoboHashRequestHandler.buildRequestUrl(null));

        assertEquals("https://robohash.org/2bf1b7a19bcad06a8e894d7373a4cfc7.jpeg"
                + "?set=set5&bgset=bg2&size=500x500&gravatar=hashed",
                RoboHashRequestHandler.buildRequestUrl(builder));

        assertEquals("https://robohash.org/2bf1b7a19bcad06a8e894d7373a4cfc7.jpeg"
                + "?set=set5&bgset=bg2&size=500x500&gravatar=hashed&ignoreext=false",
                RoboHashRequestHandler.buildRequestUrl(builder2));

        assertEquals("https://robohash.org/nathan-cheshire.jpg?set=set2&bgset=bg1&size=600x600",
                RoboHashRequestHandler.buildRequestUrl(builder3));

        assertEquals("https://robohash.org/nathan-v-cheshire.png?sets=2,5&bgset=bg2&size=701x701",
                RoboHashRequestHandler.buildRequestUrl(builder4));

        assertEquals("https://robohash.org/minimal.png?set=any&bgset=any&size=300x300",
                RoboHashRequestHandler.buildRequestUrl(builder5));
    }

    /**
     * Tests for the get image method.
     */
    @Test
    void testGetImage() {
        // this is transitively tested by the below saveToFile test
    }

    private boolean fileImagesEqual(File imageFile, File otherImageFile) {
        Preconditions.checkNotNull(imageFile);
        Preconditions.checkNotNull(otherImageFile);
        Preconditions.checkArgument(imageFile.exists());
        Preconditions.checkArgument(otherImageFile.exists());

        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            BufferedImage otherBufferedImage = ImageIO.read(otherImageFile);

            if (bufferedImage.getWidth() != otherBufferedImage.getWidth()
                    || bufferedImage.getHeight() != otherBufferedImage.getHeight()) return false;

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    if (otherBufferedImage.getRGB(x, y) != otherBufferedImage.getRGB(x, y)) {
                        return false;
                    }
                }
            }

            return true;
        } catch (IOException e) {
            throw new JRoboHashException(e);
        }
    }

    /**
     * Tests for the save to file method.
     */
    @Test
    void testSaveToFile() {
        assertThrows(NullPointerException.class, () -> RoboHashRequestHandler.saveToFile(null, null));
        assertThrows(NullPointerException.class, () -> RoboHashRequestHandler.saveToFile(builder, null));
        assertThrows(IllegalArgumentException.class, () -> RoboHashRequestHandler.saveToFile(builder, new File(".")));

        // all of this is for the fourth precondition, that of testing the file does not already exist
        File tmpDir = new File("tmp");
        //noinspection ResultOfMethodCallIgnored
        tmpDir.mkdir();
        assertTrue(tmpDir.exists());
        File existingFile = new File("tmp/existing.png");
        try {
            //noinspection ResultOfMethodCallIgnored
            existingFile.createNewFile();
        } catch (IOException e) {
            throw new JRoboHashException(e.getMessage());
        }
        assertTrue(existingFile.exists());
        assertThrows(IllegalArgumentException.class, () -> RoboHashRequestHandler.saveToFile(builder, existingFile));
        assertTrue(existingFile.delete());
        assertTrue(tmpDir.delete());

        //noinspection ResultOfMethodCallIgnored
        tmpDir.mkdir();
        assertTrue(tmpDir.exists());

        File builder1File = new File("tmp/builder" + builder.getImageExtension().getExtensionWithPeriod());
        assertDoesNotThrow(() -> RoboHashRequestHandler.saveToFile(builder, builder1File));
        assertTrue(builder1File.exists());
        File builder1ComparisonFile = new File("./src/test/java/com/github/natche/jrobohash/images/builder.jpeg");
        assertTrue(builder1ComparisonFile.exists());
        assertTrue(fileImagesEqual(builder1File, builder1ComparisonFile));
        assertTrue(builder1File.delete());

        File builder2File = new File("tmp/builder" + builder2.getImageExtension().getExtensionWithPeriod());
        assertDoesNotThrow(() -> RoboHashRequestHandler.saveToFile(builder2, builder2File));
        assertTrue(builder2File.exists());
        File builder2ComparisonFile = new File("./src/test/java/com/github/natche/jrobohash/images/builder2.jpeg");
        assertTrue(builder2ComparisonFile.exists());
        assertTrue(fileImagesEqual(builder2File, builder2ComparisonFile));
        assertTrue(builder2File.delete());

        File builder3File = new File("tmp/builder" + builder3.getImageExtension().getExtensionWithPeriod());
        assertDoesNotThrow(() -> RoboHashRequestHandler.saveToFile(builder3, builder3File));
        assertTrue(builder3File.exists());
        File builder3ComparisonFile = new File("./src/test/java/com/github/natche/jrobohash/images/builder3.jpg");
        assertTrue(builder3ComparisonFile.exists());
        assertTrue(fileImagesEqual(builder3File, builder3ComparisonFile));
        assertTrue(builder3File.delete());

        File builder4File = new File("tmp/builder" + builder4.getImageExtension().getExtensionWithPeriod());
        assertDoesNotThrow(() -> RoboHashRequestHandler.saveToFile(builder4, builder4File));
        assertTrue(builder4File.exists());
        File builder4ComparisonFile = new File("./src/test/java/com/github/natche/jrobohash/images/builder4.png");
        assertTrue(builder4ComparisonFile.exists());
        assertTrue(fileImagesEqual(builder4File, builder4ComparisonFile));
        assertTrue(builder4File.delete());

        File builder5File = new File("tmp/builder" + builder5.getImageExtension().getExtensionWithPeriod());
        assertDoesNotThrow(() -> RoboHashRequestHandler.saveToFile(builder5, builder5File));
        assertTrue(builder5File.exists());
        File builder5ComparisonFile = new File("./src/test/java/com/github/natche/jrobohash/images/builder5.png");
        assertTrue(builder5ComparisonFile.exists());
        assertTrue(fileImagesEqual(builder5File, builder5ComparisonFile));
        assertTrue(builder5File.delete());

        assertTrue(tmpDir.delete());
    }
}
