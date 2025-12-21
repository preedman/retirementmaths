# Worked Example: Yaari Optimal Spending (Australia)

This document demonstrates how to calculate the optimal annual spending for a retiree in Australia using the Yaari lifecycle model combined with Gompertz-Makeham mortality parameters.

## The Scenario
An Australian retiree wants to determine their optimal annual spending based on their current wealth and age:

*   **Starting Balance ($W$):** $1,000,000
*   **Current Age ($x$):** 65
*   **Subjective Discount Rate ($\rho$):** 2% (0.02) - *This represents the person's preference for spending now vs. later.*

## Understanding Gompertz-Makeham
The **Gompertz-Makeham Law of Mortality** describes the death rate of a population as a sum of two components: an age-independent component (Makeham) and an age-dependent component (Gompertz) that increases exponentially.

### The Formula
$$\lambda = \alpha + \beta e^{\eta x}$$

### Components Breakdown
1.  **The Makeham Term ($\alpha$):** This represents the "background" risk of death that is constant across all ages (e.g., accidents or non-age-related illness). For Australia, we use a slightly lower risk of **0.0004**.
2.  **The Gompertz Term ($\beta e^{\eta x}$):** This represents the "biological" aging process.
    *   **$\beta$ (Scale Parameter):** The initial level of age-dependent risk.
    *   **$\eta$ (Dynamics Parameter):** The rate at which the risk of death increases as you get older.
3.  **Hazard Rate ($\lambda$):** The resulting annual risk of death (also known as the force of mortality).

---

## Regional Context: Australia vs. USA
This project includes preset parameters based on historical mortality data. Studies comparing Australian Life Tables (ALT) with US data generally show that Australians have higher life expectancies at older ages [[7]](https://www.jstor.org/stable/41464865).

| Parameter | Australia (ALT) | United States | Description |
| :--- | :--- | :--- | :--- |
| **$\alpha$** | 0.0004 | 0.0005 | Lower background/accident risk in Australia. |
| **$\beta$** | 0.000035 | 0.00005 | Lower initial biological aging term in Australia. |
| **$\eta$** | 0.087 | 0.085 | Dynamics of aging (similar across human populations). |

Because Australians typically have a lower mortality risk at age 65, the optimal spending calculated via the Yaari model is more conservative (lower) than the US equivalent to ensure the capital lasts longer [[1]](https://papers.ssrn.com/sol3/Delivery.cfm/SSRN_ID4562921_code4423062.pdf?abstractid=4020309&mirid=1).

---

## Step-by-Step Calculation

### 1. Calculate the Hazard Rate ($\lambda$) for Age 65
Using the Australian parameters:
First, calculate the exponential aging component:
$$e^{0.087 \times 65} = e^{5.655} \approx 285.71$$

Then, apply the full formula:
$$\lambda = 0.0004 + (0.000035 \times 285.71)$$
$$\lambda = 0.0004 + 0.00999 \approx 0.0104$$
*(This implies a ~1.04% annual mortality risk for a 65-year-old.)*

### 2. Calculate the Optimal Spending Rate
According to the Yaari model, the spending rate is the sum of the subjective discount rate and the mortality risk:
$$\text{Rate} = \rho + \lambda = 0.02 + 0.0104 = 0.0304$$

### 3. Final Spending Amount
Multiply the starting balance by the spending rate:
$$c = 1,000,000 \times 0.0304 = 30,400$$

**Result:** For a 65-year-old Australian with $1M, the optimal annual spending is approximately **$30,400**.

---

## Comparison Note
As the retiree ages, the hazard rate ($\lambda$) increases exponentially. For example, at age 85, the hazard rate would be significantly higher, leading to a much higher optimal spending amount (as there is less need to preserve capital for a shorter remaining life expectancy).
