package com.reedmanit.retirementmaths.data;

public class OptimalSpendingInAustraliaParameters {

    private double startingBalance;
    private double subjectiveDiscountRate;
    private double age;

    public OptimalSpendingInAustraliaParameters(double startingBalance, double subjectiveDiscountRate, double age) {
        this.setStartingBalance(startingBalance);
        this.setSubjectiveDiscountRate(subjectiveDiscountRate);
        this.setAge(age);

    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public double getSubjectiveDiscountRate() {
        return subjectiveDiscountRate;
    }

    public void setSubjectiveDiscountRate(double subjectiveDiscountRate) {
        this.subjectiveDiscountRate = subjectiveDiscountRate;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
