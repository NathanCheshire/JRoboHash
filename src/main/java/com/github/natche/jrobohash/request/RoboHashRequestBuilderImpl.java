package com.github.natche.jrobohash.request;

import com.github.natche.jrobohash.enums.BackgroundSet;
import com.github.natche.jrobohash.enums.ImageExtension;
import com.github.natche.jrobohash.enums.ImageSet;
import com.github.natche.jrobohash.enums.UseGravatar;
import com.github.natche.jrobohash.util.GeneralUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The standard, default implementation of {@link RoboHashRequestBuilder}.
 *
 * @author nathancheshire
 * @since 1.0
 */
public class RoboHashRequestBuilderImpl implements RoboHashRequestBuilder {
    /**
     * The default width for a RoboHash avatar image.
     */
    public static final int DEFAULT_WIDTH = 300;

    /**
     * The default height for a RoboHash avatar image.
     */
    public static final int DEFAULT_HEIGHT = 300;

    /**
     * The avatar key for this RoboHash avatar image.
     */
    private final String avatarKey;

    /**
     * The image sets this request can use.
     */
    private final ArrayList<ImageSet> imageSets;

    /**
     * The background image sets this request can use.
     */
    private BackgroundSet backgroundImageSet;

    /**
     * The width of this request.
     */
    private int width;

    /**
     * The height of this request.
     */
    private int height;

    /**
     * The Gravatar mode of this request.
     */
    private UseGravatar useGravatar;

    /**
     * Whether the image extension should be ignored when computing the avatar for this request.
     */
    private boolean ignoreExtension;

    /**
     * The image extension for this request.
     */
    private ImageExtension imageExtension;

    /**
     * Whether safe URL mode should be enabled meaning if the avatar key
     * contains invalid URL characters, they will be encoded.
     */
    private boolean safeUrlMode;

    /**
     * Constructs a new RoboHashRequestBuilderImpl object.
     *
     * @param avatarKey the key for the RoboHash avatar to be included in the URL
     * @throws NullPointerException     if the provided avatarKey is null
     * @throws IllegalArgumentException if the provided avatarKey is empty
     */
    public RoboHashRequestBuilderImpl(String avatarKey) {
        this(avatarKey, true);
    }

    /**
     * Constructs a new RoboHashRequestBuilderImpl object.
     *
     * @param avatarKey   the key for the RoboHash avatar to be included in the URL
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
        backgroundImageSet = BackgroundSet.ANY;
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

        imageSets.add(imageSet);
        if (imageSet != ImageSet.ANY) imageSets.remove(ImageSet.ANY);
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
     * @throws NullPointerException     if the provided collection is null
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
     * @throws NullPointerException     if the provided collection is null
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
     * @throws NullPointerException     if the provided sets is null
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
     * Resets the image sets to be used by this request and
     * resets to the default, that of {@link ImageSet#ANY}
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetImageSets() {
        imageSets.clear();
        imageSets.add(ImageSet.ANY);
        return this;
    }

    /**
     * Sets the provided background as the background set this request can use.
     *
     * @param backgroundSet the background set to use
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder setBackgroundSet(BackgroundSet backgroundSet) {
        Preconditions.checkNotNull(backgroundSet);

        this.backgroundImageSet = backgroundSet;
        return this;
    }

    /**
     * Resets the background image set to the default, that of {@link BackgroundSet#ANY}.
     *
     * @return this builder
     */
    @Override
    public RoboHashRequestBuilder resetBackgroundSet() {
        backgroundImageSet = BackgroundSet.ANY;
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
        ignoreExtension = true;
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

    /**
     * Sets the width and height of the image returned by this request to the provided size.
     *
     * @param size the size of this request
     * @return this builder
     * @throws NullPointerException     if the provided size is null
     * @throws IllegalArgumentException if a dimension is less than 0
     */
    @Override
    public RoboHashRequestBuilder setSize(Dimension size) {
        Preconditions.checkNotNull(size);
        Preconditions.checkArgument(size.width >= 0);
        Preconditions.checkArgument(size.height >= 0);

        this.width = size.width;
        this.height = size.height;
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
     * {@inheritDoc}
     */
    @Override
    public String getAvatarKey() {
        return avatarKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<ImageSet> getImageSets() {
        return ImmutableList.copyOf(imageSets);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BackgroundSet getBackgroundSet() {
        return backgroundImageSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UseGravatar getUseGravatar() {
        return useGravatar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldIgnoreExtension() {
        return ignoreExtension;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ImageExtension getImageExtension() {
        return imageExtension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSafeUrlMode() {
        return safeUrlMode;
    }

    /**
     * Enables URL mode meaning any unsafe URL characters in the provided avatarKey
     * will be encoded before passing to the RoboHash server.
     *
     * @return this builder
     */
    @CanIgnoreReturnValue
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
    @CanIgnoreReturnValue
    public RoboHashRequestBuilder disableSafeUrlMode() {
        safeUrlMode = false;
        return this;
    }

    /**
     * Returns a hashcode for this {@link RoboHashRequestBuilderImpl}.
     *
     * @return a hashcode for this {@link RoboHashRequestBuilderImpl}
     */
    @Override
    public int hashCode() {
        int ret = avatarKey.hashCode();
        ret += 31 * imageSets.hashCode();
        ret += 31 * backgroundImageSet.hashCode();
        ret += 31 * Integer.hashCode(width);
        ret += 31 * Integer.hashCode(height);
        ret += 31 * useGravatar.hashCode();
        ret += 31 * Boolean.hashCode(ignoreExtension);
        ret += 31 * imageExtension.hashCode();
        ret += 31 * Boolean.hashCode(safeUrlMode);
        return ret;
    }

    /**
     * Returns whether the provided object equals {@code this} object or is equal as defined by an equivalence relation.
     *
     * @param o the other object to compare against {@code this}
     * @return whether the provided object equals {@code this} object or is equal as defined by an equivalence relation
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof RoboHashRequestBuilderImpl)) {
            return false;
        }

        RoboHashRequestBuilderImpl other = (RoboHashRequestBuilderImpl) o;
        return other.avatarKey.equals(avatarKey)
                && other.imageSets.equals(imageSets)
                && other.backgroundImageSet == backgroundImageSet
                && other.width == width
                && other.height == height
                && other.useGravatar == useGravatar
                && other.ignoreExtension == ignoreExtension
                && other.imageExtension == imageExtension
                && other.safeUrlMode == safeUrlMode;
    }

    /**
     * Returns a {@link String} representation for this {@link RoboHashRequestBuilderImpl}.
     *
     * @return a {@link String} representation for this {@link RoboHashRequestBuilderImpl}
     */
    @Override
    public String toString() {
        return "RoboHashRequestBuilderImpl{"
                + "avatarKey=\"" + avatarKey + "\""
                + ", imageSets=" + imageSets
                + ", backgroundImageSet=" + backgroundImageSet
                + ", width=" + width
                + ", height=" + height
                + ", useGravatar=" + useGravatar
                + ", ignoreExtension=" + ignoreExtension
                + ", imageExtension=" + imageExtension
                + ", safeUrlMode=" + safeUrlMode
                + '}';
    }
}
