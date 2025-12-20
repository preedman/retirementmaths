# Retirement Maths

A Java-based retirement planning utility built with Spring Boot. This project provides mathematical tools to calculate drawdown scenarios, helping users understand how long their savings will last, how much they need to start with, or what their sustainable withdrawal rate is.

## Features

The core logic is contained in the `Drawdown` class, which implements the following formulas:

*   **Drawdown Time ($t$):** Calculates how many years a balance will last given a withdrawal rate, return, and inflation.
    *   *Formula:* $t = \frac{1}{r - i} \cdot \ln \left( \frac{c}{c - W(r - i)} \right)$
*   **Required Starting Balance ($W$):** Calculates the capital needed to support a specific retirement duration.
    *   *Formula:* $W = \frac{c}{r - i} \left( 1 - e^{-(r - i)t} \right)$
*   **Initial Withdrawal ($c$):** Calculates the maximum annual withdrawal allowed for a specific timeframe.
    *   *Formula:* $c = \frac{W(r - i)}{1 - e^{-(r - i)t}}$
    *   **Yaari Optimal Spending:** Calculates spending based on Menahem Yaari's lifecycle model, incorporating mortality risk (hazard rate) and time preference.
        *   *Formula:* $c = W(\rho + \lambda)$
    *   **Gompertz-Makeham Mortality:** Calculates the age-dependent hazard rate ($\lambda$) used in the Yaari model.
        *   *Formula:* $\mu(x) = \alpha + \beta e^{\eta x}$
    *   **Regional Mortality Support:** Includes preset parameters for US and Australian mortality data (ALT) to refine optimal spending calculations based on localized longevity.

    *Key: $r$ = Real rate of return, $i$ = Inflation, $W$ = Starting balance, $c$ = Initial withdrawal, $\rho$ = Subjective discount rate, $\lambda$ = Hazard (mortality) rate, $x$ = Age.*

    ## Tech Stack

*   **Java 17**
*   **Spring Boot 3.5.8**
*   **JUnit 5 & AssertJ** (for mathematical validation)
*   **Maven**

## Getting Started

### Prerequisites
*   JDK 17 or higher
*   Maven

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/retirementmaths.git
   ```
2. Navigate to the project directory:
   ```bash
   cd retirementmaths
   ```
3. Build the project:
   ```bash
   ./mvnw clean install
   ```

### Running Tests
To verify the mathematical accuracy of the drawdown formulas, run the included test suite:
```bash
./mvnw test
```