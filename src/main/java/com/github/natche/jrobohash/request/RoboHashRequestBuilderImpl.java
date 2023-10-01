package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.github.natche.jrobohash.util.GeneralUtils;
import com.google.common.base.Preconditions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The standard, default implementation of {@link RoboHashRequestBuilder}.
 *
 * @author nathancheshire
 */
public class RoboHashRequestBuilderImpl implements RoboHashRequestBuilder {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;

    private final String avatarKey;
    private final ArrayList<ImageSet> imageSets;
    private final ArrayList<BackgroundSet> backgroundImageSets;
    private int width;
    private int height;
    private UseGravatar useGravatar;
    private boolean ignoreExtension;
    private ImageExtension imageExtension;
    private boolean safeUrlMode;

    /**
     * Constructs a new RoboHashRequestBuilderImpl object.
     *
     * @param avatarKey the key for the RoboHash avatar to be included in the url
     * @throws NullPointerException     if the provided avatarKey is null
     * @throws IllegalArgumentException if the provided avatarKey is empty
     */
    public RoboHashRequestBuilderImpl(String avatarKey) {
        this(avatarKey, true);
    }

    /**
     * Constructs a new RoboHashRequestBuilderImpl object.
     *
     * @param avatarKey   the key for the RoboHash avatar to be included in the url
     * @param safeUrlMode whether safe URL mode is enabled meaning if the provided
     *                    avatarKey contained invalid characters, they will be encoded
     * @throws NullPointerException     if the provided avatarKey is null
     * @throws IllegalArgumentException if the provided avatarKey is empty or contains
     *                                  invalid characters when safeUrlMode is false
     */
    public RoboHashRequestBuilderImpl(String avatarKey, boolean safeUrlMode) {
        Preconditions.checkNotNull(avatarKey);
        Preconditions.checkArgument(avatarKey.trim().length() != 0);
        if (!safeUrlMode) Preconditions.checkArgument(GeneralUtils.isValidUrlChars(avatarKey));

        this.avatarKey = avatarKey;
        imageSets = new ArrayList<>(List.of(ImageSet.ANY));
        backgroundImageSets = new ArrayList<>(List.of(BackgroundSet.ANY));
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        useGravatar = UseGravatar.NO;
        ignoreExtension = true;
        imageExtension = ImageExtension.PNG;
        this.safeUrlMode = safeUrlMode;
    }

    /**
     * Adds the provided image set to the sets that this request can use.
     *
     * @param imageSet the image set to add to the sets this request can use
     * @return this builder
     * @throws NullPointerException if the provided image set is null
     */
    @Override
    public RoboHashRequestBuilder addImageSet(ImageSet imageSet) {
        Preconditions.checkNotNull(imageSet);
        // Possibly returns false as specified by the Collections API, but we do not care here
        imageSets.add(imageSet);
        return this;
    }

    /**
     * Removes the provided image set from the sets this request can use.
     *
     * @param imageSet the image set to remove from the sets this request can use
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder removeImageSet(ImageSet imageSet) {
        Preconditions.checkNotNull(imageSet);
        imageSets.remove(imageSet);
        return this;
    }

    /**
     * Adds the provided image sets to the list of image sets this request can use.
     *
     * @param imageSets the image sets to add to the sets this request can use
     * @return this builder
     * @throws NullPointerException if the provided collection is null
     * @throws IllegalArgumentException if the provided collection is empty
     */
    @Override
    public RoboHashRequestBuilder addImageSets(Collection<ImageSet> imageSets) {
        Preconditions.checkNotNull(imageSets);
        Preconditions.checkArgument(!imageSets.isEmpty());
        this.imageSets.addAll(imageSets);
        return this;
    }

    /**
     * Removes the provided image sets from the list of image sets this request can use.
     *
     * @param imageSets the image sets to remove from the sets this request can use
     * @return this builder
     * @throws NullPointerException if the provided collection is null
     * @throws IllegalArgumentException if the provided collection is empty
     */
    @Override
    public RoboHashRequestBuilder removeImageSets(Collection<ImageSet> imageSets) {
        Preconditions.checkNotNull(imageSets);
        Preconditions.checkArgument(!imageSets.isEmpty());
        this.imageSets.removeAll(imageSets);
        return this;
    }

    /**
     * Sets the image sets to be used to the provided sets.
     *
     * @param imageSets the image sets this request can use
     * @return this builder
     * @throws NullPointerException if the provided sets is null
     * @throws IllegalArgumentException if the provided sets is empty
     */
    @Override
    public RoboHashRequestBuilder setImageSets(Collection<ImageSet> imageSets) {
        Preconditions.checkNotNull(imageSets);
        Preconditions.checkArgument(!imageSets.isEmpty());
        this.imageSets.clear();
        this.imageSets.addAll(imageSets);
        return this;
    }

    /**
     * Clears the image sets to be used by this request and
     * resets to the default, that of {@link ImageSet#ANY}
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder clearImageSets() {
        imageSets.clear();
        imageSets.add(ImageSet.ANY);
        return this;
    }

    /**
     * Adds the provided background set to the background sets this request can use.
     *
     * @param backgroundSet the background set to add
     * @return this builder
     * @throws NullPointerException if the provided background set is null
     */
    @Override
    public RoboHashRequestBuilder addBackgroundSet(BackgroundSet backgroundSet) {
        Preconditions.checkNotNull(backgroundSet);
        backgroundImageSets.add(backgroundSet);
        return this;
    }

    /**
     * Removes the provided background set from the background sets this request can use.
     *
     * @param backgroundSet the background set to remove
     * @return this builder
     * @throws NullPointerException if the provided background set is null
     */
    @Override
    public RoboHashRequestBuilder removeBackgroundSet(BackgroundSet backgroundSet) {
        Preconditions.checkNotNull(backgroundSet);
        backgroundImageSets.remove(backgroundSet);
        return this;
    }

    /**
     * Clears the background image sets this request can use and reset to the default of {@link BackgroundSet#ANY}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder clearBackgroundSets() {
        backgroundImageSets.clear();
        backgroundImageSets.add(BackgroundSet.ANY);
        return this;
    }

    /**
     * Sets the image extension of this request.
     *
     * @param imageExtension the image extension
     * @return this builder
     * @throws NullPointerException if the provided image extension is null
     */
    @Override
    public RoboHashRequestBuilder setImageExtension(ImageExtension imageExtension) {
        Preconditions.checkNotNull(imageExtension);
        this.imageExtension = imageExtension;
        return this;
    }

    /**
     * Resets the image extension to the default of {@link ImageExtension#PNG}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetImageExtension() {
        this.imageExtension = ImageExtension.PNG;
        return this;
    }

    /**
     * Sets whether the image extension should be ignored when hashing the provided text
     *
     * @param shouldIgnoreImageExtension whether the image extension should be ignored when hashing the provided text
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder setIgnoreExtension(boolean shouldIgnoreImageExtension) {
        this.ignoreExtension = shouldIgnoreImageExtension;
        return this;
    }

    /**
     * Sets the state of ignore extension to true.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetIgnoreExtension() {
        return this;
    }

    /**
     * Sets the use Gravatar mode for this request
     *
     * @param useGravatar the use Gravatar mode
     * @return this builder
     * @throws NullPointerException if the provided Gravatar mode is null
     */
    @Override
    public RoboHashRequestBuilder setUseGravatar(UseGravatar useGravatar) {
        Preconditions.checkNotNull(useGravatar);
        this.useGravatar = useGravatar;
        return this;
    }

    /**
     * Resets the use Gravatar mode of this request to {@link UseGravatar#NO}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetUseGravatar() {
        this.useGravatar = UseGravatar.NO;
        return this;
    }

    /**
     * Sets the width of the image returned by this request.
     *
     * @param width the width of the image returned by this request
     * @return this builder
     * @throws IllegalArgumentException if the provided width is less than 0
     */
    @Override
    public RoboHashRequestBuilder setWidth(int width) {
        Preconditions.checkArgument(width >= 0);
        this.width = width;
        return this;
    }

    /**
     * Resets the width of the image returned by this request to {@link #DEFAULT_WIDTH}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetWidth() {
        width = DEFAULT_WIDTH;
        return this;
    }

    /**
     * Sets the height of the image returned by this request.
     *
     * @param height the height of the image returned by this request
     * @return this builder
     * @throws IllegalArgumentException if the provided height is less than 0
     */
    @Override
    public RoboHashRequestBuilder setHeight(int height) {
        Preconditions.checkArgument(height >= 0);
        this.height = height;
        return this;
    }

    /**
     * Resets the height of the image returned by this request to {@link #DEFAULT_HEIGHT}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetHeight() {
        this.height = DEFAULT_HEIGHT;
        return this;
    }

    @Override
    public RoboHashRequestBuilder setSize(Dimension size) {
        return this;
    }

    /**
     * Resets the size of the image returned by this request to {@link #DEFAULT_WIDTH} x {@link #DEFAULT_HEIGHT}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetSize() {
        resetWidth();
        resetHeight();
        return this;
    }

    /**
     * Enables URL mode meaning any unsafe URL characters in the provided avatarKey
     * will be encoded before passing to the RoboHash server.
     *
     * @return this builder
     */
    public RoboHashRequestBuilder enableSafeUrlMode() {
        safeUrlMode = true;
        return this;
    }

    /**
     * Disables URL mode meaning any unsafe URL characters in the provided avatarKey
     * will NOT be encoded before passing to the RoboHash server.
     *
     * @return this builder
     */
    public RoboHashRequestBuilder disableSafeUrlMode() {
        safeUrlMode = false;
        return this;
    }

    @Override
    public String buildRequestUrl() {
        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}
