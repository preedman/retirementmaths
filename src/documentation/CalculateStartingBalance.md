# Worked Example: Calculating Required Starting Balance

This document explains how to calculate the capital needed ($W$) at the start of retirement to support a specific annual withdrawal for a set number of years.

## The Scenario
A user wants to plan for a retirement period with the following goals:

*   **Desired Time ($t$):** 25 years
*   **Annual Withdrawal ($c$):** $50,000
*   **Expected Return ($r$):** 5% (0.05)
*   **Expected Inflation ($i$):** 2% (0.02)

## The Formula
The required starting balance ($W$) is calculated using the following formula:

$$W = \frac{c}{r - i} \left( 1 - e^{-(r - i)t} \right)$$

## Step-by-Step Calculation

### 1. Calculate the Net Rate
Find the real growth rate ($r - i$):
$$0.05 - 0.02 = 0.03$$

### 2. Calculate the Ratio ($c / \text{netRate}$)
Divide the initial withdrawal by the net growth rate:
$$\frac{50,000}{0.03} \approx 1,666,666.67$$

### 3. Calculate the Exponential Decay Factor
Calculate $e^{-(r - i)t}$ which represents the "discounting" effect over time:
$$e^{-(0.03 \times 25)} = e^{-0.75} \approx 0.47237$$

### 4. Calculate the Present Value Factor
Subtract the decay factor from 1:
$$1 - 0.47237 = 0.52763$$

### 5. Final Result
Multiply the ratio from step 2 by the factor from step 4:
$$1,666,666.67 \times 0.52763 \approx 879,383.33$$

**Result:** To support a $50,000 annual withdrawal for 25 years with a 3% net return, you would need a starting balance of approximately **$879,383.33**.

---

## Special Case: Zero Net Growth
If the return on investment exactly matches inflation ($r = i$), the formula simplifies to:
$$W = c \times t$$

**Example:**
*   Withdrawal ($c$): $50,000
*   Time ($t$): 20 years
*   Net Growth: 0%
*   **Required Balance:** $50,000 \times 20 = \mathbf{\$1,000,000}$