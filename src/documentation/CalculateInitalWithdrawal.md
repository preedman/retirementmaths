# Worked Example: Calculating Initial Withdrawal

This document provides a walkthrough for determining the maximum sustainable annual withdrawal amount ($c$) given a specific starting balance and a target duration.

## The Scenario
A retiree wants to spend their savings over a fixed period and needs to know how much they can withdraw each year:

*   **Starting Balance ($W$):** $300,000
*   **Desired Time ($t$):** 11.89 years
*   **Expected Return ($r$):** 6% (0.06)
*   **Expected Inflation ($i$):** 3% (0.03)

## The Formula
The initial withdrawal ($c$) is calculated using the following formula:

$$c = \frac{W(r - i)}{1 - e^{-(r - i)t}}$$

## Step-by-Step Calculation

### 1. Calculate the Net Rate
Find the real growth rate ($r - i$):
$$0.06 - 0.03 = 0.03$$

### 2. Calculate the Annual Net Growth
Multiply the starting balance by the net rate:
$$300,000 \times 0.03 = 9,000$$

### 3. Calculate the Exponential Decay Factor
Calculate $e^{-(r - i)t}$:
$$e^{-(0.03 \times 11.89)} \approx e^{-0.3567} \approx 0.7000$$

### 4. Calculate the Divisor
Subtract the decay factor from 1:
$$1 - 0.7000 = 0.3000$$

### 5. Final Result
Divide the annual net growth (Step 2) by the divisor (Step 4):
$$\frac{9,000}{0.3000} = 30,000$$

**Result:** Given a $300,000 balance and a 3% net return, you can withdraw **$30,000** annually for approximately 11.89 years.

---

## Special Case: Zero Net Growth
If the return matches inflation ($r = i$), the growth doesn't offset the withdrawal, so the formula simplifies to:
$$c = \frac{W}{t}$$

**Example:**
*   Starting Balance ($W$): $200,000
*   Time ($t$): 20 years
*   Net Growth: 0%
*   **Initial Withdrawal:** $200,000 / 20 = \mathbf{\$10,000}$