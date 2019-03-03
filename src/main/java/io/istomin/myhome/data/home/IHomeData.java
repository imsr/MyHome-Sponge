package io.istomin.myhome.data.home;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;

public interface IHomeData extends DataManipulator<IHomeData, IImmutableHomeData> {

    Value<Home> defaultHome();

    MapValue<String, Home> homes();

}
