package io.istomin.myhome.data.friends;

import org.spongepowered.api.data.manipulator.mutable.ListData;
import org.spongepowered.api.data.value.mutable.ListValue;

import java.util.UUID;

public interface IFriendsData extends ListData<UUID, IFriendsData, IImmutableFriendsData> {

    ListValue<UUID> friends();

}