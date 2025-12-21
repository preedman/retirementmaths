# Worked Example: Calculating Drawdown Time

This document provides a detailed walkthrough of how the drawdown time is calculated using the formula implemented in this project.

## The Scenario
A retiree wants to determine how long their savings will last given the following parameters:

*   **Starting Balance ($W$):** $300,000
*   **Annual Withdrawal ($c$):** $30,000
*   **Expected Return ($r$):** 6% (0.06)
*   **Expected Inflation ($i$):** 3% (0.03)

## The Formula
The time ($t$) in years is calculated using the following continuous drawdown formula:

$$t = \frac{1}{r - i} \cdot \ln \left( \frac{c}{c - W(r - i)} \right)$$

## Step-by-Step Calculation

### 1. Calculate the Net Rate
First, we find the real rate of growth by subtracting inflation from the return:
$$0.06 - 0.03 = 0.03$$

### 2. Calculate the Interest Earned
Determine how much "net" interest the starting balance generates annually:
$$300,000 \times 0.03 = 9,000$$

### 3. Calculate the Denominator
Subtract the interest earned from the annual withdrawal amount:
$$30,000 - 9,000 = 21,000$$

### 4. Calculate the Logarithmic Ratio
Divide the withdrawal by the denominator and take the natural logarithm ($\ln$):
$$\frac{30,000}{21,000} \approx 1.42857$$
$$\ln(1.42857) \approx 0.35667$$

### 5. Final Result
Multiply by the inverse of the net rate to find the years:
$$\frac{1}{0.03} \times 0.35667 \approx 11.89 \text{ years}$$

**Result:** The savings will last approximately **11.89 years**.

## Important Edge Cases

*   **Sustainability:** If the annual withdrawal ($c$) is less than or equal to the interest earned ($W \times (r-i)$), the money will last indefinitely (returns $\infty$).
*   **Zero Net Rate:** If the return exactly matches inflation ($r = i$), the time is simply the balance divided by the withdrawal: $W / c$.