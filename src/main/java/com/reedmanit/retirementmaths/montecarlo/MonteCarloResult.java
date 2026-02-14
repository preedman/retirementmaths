package com.reedmanit.retirementmaths.montecarlo;

public record MonteCarloResult(
        int years,
        int trials,
        double ruinProbability,
        double endingBalanceP5,
        double endingBalanceP50,
        double endingBalanceP95
)
{
}
