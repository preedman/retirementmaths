# Nominal Return Definition (Balanced 60/40 AU Portfolio)

This project’s `nominal_return` is defined as the **annual nominal total return** of a **balanced portfolio** consisting of:

- **60% Australian equities**: *All Ordinaries Accumulation Index* (dividends reinvested)
- **40% Australian government bonds**: *Australian Government Bonds (All Maturities) Total Return*
- **Rebalanced annually** back to 60/40 at the start of each year (implicit in the calculation)

“Nominal” means returns are **not adjusted for inflation**. Inflation is modelled separately.

---

## 1) Inputs (annual returns)

For each calendar year \(t\), you have:

- \(R^{eq}_t\): annual **equity total return** (e.g., `0.12` = +12%)
- \(R^{gov}_t\): annual **government bond total return** (e.g., `0.04` = +4%)

These are **annual returns**, not index levels and not yields.

---

## 2) Portfolio nominal return calculation

Let the target weights be:

- \(w_{eq} = 0.60\)
- \(w_{gov} = 0.40\)

Then the portfolio nominal return for year \(t\) is:

\[
R^{port}_t = w_{eq}\cdot R^{eq}_t + w_{gov}\cdot R^{gov}_t
\]

i.e.

\[
R^{port}_t = 0.60 \cdot R^{eq}_t + 0.40 \cdot R^{gov}_t
\]

This is equivalent to an **annual rebalance** back to 60/40.

---

## 3) Worked example

Assume for year \(t\):

- All Ords Accumulation total return: \(R^{eq}_t = 0.10\) (10%)
- AU gov bonds all-maturities total return: \(R^{gov}_t = 0.04\) (4%)

Compute:

\[
R^{port}_t = 0.60\cdot 0.10 + 0.40\cdot 0.04
= 0.06 + 0.016
= 0.076
\]

So:

- `nominal_return = 0.076` → **7.6%** for that year.

---

## 4) Notes / common pitfalls

- Use **Accumulation / Total Return** for equities (includes dividends). Price-only equity indices understate returns.
- Use **Total Return** for bonds (includes coupon income). A **yield** series (e.g., “10-year yield”) is *not* an annual return series.
- Keep conventions consistent across assets and inflation: **calendar years**, **AUD**, and matching year boundaries.
- Inflation is **not** subtracted here. If you later need a real return, compute it separately (e.g., Fisher relationship).