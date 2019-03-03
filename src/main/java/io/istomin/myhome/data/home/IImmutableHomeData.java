package io.istomin.myhome.data.home;

import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;

public interface IImmutableHomeData extends ImmutableDataManipulator<IImmutableHomeData, IHomeData> {

    Value<Home> defaultHome();

    MapValue<String, Home> homes();

}
