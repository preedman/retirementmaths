package com.reedmanit.retirementmaths.service;

import org.springframework.stereotype.Service;

@Service
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

    /**
     * Calculates optimal spending using the Yaari model.
     * In a simplified Yaari model, consumption is proportional to wealth,
     * adjusted by the interest rate and the hazard rate (mortality).
     *
     * @param r Real rate of return
     * @param i Inflation
     * @param W Starting balance
     * @param lambda Hazard rate (mortality rate, e.g., 0.04 for a 4% annual risk of death)
     * @param rho Subjective discount rate (time preference)
     * @return Optimal annual spending amount
     */
    public double calculateYaariSpending(double r, double i, double W, double lambda, double rho) {
        if (W < 0) {
            throw new IllegalArgumentException("Starting balance cannot be negative.");
        }

        double netRate = r - i;

        // In the basic Yaari lifecycle model with log utility:
        // Optimal consumption c(t) = W(t) * (rho + lambda)
        // However, if we want the sustainable withdrawal considering the interest rate:
        // c = W * (rho + lambda) / (1 + (netRate - rho - lambda)) is a common derivation
        // Simply put: the spending rate is the sum of time preference and mortality risk.
        
        return W * (rho + lambda);
    }

    /**
     * Calculates the hazard rate (mortality risk) using Gompertz-Makeham Law.
     * Formula: mu(x) = alpha + beta * e^(eta * x)
     *
     * @param age Current age (x)
     * @param alpha Age-independent mortality (Makeham term, e.g., 0.0005)
     * @param beta Scale parameter (Gompertz term, e.g., 0.00005)
     * @param eta Dynamics parameter (rate of aging, e.g., 0.085)
     * @return Calculated hazard rate
     */
    public double calculateGompertzMakehamHazardRate(double age, double alpha, double beta, double eta) {
        return alpha + beta * Math.exp(eta * age);
    }

    /**
     * Calculates Yaari spending using a hazard rate derived from Gompertz-Makeham.
     *
     * @param W Starting balance
     * @param rho Subjective discount rate
     * @param age Current age
     * @return Optimal annual spending amount
     */
    public double calculateYaariSpendingWithGompertz(double W, double rho, double age) {
        // Typical US mortality parameters (simplified)
        double alpha = 0.0005;
        double beta = 0.00005;
        double eta = 0.085;

        double lambda = calculateGompertzMakehamHazardRate(age, alpha, beta, eta);

        return calculateYaariSpending(0, 0, W, lambda, rho);
    }

    /**
     * Calculates Yaari spending using Australian mortality parameters.
     * Based on data typically seen in Australian Life Tables (ALT).
     *
     * @param W Starting balance
     * @param rho Subjective discount rate
     * @param age Current age
     * @return Optimal annual spending amount
     */
    public double calculateYaariSpendingAustralia(double W, double rho, double age) {
        // Refined parameters for Australia (Lower base mortality)
        double alpha = 0.0004;  // Slightly lower accident/background risk
        double beta = 0.000035; // Lower initial aging term
        double eta = 0.087;    // Dynamics of aging remain consistent

        double lambda = calculateGompertzMakehamHazardRate(age, alpha, beta, eta);

        return calculateYaariSpending(0, 0, W, lambda, rho);
    }
}
