package com.reedmanit.retirementmaths.service;

import com.reedmanit.retirementmaths.montecarlo.AuHistoricalSeries;
import com.reedmanit.retirementmaths.montecarlo.MonteCarloResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MonteCarloDrawdownServiceTest {

    @Test
    void simulateBootstrapAu_sameSeed_sameResult() {
        AuHistoricalSeries series = new AuHistoricalSeries();
        MonteCarloDrawdownService service = new MonteCarloDrawdownService(series);

        double startingBalance = 1_000_000;
        double initialWithdrawal = 40_000;
        int years = 30;
        int trials = 5_000;
        long seed = 42L;

        MonteCarloResult r1 = service.simulateBootstrapAu(startingBalance, initialWithdrawal, years, trials, seed);
        MonteCarloResult r2 = service.simulateBootstrapAu(startingBalance, initialWithdrawal, years, trials, seed);

        assertEquals(r1.years(), r2.years());
        assertEquals(r1.trials(), r2.trials());

        assertEquals(r1.ruinProbability(), r2.ruinProbability(), 0.0);
        assertEquals(r1.endingBalanceP5(), r2.endingBalanceP5(), 0.0);
        assertEquals(r1.endingBalanceP50(), r2.endingBalanceP50(), 0.0);
        assertEquals(r1.endingBalanceP95(), r2.endingBalanceP95(), 0.0);
    }

    @Test
    void simulateBootstrapAu_differentSeed_usuallyDifferentResult() {
        AuHistoricalSeries series = new AuHistoricalSeries();
        MonteCarloDrawdownService service = new MonteCarloDrawdownService(series);

        double startingBalance = 1_000_000;
        double initialWithdrawal = 40_000;
        int years = 30;
        int trials = 5_000;

        MonteCarloResult r1 = service.simulateBootstrapAu(startingBalance, initialWithdrawal, years, trials, 1L);
        MonteCarloResult r2 = service.simulateBootstrapAu(startingBalance, initialWithdrawal, years, trials, 2L);

        // Not mathematically guaranteed, but extremely likely with 5000 trials.
        // We only assert "not exactly equal" on at least one key metric.
        boolean anyDifferent =
                r1.ruinProbability() != r2.ruinProbability()
                        || r1.endingBalanceP50() != r2.endingBalanceP50()
                        || r1.endingBalanceP95() != r2.endingBalanceP95();

        assertTrue(anyDifferent, "Different seeds should usually produce different results");
    }

}
