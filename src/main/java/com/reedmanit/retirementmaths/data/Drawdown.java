package com.reedmanit.retirementmaths.data;

public class Drawdown {

    /**
     * Calculates the time (t) for a drawdown.
     * Formula: t = (1 / (r - i)) * ln( c / (c - W * (r - i)) )
     *
     * @param r Real rate of return (e.g. 0.05)
     * @param i Inflation (e.g. 0.02)
     * @param W Starting balance
     * @param c Initial withdrawal
     * @return Time in years, or Double.POSITIVE_INFINITY if sustainable
     * @throws IllegalArgumentException if withdrawal c <= 0 or W < 0
     */
    public double calculateDrawdownTime(double r, double i, double W, double c) {
        if (c <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (W < 0) {
            throw new IllegalArgumentException("Starting balance cannot be negative.");
        }

        double netRate = r - i;

        // If net growth is zero, time is just balance divided by withdrawal
        if (Math.abs(netRate) < 1e-9) {
            return W / c;
        }

        double denominator = c - (W * netRate);

        // If the withdrawal is less than or equal to the interest earned, 
        // the money lasts forever.
        if (denominator <= 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (1.0 / netRate) * Math.log(c / denominator);
    }

    /**
     * Calculates the required starting balance (W) to last for t years.
     * Formula: W = (c / (r - i)) * (1 - e^(-(r - i) * t))
     *
     * @param r Real rate of return
     * @param i Inflation
     * @param t Desired time in years
     * @param c Initial withdrawal
     * @return Required starting balance
     */
    public double calculateRequiredStartingBalance(double r, double i, double t, double c) {
        double netRate = r - i;

        if (Math.abs(netRate) < 1e-9) {
            return c * t;
        }

        return (c / netRate) * (1 - Math.exp(-netRate * t));
    }

    /**
     * Calculates the initial withdrawal amount (c) given a balance and time.
     * Formula: c = (W * (r - i)) / (1 - e^(-(r - i) * t))
     *
     * @param r Real rate of return
     * @param i Inflation
     * @param W Starting balance
     * @param t Desired time in years
     * @return Initial withdrawal amount
     */
    public double calculateInitialWithdrawal(double r, double i, double W, double t) {
        if (t <= 0) {
            throw new IllegalArgumentException("Time must be greater than zero.");
        }

        double netRate = r - i;

        if (Math.abs(netRate) < 1e-9) {
            return W / t;
        }

        return (W * netRate) / (1 - Math.exp(-netRate * t));
    }
}
