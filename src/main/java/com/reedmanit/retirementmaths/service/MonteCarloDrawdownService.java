package com.reedmanit.retirementmaths.service;

import com.reedmanit.retirementmaths.montecarlo.AuHistoricalSeries;
import com.reedmanit.retirementmaths.montecarlo.BootstrapReturnInflationModel;
import com.reedmanit.retirementmaths.montecarlo.MonteCarloResult;
import com.reedmanit.retirementmaths.montecarlo.ReturnInflation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.random.RandomGenerator;

@Service

public class MonteCarloDrawdownService {

    private final AuHistoricalSeries auHistoricalSeries;

    public MonteCarloDrawdownService(AuHistoricalSeries auHistoricalSeries) {
        this.auHistoricalSeries = auHistoricalSeries;
    }

    public MonteCarloResult simulateBootstrapAu(
            double startingBalance,
            double initialWithdrawal,
            int years,
            int trials,
            long seed
    ) {
        if (startingBalance < 0) throw new IllegalArgumentException("startingBalance must be >= 0");
        if (initialWithdrawal < 0) throw new IllegalArgumentException("initialWithdrawal must be >= 0");
        if (years <= 0) throw new IllegalArgumentException("years must be > 0");
        if (trials <= 0) throw new IllegalArgumentException("trials must be > 0");

        BootstrapReturnInflationModel model = new BootstrapReturnInflationModel(auHistoricalSeries.samples());
        RandomGenerator rng = new SplittableRandom(seed);

        int ruined = 0;
        double[] endingBalances = new double[trials];

        for (int t = 0; t < trials; t++) {
            double balance = startingBalance;
            double withdrawal = initialWithdrawal;

            for (int year = 1; year <= years; year++) {
                ReturnInflation ri = model.sample(rng);

                balance = balance * (1.0 + ri.nominalReturn()) - withdrawal;

                if (balance <= 0.0) {
                    balance = 0.0;
                    ruined++;
                    break;
                }

                withdrawal = withdrawal * (1.0 + ri.inflation());
            }

            endingBalances[t] = balance;
        }

        Arrays.sort(endingBalances);

        return new MonteCarloResult(
                years,
                trials,
                ruined / (double) trials,
                percentileSorted(endingBalances, 0.05),
                percentileSorted(endingBalances, 0.50),
                percentileSorted(endingBalances, 0.95)
        );
    }

    private static double percentileSorted(double[] sorted, double p) {
        if (sorted.length == 0) return Double.NaN;
        double idx = p * (sorted.length - 1);
        int lo = (int) Math.floor(idx);
        int hi = (int) Math.ceil(idx);
        if (lo == hi) return sorted[lo];
        double w = idx - lo;
        return sorted[lo] * (1 - w) + sorted[hi] * w;
    }
}
