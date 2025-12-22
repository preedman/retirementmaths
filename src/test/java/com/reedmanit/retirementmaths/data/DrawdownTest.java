package com.reedmanit.retirementmaths.data;

import com.reedmanit.retirementmaths.service.Drawdown;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;


public class DrawdownTest {

    private Drawdown drawdown;

    @BeforeEach
    void setUp() {
        drawdown = new Drawdown();
    }

    @Test
    void calculateMyDrawndown() {
        // $300,000 balance, $30,000 withdrawal, 6% return, 3% inflation
        // t = (1/0.03) * ln(300000 / (300000 - 30000 * 0.03))
        // t = 11.89 years
        double result = drawdown.calculateDrawdownTime(0.06, 0.03, 300000, 30000);
        System.out.println(result);
        assertThat(result).isCloseTo(11.89, within(0.01));
    }

    @Test
    void shouldCalculateCorrectTimeForStandardScenario() {
        // Example: $1M balance, 5% return, 2% inflation (3% net), $50k withdrawal
        // t = (1/0.03) * ln(50000 / (50000 - 1000000 * 0.03))
        // t = 33.33 * ln(50000 / 20000) = 33.33 * ln(2.5) ≈ 30.54 years
        double result = drawdown.calculateDrawdownTime(0.05, 0.02, 1000000, 50000);
        assertThat(result).isCloseTo(30.54, within(0.01));
    }

    @Test
    void shouldReturnInfinityWhenWithdrawalIsSustainable() {
        // Growth (30k) is equal to withdrawal (30k) -> lasts forever
        double result = drawdown.calculateDrawdownTime(0.05, 0.02, 1000000, 30000);
        assertThat(result).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    void shouldHandleZeroNetRate() {
        // $100k balance, $10k withdrawal, return matches inflation
        double result = drawdown.calculateDrawdownTime(0.02, 0.02, 100000, 10000);
        assertThat(result).isEqualTo(10.0);
    }

    @Test
    void shouldThrowExceptionForInvalidWithdrawal() {
        assertThatThrownBy(() -> drawdown.calculateDrawdownTime(0.05, 0.02, 1000, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than zero");
    }

    @Test
    void shouldCalculateCorrectStartingBalance() {
        // From your previous test: $30,000 withdrawal, 6% return, 3% inflation, 11.89 years
        // Should result in approximately $300,000
        double requiredBalance = drawdown.calculateRequiredStartingBalance(0.06, 0.03, 11.89, 30000);

        assertThat(requiredBalance).isCloseTo(300000.0, within(100.0));
    }

    @Test
    void shouldCalculateBalanceForZeroNetRate() {
        // If return equals inflation, you just need Withdrawal * Years
        double result = drawdown.calculateRequiredStartingBalance(0.02, 0.02, 20, 10000);
        assertThat(result).isEqualTo(200000.0);
    }

    @Test
    void shouldCalculateCorrectInitialWithdrawal() {
        // Given $300k, 6% return, 3% inflation, lasting 11.89 years
        // Should result in approximately $30,000 withdrawal
        double result = drawdown.calculateInitialWithdrawal(0.06, 0.03, 300000, 11.89);

        assertThat(result).isCloseTo(30000.0, within(100.0));
    }

    @Test
    void shouldHandleZeroNetRateForWithdrawal() {
        // $200k balance lasting 20 years with zero net growth should be $10k/year
        double result = drawdown.calculateInitialWithdrawal(0.02, 0.02, 200000, 20);
        assertThat(result).isEqualTo(10000.0);
    }

    @Test
    void shouldCalculateYaariSpending() {
        // $1,000,000 balance
        // Mortality risk (lambda) = 0.04
        // Subjective discount rate (rho) = 0.02
        // Result: 1,000,000 * (0.04 + 0.02) = 60,000
        double result = drawdown.calculateYaariSpending(0.05, 0.02, 1000000, 0.04, 0.02);
        assertThat(result).isEqualTo(60000.0);
    }

    @Test
    void shouldCalculateIncreasedSpendingWithAgeUsingGompertz() {
        double balance = 1000000;
        double rho = 0.02;

        // Spending at age 65
        double spending65 = drawdown.calculateYaariSpendingWithGompertz(balance, rho, 65);
        // Spending at age 85 (hazard rate will be much higher)
        double spending85 = drawdown.calculateYaariSpendingWithGompertz(balance, rho, 85);

        System.out.println("Spending at 65: " + spending65);
        System.out.println("Spending at 85: " + spending85);

        assertThat(spending85).isGreaterThan(spending65);
    }

    @Test
    void shouldCompareUSAndAustralianSpending() {
        double balance = 1000000;
        double rho = 0.02;
        double age = 65;

        double spendingUS = drawdown.calculateYaariSpendingWithGompertz(balance, rho, age);
        double spendingAU = drawdown.calculateYaariSpendingAustralia(balance, rho, age);

        System.out.println("At age 65:");
        System.out.println("US Optimal Spending: " + spendingUS);
        System.out.println("AU Optimal Spending: " + spendingAU);

        // Australians usually have lower mortality at 65, so spending is lower/more conservative
        assertThat(spendingAU).isLessThan(spendingUS);
    }
}
