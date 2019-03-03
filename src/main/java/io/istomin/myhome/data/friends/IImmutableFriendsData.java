package io.istomin.myhome.data.friends;

import org.spongepowered.api.data.manipulator.immutable.ImmutableListData;
import org.spongepowered.api.data.value.immutable.ImmutableListValue;

import java.util.UUID;

public interface IImmutableFriendsData extends ImmutableListData<UUID, IImmutableFriendsData, IFriendsData> {

    ImmutableListValue<UUID> friends();

}