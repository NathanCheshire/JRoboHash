package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.Set;
import com.github.natche.jrobohash.enums.UseGravatar;

import java.awt.*;
import java.util.Collection;

/**
 * The interface from which all implementations of a RoboHash request builder must inherit from.
 */
public interface RoboHashRequestBuilder {
    /**
     * Adds the provided image set to the sets this request can use.
     *
     * @param set the image set to add to the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder addImageSet(Set set);

    /**
     * Removes the provided image set from the sets this request can use.
     *
     * @param set the image set to remove from the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder removeImageSet(Set set);

    /**
     * Adds the provided image sets to the sets this request can use.
     *
     * @param sets the image sets to add to the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder addImageSets(Collection<Set> sets);

    /**
     * Removes the provided image sets from the sets this request can use.
     *
     * @param sets the image sets to remove from the sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder removeImageSets(Collection<Set> sets);

    /**
     * Sets the image sets this request can use.
     *
     * @param sets the image sets this request can use
     * @return this builder
     */
    RoboHashRequestBuilder setImageSets(Collection<Set> sets);

    /**
     * Removes all sets this request can use and sets the default, that of {@link Set#ANY}.
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
     * Builds and returns the request URL based on the current state of this builder.
     *
     * @return the built url
     */
    String buildRequestUrl();

    /**
     * Builds the URL based on the current state of this builder and reads and returned the image from the URL.
     *
     * @return the {@link Image} read from the URL
     */
    Image getImage();
}
