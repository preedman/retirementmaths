package com.reedmanit.retirementmaths.data;

public class MonteCarloParameters {

    private double startingBalance;
    private double initialWithdrawal;
    private int years;
    private int trials;
    private long seed;

    public MonteCarloParameters() {
    }

    public MonteCarloParameters(double startingBalance, double initialWithdrawal, int years, int trials, long seed) {
        this.startingBalance = startingBalance;
        this.initialWithdrawal = initialWithdrawal;
        this.years = years;
        this.trials = trials;
        this.seed = seed;
    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public double getInitialWithdrawal() {
        return initialWithdrawal;
    }

    public void setInitialWithdrawal(double initialWithdrawal) {
        this.initialWithdrawal = initialWithdrawal;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getTrials() {
        return trials;
    }

    public void setTrials(int trials) {
        this.trials = trials;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
