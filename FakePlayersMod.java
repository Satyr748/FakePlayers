package de.example.fakeplayers;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class FakePlayersMod implements ModInitializer {
    public static final String MOD_ID = "fakeplayers";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(Commands.literal("fakeplayers")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("spawn")
                    .then(Commands.argument("amount", IntegerArgumentType.integer(1, 2000))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            int amount = IntegerArgumentType.getInteger(context, "amount");
                            int created = FakePlayerStore.create(
                                player.serverLevel().dimension().location().toString(),
                                player.getX(), player.getY(), player.getZ(), amount
                            );
                            context.getSource().sendSuccess(
                                () -> Component.literal("FakePlayers: " + created
                                    + " virtuelle Datensätze angelegt. Hinweis: Diese Prototype-Version rendert noch keine Figuren."),
                                false
                            );
                            return created;
                        })))
                .then(Commands.literal("remove")
                    .executes(context -> {
                        int removed = FakePlayerStore.clear();
                        context.getSource().sendSuccess(
                            () -> Component.literal(removed + " FakePlayer-Datensätze entfernt."),
                            false
                        );
                        return removed;
                    }))
                .then(Commands.literal("count")
                    .executes(context -> {
                        int count = FakePlayerStore.size();
                        context.getSource().sendSuccess(
                            () -> Component.literal("Gespeicherte FakePlayer-Datensätze: " + count),
                            false
                        );
                        return count;
                    }))
            )
        );
    }
}
