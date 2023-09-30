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
    private final ArrayList<ImageSet> backgroundImageSets;
    private int width;
    private int height;
    private UseGravatar useGravatar;
    private boolean ignoreExtension;
    private ImageExtension imageExtension;
    private final boolean safeUrlMode;

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
        imageSets = new ArrayList<>();
        backgroundImageSets = new ArrayList<>();
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

    @Override
    public RoboHashRequestBuilder removeImageSets(Collection<ImageSet> imageSets) {
        return this;
    }

    @Override
    public RoboHashRequestBuilder setImageSets(Collection<ImageSet> imageSets) {
        return this;
    }

    @Override
    public RoboHashRequestBuilder clearImageSets() {
        return this;
    }

    @Override
    public RoboHashRequestBuilder addBackgroundSet(BackgroundSet backgroundSet) {
        return this;
    }

    @Override
    public RoboHashRequestBuilder removeBackgroundSet(BackgroundSet backgroundSet) {
        return this;
    }

    @Override
    public RoboHashRequestBuilder clearBackgroundSets() {
        return this;
    }

    @Override
    public RoboHashRequestBuilder setImageExtension(ImageExtension imageExtension) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetImageExtension() {
        return null;
    }

    @Override
    public RoboHashRequestBuilder setIgnoreExtension(boolean shouldIgnoreImageExtension) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetIgnoreExtension() {
        return null;
    }

    @Override
    public RoboHashRequestBuilder setUseGravatar(UseGravatar useGravatar) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetUseGravatar() {
        return null;
    }

    @Override public RoboHashRequestBuilder setWidth(int width) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetWidth() {
        return null;
    }

    @Override public RoboHashRequestBuilder setHeight(int height) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetHeight() {
        return null;
    }

    @Override
    public RoboHashRequestBuilder setSize(Dimension size) {
        return null;
    }

    @Override
    public RoboHashRequestBuilder resetSize() {
        return null;
    }

    public RoboHashRequestBuilder enableSafeUrlMode() {
        safeUrlMode = true;
        return this;
    }

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
