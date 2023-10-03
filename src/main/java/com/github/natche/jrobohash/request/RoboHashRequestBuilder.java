package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;

import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The interface from which all implementations of a RoboHash request builder must inherit from.
 */
public interface RoboHashRequestBuilder {
    /**
     * Adds the provided image set to the sets this request can use.
     *
     * @param imageSet the image set to add to the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder addImageSet(ImageSet imageSet);

    /**
     * Removes the provided image set from the sets this request can use.
     *
     * @param imageSet the image set to remove from the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder removeImageSet(ImageSet imageSet);

    /**
     * Adds the provided image sets to the sets this request can use.
     *
     * @param imageSets the image sets to add to the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder addImageSets(Collection<ImageSet> imageSets);

    /**
     * Removes the provided image sets from the sets this request can use.
     *
     * @param imageSets the image sets to remove from the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder removeImageSets(Collection<ImageSet> imageSets);

    /**
     * Sets the image sets this request can use.
     *
     * @param imageSets the image sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder setImageSets(Collection<ImageSet> imageSets);

    /**
     * Removes all sets this request can use and sets the default, that of {@link ImageSet#ANY}.
     *
     * @return this builder
     */
    RoboHashRequestBuilder clearImageSets();

    /**
     * Adds the provided background set to the list of sets this request can use.
     *
     * @param backgroundSet the background set to add
     * @return this builder
     */
    RoboHashRequestBuilder addBackgroundSet(BackgroundSet backgroundSet);

    /**
     * Removes the provided background set from the list of sets this request can use.
     *
     * @param backgroundSet the background set to remove
     * @return this builder
     */
    RoboHashRequestBuilder removeBackgroundSet(BackgroundSet backgroundSet);

    /**
     * Removes all background sets this request can use and sets the default, that of {@link BackgroundSet#ANY}.
     *
     * @return this builder
     */
    RoboHashRequestBuilder clearBackgroundSets();

    /**
     * Sets the extension this request should return.
     *
     * @param imageExtension the image extension
     * @return this builder
     */
    RoboHashRequestBuilder setImageExtension(ImageExtension imageExtension);

    /**
     * Resets the image extension this request should use, that of {@link ImageExtension#PNG}.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetImageExtension();

    /**
     * Sets whether the image extension should be ignored when hashing the text provided to RoboHash.
     * By default, this is true meaning "alive.png" and "alive.bmp" would return the same image.
     * If this is set to false, however, these would return different images.
     *
     * @param shouldIgnoreImageExtension whether the image extension should be ignored when hashing the provided text
     * @return this builder
     */
    RoboHashRequestBuilder setIgnoreExtension(boolean shouldIgnoreImageExtension);

    /**
     * Resets whether the image extension should be ignored when hashing the provided text, meaning it is set to true.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetIgnoreExtension();

    /**
     * Sets the use Gravatar mode of this request.
     *
     * @param useGravatar the use Gravatar mode
     * @return this builder
     */
    RoboHashRequestBuilder setUseGravatar(UseGravatar useGravatar);

    /**
     * Resets the use Gravatar mode of this request by setting it to {@link UseGravatar#NO}.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetUseGravatar();

    /**
     * Sets the width of the returned image of this request.
     *
     * @param width the width of the image returned by this request
     * @return this builder
     */
    RoboHashRequestBuilder setWidth(int width);

    /**
     * Resets the width of this request.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetWidth();

    /**
     * Sets the height of the returned image of this request.
     *
     * @param height the height of the image returned by this request
     * @return this builder
     */
    RoboHashRequestBuilder setHeight(int height);

    /**
     * Resets the height of this request.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetHeight();

    /**
     * Sets the size of the returned image of this request.
     *
     * @param size the size of this request
     * @return this builder
     */
    RoboHashRequestBuilder setSize(Dimension size);

    /**
     * Resets the size of the returned image of this request.
     *
     * @return this builder
     */
    RoboHashRequestBuilder resetSize();

    /**
     * Returns the avatar key this request will use.
     *
     * @return the avatar key this request will use
     */
    String getAvatarKey();

    /**
     * Returns the image sets this request can use.
     *
     * @return the image sets this request can use
     */
    Collection<ImageSet> getImageSets();

    /**
     * Returns the background sets this request can use.
     *
     * @return the background sets this request can use
     */
    Collection<BackgroundSet> getBackgroundSets();

    /**
     * Returns the width the resulting image should be of.
     *
     * @return the width the resulting image should be of
     */
    int getWidth();

    /**
     * Returns the height the resulting image should be of.
     *
     * @return the height the resulting image should be of
     */
    int getHeight();

    /**
     * Returns the Gravatar mode for this request.
     *
     * @return the Gravatar mode for this request
     */
    UseGravatar getUseGravatar();

    /**
     * Returns whether the image extension should be ignored when constructing
     * the RoboHash avatar based on the avatar key.
     *
     * @return whether the image extension should be ignored when constructing
     * the RoboHash avatar based on the avatar key
     */
    boolean shouldIgnoreExtension();

    /**
     * Returns the image extension to use for this request.
     *
     * @return the image extension to use for this request
     */
    ImageExtension getImageExtension();

    /**
     * Returns whether safe mode is enabled when building the url.
     *
     * @return whether safe mode is enabled when building the url
     */
    default boolean isSafeUrlMode() {
        return false;
    }
}
