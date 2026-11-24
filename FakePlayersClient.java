package de.example.fakeplayers;

import net.fabricmc.api.ClientModInitializer;

public final class FakePlayersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Client-side rendering will be implemented in the next stage.
        // No entities are spawned here, so this prototype has no fake-player
        // rendering cost and does not yet display visible player models.
    }
}
