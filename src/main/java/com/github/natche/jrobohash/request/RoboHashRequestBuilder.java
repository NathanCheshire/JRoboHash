package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.awt.*;
import java.util.Collection;

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
    @CanIgnoreReturnValue
    RoboHashRequestBuilder addImageSet(ImageSet imageSet);

    /**
     * Removes the provided image set from the sets this request can use.
     *
     * @param imageSet the image set to remove from the sets this request can use
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder removeImageSet(ImageSet imageSet);

    /**
     * Adds the provided image sets to the sets this request can use.
     *
     * @param imageSets the image sets to add to the sets this request can use
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder addImageSets(Collection<ImageSet> imageSets);

    /**
     * Removes the provided image sets from the sets this request can use.
     *
     * @param imageSets the image sets to remove from the sets this request can use
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder removeImageSets(Collection<ImageSet> imageSets);

    /**
     * Overwrites and sets the image sets this request can use.
     *
     * @param imageSets the image sets this request can use
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setImageSets(Collection<ImageSet> imageSets);

    /**
     * Removes all sets this request can use and adds the default, that of {@link ImageSet#ANY}.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetImageSets();

    /**
     * Sets the provided background set as the background set this request will use.
     *
     * @param backgroundSet the background set to use
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setBackgroundSet(BackgroundSet backgroundSet);

    /**
     * Resets the background image set to the default, that of {@link BackgroundSet#ANY}.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetBackgroundSet();

    /**
     * Sets the extension of the image this request should result in.
     *
     * @param imageExtension the image extension this request should result in
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setImageExtension(ImageExtension imageExtension);

    /**
     * Resets the image extension this request should result in, that of {@link ImageExtension#PNG}.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetImageExtension();

    /**
     * Sets whether the image extension should be ignored when hashing the text provided to RoboHash.
     * By default, this is {@code true} meaning "alive.png" and "alive.bmp" would return the same image.
     * If this is set to {@code false}, however, differing extensions would result in different images.
     *
     * @param shouldIgnoreImageExtension whether the image extension should be ignored when hashing the provided text
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setIgnoreExtension(boolean shouldIgnoreImageExtension);

    /**
     * Resets whether the image extension should be ignored when hashing
     * the provided text. The default value is {@code true}.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetIgnoreExtension();

    /**
     * Sets the use {@link UseGravatar} mode of this request.
     *
     * @param useGravatar the use {@link UseGravatar} mode
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setUseGravatar(UseGravatar useGravatar);

    /**
     * Resets the {@link UseGravatar} mode of this request by setting it to {@link UseGravatar#NO}.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetUseGravatar();

    /**
     * Sets the width of the returned image of this request.
     *
     * @param width the width of the image returned by this request
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setWidth(int width);

    /**
     * Resets the width of this request.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetWidth();

    /**
     * Sets the height of the returned image of this request.
     *
     * @param height the height of the image returned by this request
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setHeight(int height);

    /**
     * Resets the height of this request.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetHeight();

    /**
     * Sets the size of the returned image of this request.
     *
     * @param size the size of this request
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder setSize(Dimension size);

    /**
     * Resets the size of the returned image of this request.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
    RoboHashRequestBuilder resetSize();

    /**
     * Returns the avatar key this request will use.
     *
     * @return the avatar key this request will use
     */
    String getAvatarKey();

    /**
     * Returns the image sets this request will use.
     *
     * @return the image sets this request will use
     */
    Collection<ImageSet> getImageSets();

    /**
     * Returns the background set this request will use.
     *
     * @return the background set this request will use
     */
    BackgroundSet getBackgroundSet();

    /**
     * Returns the width the resulting image will be of.
     *
     * @return the width the resulting image will be of
     */
    int getWidth();

    /**
     * Returns the height the resulting image will be of.
     *
     * @return the height the resulting image will be of
     */
    int getHeight();

    /**
     * Returns the {@link UseGravatar} mode for this request.
     *
     * @return the {@link UseGravatar} mode for this request
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
     * Returns whether safe mode is enabled for when building the URL.
     *
     * @return whether safe mode is enabled for when building the URL
     */
    default boolean isSafeUrlMode() {
        return false;
    }
}
