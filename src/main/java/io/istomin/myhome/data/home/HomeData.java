package io.istomin.myhome.data.home;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;
import io.istomin.myhome.MyHome;


import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class HomeData extends AbstractData<IHomeData, IImmutableHomeData> implements IHomeData {

    private Home defaultHome;
    private Map<String, Home> homes;

    public HomeData(Home defaultHome, Map<String, Home> homes) {
        this.defaultHome = defaultHome;
        this.homes = homes;

        this.registerGettersAndSetters();
    }

    // It's best to provide an empty constructor with "default" values
    public HomeData() {
        this(null, ImmutableMap.of());
    }

    // Override if you have a separate interface
    @Override
    public Value<Home> defaultHome() {
        return Sponge.getRegistry().getValueFactory()
                .createValue(MyHome.DEFAULT_HOME, this.defaultHome, null);
    }

    // Override if you have a separate interface
    @Override
    public MapValue<String, Home> homes() {
        return Sponge.getRegistry().getValueFactory()
                .createMapValue(MyHome.HOMES, this.homes, ImmutableMap.of());
    }

    private Home getDefaultHome() {
        return this.defaultHome;
    }

    private Map<String, Home> getHomes() {
        return Maps.newHashMap(this.homes);
    }

    private void setDefaultHome(@Nullable Home defaultHome) {
        this.defaultHome = defaultHome;
    }

    private void setHomes(Map<String, Home> homes) {
        Preconditions.checkNotNull(homes);
        this.homes = Maps.newHashMap(homes);
    }

    @Override
    protected void registerGettersAndSetters() {
        registerKeyValue(MyHome.DEFAULT_HOME, this::defaultHome);
        registerKeyValue(MyHome.HOMES, this::homes);

        registerFieldGetter(MyHome.DEFAULT_HOME, this::getDefaultHome);
        registerFieldGetter(MyHome.HOMES, this::getHomes);

        registerFieldSetter(MyHome.DEFAULT_HOME, this::setDefaultHome);
        registerFieldSetter(MyHome.HOMES, this::setHomes);
    }

    @Override
    public int getContentVersion() {
        // Update whenever the serialization format changes
        return HomeDataBuilder.CONTENT_VERSION;
    }

    @Override
    public ImmutableHomeData asImmutable() {
        return new ImmutableHomeData(this.defaultHome, this.homes);
    }

    // Only required on mutable implementations
    @Override
    public Optional<HomeData> fill(DataHolder dataHolder, MergeFunction overlap) {
        HomeData merged = overlap.merge(this, dataHolder.get(HomeData.class).orElse(null));
        this.defaultHome = merged.defaultHome().get();
        this.homes = merged.homes().get();

        return Optional.of(this);
    }

    // Only required on mutable implementations
    @Override
    public Optional<HomeData> from(DataContainer container) {
        if (!container.contains(MyHome.DEFAULT_HOME, MyHome.HOMES)) {
            return Optional.empty();
        }
        // Loads the structure defined in toContainer
        this.defaultHome = container.getSerializable(MyHome.DEFAULT_HOME.getQuery(), Home.class).get();

        // Loads the map of homes
        this.homes = Maps.newHashMap();
        DataView homes = container.getView(MyHome.HOMES.getQuery()).get();
        for (DataQuery homeQuery : homes.getKeys(false)) {
            homes.getSerializable(homeQuery, Home.class)
                    .ifPresent(home -> this.homes.put(homeQuery.toString(), home));
        }

        return Optional.of(this);
    }

    @Override
    public HomeData copy() {
        return new HomeData(this.defaultHome, this.homes);
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        // This is the simplest, but use whatever structure you want!
        if (this.defaultHome != null) {
            container.set(MyHome.DEFAULT_HOME, this.defaultHome);
        }
        container.set(MyHome.HOMES, this.homes);

        return container;
    }
}
