package io.istomin.myhome.data.home;

import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.DataContentUpdater;
import org.spongepowered.api.data.persistence.InvalidDataException;
import io.istomin.myhome.MyHome;
import io.istomin.myhome.data.home.Home;
import io.istomin.myhome.data.home.IHomeData;
import io.istomin.myhome.data.home.IImmutableHomeData;

import java.util.Optional;

public class HomeDataBuilder extends AbstractDataBuilder<IHomeData> implements DataManipulatorBuilder<IHomeData, IImmutableHomeData> {

    public static final int CONTENT_VERSION = 2;

    public HomeDataBuilder() {
        super(IHomeData.class, CONTENT_VERSION);
    }

    @Override
    public HomeData create() {
        return new HomeData();
    }

    @Override
    public Optional<IHomeData> createFrom(DataHolder dataHolder) {
        return create().fill(dataHolder);
    }

    @Override
    protected Optional<HomeData> buildContent(DataView container) throws InvalidDataException {
        if (!container.contains(MyHome.HOMES)) return Optional.empty();

        HomeData data = new HomeData();

        container.getView(MyHome.HOMES.getQuery())
                .get().getKeys(false).forEach(name -> data.homes().put(name.toString(), container.getSerializable(name, Home.class)
                .orElseThrow(InvalidDataException::new)));

        container.getSerializable(MyHome.DEFAULT_HOME.getQuery(), Home.class).ifPresent(home -> {
            data.set(MyHome.DEFAULT_HOME, home);
        });

        return Optional.of(data);
    }

    public static class HomesUpdater implements DataContentUpdater {

        @Override
        public int getInputVersion() {
            return 1;
        }

        @Override
        public int getOutputVersion() {
            return 2;
        }

        @Override
        public DataView update(DataView content) {
            content.set(MyHome.HOMES, ImmutableMap.of());

            return content;
        }
    }
}