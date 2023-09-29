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
    RoboHashRequestBuilder addSet(Set set);
    RoboHashRequestBuilder removeSet(Set set);
    RoboHashRequestBuilder addSets(Collection<Set> sets);
    RoboHashRequestBuilder removeSets(Collection<Set> sets);

    RoboHashRequestBuilder addBackgroundSet(BackgroundSet backgroundSet);
    RoboHashRequestBuilder removeBackgroundSet(BackgroundSet backgroundSet);

    RoboHashRequestBuilder setImageExtension(ImageExtension imageExtension);
    RoboHashRequestBuilder resetImageExtension();

    RoboHashRequestBuilder setIgnoreExtension(boolean shouldIgnoreImageExtension);
    RoboHashRequestBuilder resetIgnoreExtension();

    RoboHashRequestBuilder setUseGravatar(UseGravatar useGravatar);
    RoboHashRequestBuilder resetUseGravatar();

    RoboHashRequestBuilder setWidth(int width);
    RoboHashRequestBuilder unsetWidth();
    RoboHashRequestBuilder setHeight(int height);
    RoboHashRequestBuilder unsetHeight();
    RoboHashRequestBuilder setSize(Dimension size);
    RoboHashRequestBuilder unsetSize();
}
