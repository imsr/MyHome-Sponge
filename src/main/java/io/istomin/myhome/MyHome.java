package io.istomin.myhome;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import io.istomin.myhome.data.friends.IFriendsData;
import io.istomin.myhome.data.friends.IImmutableFriendsData;
import io.istomin.myhome.data.home.*;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataManager;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

import java.util.UUID;

@Plugin(
        id = "myhome",
        name = "MyHome",
        description = "A Home warp point plugin for Sponge modding platform",
        url = "https://github.com/imsr",
        authors = {
                "Istom1n"
        }
)
public class MyHome {

    public static Key<Value<Home>> DEFAULT_HOME = DummyObjectProvider.createExtendedFor(Key.class, "DEFAULT_HOME");
    public static Key<MapValue<String, Home>> HOMES = DummyObjectProvider.createExtendedFor(Key.class, "HOMES");
    public static Key<ListValue<UUID>> FRIENDS = DummyObjectProvider.createExtendedFor(Key.class, "FRIENDS");

    @Inject
    private PluginContainer container;
    private DataRegistration<IFriendsData, IImmutableFriendsData> FRIENDS_DATA_REGISTRATION;
    private DataRegistration<IHomeData, IImmutableHomeData> HOME_DATA_REGISTRATION;

    @Listener
    public void onKeyRegistration(GameRegistryEvent.Register<Key<?>> event) {
        DEFAULT_HOME = Key.builder()
                .type(new TypeToken<Value<Home>>() {})
                .id("myhome:default_home")
                .name("Default Home")
                .query(DataQuery.of("DefaultHome"))
                .build();
        HOMES = Key.builder()
                .type(new TypeToken<MapValue<String, Home>>() {})
                .id("myhome:homes")
                .name("Homes")
                .query(DataQuery.of("Homes"))
                .build();
        FRIENDS = Key.builder()
                .type(new TypeToken<ListValue<UUID>>() {})
                .id("myhome:friends")
                .name("Friends")
                .query(DataQuery.of("Friends"))
                .build();
    }

    @Listener
    public void onGameInit(GameInitializationEvent event) {
        System.err.println("derp");
    }

    @Listener
    public void onDataRegistration(GameRegistryEvent.Register<DataRegistration<?, ?>> event) {
        final DataManager dataManager = Sponge.getDataManager();
        // Home stuff
        dataManager.registerBuilder(Home.class, new HomeBuilder());
        dataManager.registerContentUpdater(Home.class, new HomeBuilder.NameUpdater());
        dataManager.registerContentUpdater(IHomeData.class, new HomeDataBuilder.HomesUpdater());

        this.HOME_DATA_REGISTRATION = DataRegistration.builder()
                .dataClass(IHomeData.class)
                .immutableClass(IImmutableHomeData.class)
                .dataImplementation(HomeData.class)
                .immutableImplementation(ImmutableHomeData.class)
                .dataName("Home Data")
                .manipulatorId("myhome:home")
                .buildAndRegister(this.container);

        // Friends stuff
        this.FRIENDS_DATA_REGISTRATION = DataRegistration.builder()
                .dataClass(IFriendsData.class)
                .immutableClass(IImmutableFriendsData.class)
                .dataImplementation(FriendsData.class)
                .immutableImplementation(ImmutableFriendsData.class)
                .dataName("Friends Data")
                .manipulatorId("myhome:friends")
                .buildAndRegister(this.container);
    }

    @Listener
    public void onClientConnectionJoin(ClientConnectionEvent.Join event) {
        Player player = event.getTargetEntity();

        player.get(DEFAULT_HOME).ifPresent(home -> {
            player.setTransform(home.getTransform());
            player.sendMessage(ChatTypes.ACTION_BAR,
                    Text.of("[MyHome] Teleported to home - ", TextStyles.BOLD, home.getName()));
        });
    }
}
