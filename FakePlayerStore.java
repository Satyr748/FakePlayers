package de.example.fakeplayers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Lightweight in-memory prototype store.
 *
 * Important: these records are NOT Minecraft entities and are NOT rendered.
 * The next development step is a server-to-client payload plus client-side
 * player-model rendering. Keeping them as data avoids entity ticking.
 */
public final class FakePlayerStore {
    private static final int HARD_LIMIT = 2000;
    private static final String[] PARTS = {
        "Fox", "Miner", "Pixel", "Craft", "Nova", "Block", "Birch",
        "Shadow", "Cloud", "Bee", "Stone", "River", "Comet", "Spark"
    };
    private static final List<FakePlayerRecord> RECORDS = new ArrayList<>();

    private FakePlayerStore() {}

    public static synchronized int create(String dimension, double x, double y, double z, int amount) {
        int count = Math.min(amount, HARD_LIMIT - RECORDS.size());
        for (int i = 0; i < count; i++) {
            String name = PARTS[ThreadLocalRandom.current().nextInt(PARTS.length)]
                + ThreadLocalRandom.current().nextInt(1000, 10000);
            RECORDS.add(new FakePlayerRecord(
                UUID.randomUUID(), name, dimension, x, y, z
            ));
        }
        return count;
    }

    public static synchronized int clear() {
        int old = RECORDS.size();
        RECORDS.clear();
        return old;
    }

    public static synchronized int size() {
        return RECORDS.size();
    }

    public static synchronized List<FakePlayerRecord> snapshot() {
        return Collections.unmodifiableList(new ArrayList<>(RECORDS));
    }

    public record FakePlayerRecord(
        UUID id, String name, String dimension, double x, double y, double z
    ) {}
}
