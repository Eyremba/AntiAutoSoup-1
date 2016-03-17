package com.therealspoljo.antiautosoup.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TempStorage {

    private TempStorage() {
    }

    private static final Map<UUID, Long> lastAttacked = new HashMap<>();
    public static final Map<UUID, Violation> violations = new HashMap<>();

    public static long getLastAttackTime(UUID uuid) {
	if (!lastAttacked.containsKey(uuid)) {
	    lastAttacked.put(uuid, System.currentTimeMillis());
	}

	return lastAttacked.get(uuid);
    }

    public static void setLastAttackTime(UUID uuid) {
	lastAttacked.put(uuid, System.currentTimeMillis());
    }

    public static int raiseViolationLevel(UUID uuid) {
	Violation violation = getViolation(uuid);

	violation.raiseViolationLevel();
	violations.put(uuid, violation);

	return violation.getViolationLevel();
    }

    public static Violation getViolation(UUID uuid) {
	if (!violations.containsKey(uuid)) {
	    violations.put(uuid, new Violation());
	}

	return (Violation) violations.get(uuid);
    }
}