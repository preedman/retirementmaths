package com.reedmanit.retirementmaths.data;


public class DrawDownParameters {

    private double realRateOfReturn;
    private double inflation;
    private double startingBalance;
    private double initialWithdrawal;

    public DrawDownParameters() {
    }

    public DrawDownParameters(double realRateOfReturn, double inflation, double startingBalance, double initialWithdrawal) {
        this.setRealRateOfReturn(realRateOfReturn);
        this.setInflation(inflation);
        this.setStartingBalance(startingBalance);
        this.setInitialWithdrawal(initialWithdrawal);
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

    public double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public double getInitialWithdrawal() {
        return initialWithdrawal;
    }

    public void setInitialWithdrawal(double initialWithdrawal) {
        this.initialWithdrawal = initialWithdrawal;
    }

    public double getRealRateOfReturn() {
        return realRateOfReturn;
    }
}
