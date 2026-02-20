# Worked Example: Monte Carlo Drawdown Simulation (Bootstrap AU Returns + Inflation)

This document explains how the **Monte Carlo drawdown simulation** works in this project and provides:

- A small hand-calculable worked example (1 trial, 3 years)
- A “how to run it” guide using the **Monte Carlo** tab in the Dashboard UI
- Guidance for interpreting the results (ruin probability and percentiles)

---

## 1) What this Monte Carlo simulation is doing

We simulate retirement year-by-year where:

- **Time step:** yearly
- **Returns:** **nominal** (not “real”)
- **Inflation:** simulated separately
- **Spending rule:** the **withdrawal grows with inflation** each year
- **Data source:** Australian historical annual observations from:

`src/main/resources/data/au_return_inflation.csv`

Each CSV row includes a paired observation:

- `nominal_return` (e.g., `0.12` = +12%)
- `inflation` (e.g., `0.03` = +3%)

---

## 2) Why “bootstrap” (and what that means)

“Bootstrap” here means:

- For each simulated year, we **randomly select one historical year** from the CSV
- Selection is **with replacement** (the same year can be selected multiple times)

This is useful because it:

- Avoids assuming returns/inflation are Normal
- Keeps real-world features like **extreme years**
- Preserves the historical **pairing** between return and inflation in each selected year

---

## 3) The update equations (yearly)

For year (t), define:

- (B_t): starting balance at the beginning of year (t)
- (W_t): withdrawal (spending) at the beginning of year (t)
- (R_t): sampled nominal return for year (t)
- (I_t): sampled inflation for year (t)

### 3.1 Apply return and withdrawal

\[
B_{t+1} = B_t * (1 + R_t) - W_t
\]

### 3.2 Increase next year’s withdrawal by inflation

\[
W_{t+1} = W_t * (1 + I_t)
\]

### 3.3 Ruin rule

If (B_{t+1} le 0), the portfolio is **ruined**:

- The balance is floored to 0
- The trial ends early (no more years simulated for that trial)

---

## 4) Worked example (3 years, 1 simulated trial)

Assume:

- Starting balance: (B_1 = 100,000)
- Initial annual withdrawal: (W_1 = 5,000)
- Years: 3

Now we simulate one path by sampling 3 historical (return, inflation) pairs.

### Example sampled outcomes (illustrative)


| Sim Year | Nominal Return (R_t) | Inflation (I_t) |
| -------: | -------------------: | --------------: |
|        1 |          +10% (0.10) |       2% (0.02) |
|        2 |         -20% (-0.20) |       5% (0.05) |
|        3 |           +8% (0.08) |       3% (0.03) |

> Note: In real runs, these are randomly selected from the CSV using the chosen seed.

---

### Year 1

Start:

- \(B_1 = 100,000\)
- \(W_1 = 5,000\)

Apply return and withdraw:

\[
B_2 = 100,000 * 1.10 - 5,000 = 110,000 - 5,000 = 105,000
\]

Update withdrawal using inflation:

[
W_2 = 5,000 * 1.02 = 5,100
]

---

### Year 2

Start:

- \(B_2 = 105,000\)
- \(W_2 = 5,100\)

Apply return and withdraw:

\[
B_3 = 105,000 * 0.80 - 5,100 = 84,000 - 5,100 = 78,900
\]

Update withdrawal using inflation:

\[
W_3 = 5,100 * 1.05 = 5,355
\]

---

### Year 3

Start:

-\(B_3 = 78,900\)

- \(W_3 = 5,355\)

Apply return and withdraw:

\[
B_4 = 78,900 * 1.08 - 5,355 = 85,212 - 5,355 = 79,857
\]

**End of trial results:**

- Ending balance after 3 years: **$79,857**
- Ruin occurred? **No**

That’s one Monte Carlo trial.

---

## 5) Running the simulation from the Dashboard UI

1. Start the application and open the dashboard.
2. Click the **Monte Carlo** tab.
3. Enter the simulation input values:

### UI input fields

- **Starting Balance ($)**
  Starting portfolio value (B_1)
- **Initial Annual Withdrawal ($)**
  First-year spending (W_1)
- **Years**
  Number of years to simulate per trial
- **Trials**
  Number of independent simulated retirement paths to run (sometimes mistyped as “trails”).

  Why it matters:

  - **Reduces randomness / noise in results:** Outputs like **Probability of Ruin** and ending-balance percentiles (P5/P50/P95) are estimated from the simulated trials. More trials generally makes these estimates **more stable** from run to run.
  - **Improves precision:** Ruin probability is computed as `ruinedTrials / totalTrials`. With more trials, each single trial has less impact on the final percentage.
  - **Costs more time:** Runtime is roughly proportional to `years × trials`. Doubling trials will usually take about twice as long.

  Practical guidance:

  - **Quick check:** 200–1,000 trials (fast, noisier)
  - **Typical use:** 2,000–10,000 trials (good stability vs speed)
  - **High confidence:** 20,000+ trials (slower, smoother results)
- **Seed**
  Controls randomness (the starting value used to initialize the random number generator):

  - Same seed + same inputs + same dataset ⇒ **exactly the same results** (reproducible)
  - Different seed ⇒ **exactly the same results** (reproducible)
  - Different seed ⇒ **usually different results** (different sampled years / different trial paths)
  - The seed **does not change the model math** or make results “more accurate” by itself—it only controls which random draws you get.

  **Practical tip:** When comparing two strategies fairly (e.g., different withdrawals), keep the **same seed** so differences in outcomes come from your parameter change, not from a new random draw sequence.

4. Click **Run Simulation**.

---

## 6) “UI worked example” settings (recommended)

Use these as a practical starting point:

- Starting Balance ($): **1,000,000**
- Initial Annual Withdrawal ($): **40,000**
- Years: **30**
- Trials: **5,000**
- Seed: **42**

After you run, you’ll see:

- **Probability of Ruin**
- **Ending Balance Percentiles**: P5, P50 (median), P95

---

## 7) How to interpret the results

### 7.1 Probability of Ruin

If ruin probability is:

- **Low** (e.g., 1–5%): the plan is resilient under this dataset and assumptions
- **Medium** (e.g., 5–15%): the plan is sensitive; consider reducing withdrawal or increasing starting balance
- **High** (e.g., > 15%): withdrawal is likely too aggressive for the horizon (given the dataset)

### 7.2 Percentiles (P5 / P50 / P95)

- **P5 (bad outcome):** only 5% of trials end below this value
- **P50 (median):** “typical” outcome
- **P95 (good outcome):** only 5% of trials end above this value

These show the spread of outcomes—Monte Carlo is most valuable because it exposes uncertainty.

---

## 8) Practical comparison tip

To compare strategies fairly, keep **everything the same** and change only one thing at a time (e.g., initial withdrawal), using the **same seed**:

- Strategy A: withdrawal = 40,000
- Strategy B: withdrawal = 35,000
  Same years/trials/seed ⇒ results are easier to compare.

---

## Appendix: Glossary

- **Trial:** one simulated retirement path (sequence of yearly outcomes)
- **Horizon:** number of years in a trial
- **Bootstrap:** random sampling of historical years with replacement
- **Ruin:** balance reaches 0 before the horizon ends
