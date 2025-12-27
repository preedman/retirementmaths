package com.reedmanit.retirementmaths.data;

public class StartingBalanceParameters {

    private double realRateOfReturn;
    private double inflation;
    private double desiredTimeInYears;
    private double initialWithdrawal;

    public StartingBalanceParameters(double realRateOfReturn, double inflation, double desiredTimeInYears, double initialWithdrawal) {
        this.setRealRateOfReturn(realRateOfReturn);
        this.setInflation(inflation);
        this.setDesiredTimeInYears(desiredTimeInYears);
        this.setInitialWithdrawal(initialWithdrawal);
    }

    public double getRealRateOfReturn() {
        return realRateOfReturn;
    }

    public void setRealRateOfReturn(double realRateOfReturn) {
        this.realRateOfReturn = realRateOfReturn;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public double getDesiredTimeInYears() {
        return desiredTimeInYears;
    }

    public void setDesiredTimeInYears(double desiredTimeInYears) {
        this.desiredTimeInYears = desiredTimeInYears;
    }

    public double getInitialWithdrawal() {
        return initialWithdrawal;
    }

    public void setInitialWithdrawal(double initialWithdrawal) {
        this.initialWithdrawal = initialWithdrawal;
    }
}
