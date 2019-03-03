package io.istomin.myhome.data.home;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableMapValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import io.istomin.myhome.MyHome;


import java.util.Map;

public class ImmutableHomeData extends AbstractImmutableData<IImmutableHomeData, IHomeData> implements IImmutableHomeData {

    private final Home defaultHome;
    private final ImmutableMap<String, Home> homes;

    private final ImmutableValue<Home> defaultHomeValue;
    private final ImmutableMapValue<String, Home> homesValue;

    public ImmutableHomeData() {
        this(null, ImmutableMap.of());
    }

    public ImmutableHomeData(Home defaultHome, Map<String, Home> homes) {
        this.defaultHome = checkNotNull(defaultHome);
        this.homes = ImmutableMap.copyOf(checkNotNull(homes));

        this.defaultHomeValue = Sponge.getRegistry().getValueFactory()
                .createValue(MyHome.DEFAULT_HOME, defaultHome)
                .asImmutable();

        this.homesValue = Sponge.getRegistry().getValueFactory()
                .createMapValue(MyHome.HOMES, homes, ImmutableMap.of())
                .asImmutable();

        this.registerGetters();
    }

    // Override if you have a separate interface
    @Override
    public ImmutableValue<Home> defaultHome() {
        return this.defaultHomeValue;
    }

    // Override if you have a separate interface
    @Override
    public ImmutableMapValue<String, Home> homes() {
        return this.homesValue;
    }

    private Home getDefaultHome() {
        return this.defaultHome;
    }

    private Map<String, Home> getHomes() {
        return this.homes;
    }

    @Override
    protected void registerGetters() {
        registerKeyValue(MyHome.DEFAULT_HOME, this::defaultHome);
        registerKeyValue(MyHome.HOMES, this::homes);

        registerFieldGetter(MyHome.DEFAULT_HOME, this::getDefaultHome);
        registerFieldGetter(MyHome.HOMES, this::getHomes);
    }

    @Override
    public int getContentVersion() {
        // Update whenever the serialization format changes
        return HomeDataBuilder.CONTENT_VERSION;
    }

    @Override
    public HomeDataImpl asMutable() {
        return new HomeDataImpl(this.defaultHome, this.homes);
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        // This is the simplest, but use whatever structure you want!
        if (this.defaultHome != null) {
            container.set(MyHomes.DEFAULT_HOME, this.defaultHome);
        }
        container.set(MyHomes.HOMES, this.homes);

        return container;
    }
}
