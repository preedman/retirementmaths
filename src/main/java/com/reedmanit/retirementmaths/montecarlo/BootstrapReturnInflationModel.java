package com.reedmanit.retirementmaths.montecarlo;

import java.util.List;
import java.util.random.RandomGenerator;

public final class BootstrapReturnInflationModel {

    private final List<ReturnInflation> historical;

    public BootstrapReturnInflationModel(List<ReturnInflation> historical) {
        if (historical == null || historical.isEmpty()) {
            throw new IllegalArgumentException("historical samples must not be null/empty");
        }
        this.historical = historical;
    }

    public ReturnInflation sample(RandomGenerator rng) {
        int idx = rng.nextInt(historical.size());
        return historical.get(idx);
    }
}
