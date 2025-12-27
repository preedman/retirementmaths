package com.reedmanit.retirementmaths.service;

import com.reedmanit.retirementmaths.data.DrawDownParameters;
import com.reedmanit.retirementmaths.data.StartingBalanceParameters;
import org.springframework.stereotype.Service;

@Service
public class RetirementMathsService {
    
    private final Drawdown drawdown;
    public RetirementMathsService(Drawdown drawdown) {
        this.drawdown = drawdown;
    }

    public double calculateDrawdownTime(DrawDownParameters parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        return drawdown.calculateDrawdownTime(
                parameters.getRealRateOfReturn(),
                parameters.getInflation(),
                parameters.getStartingBalance(),
                parameters.getInitialWithdrawal()
        );
    }

    public double calculateStartingBalance(StartingBalanceParameters parameters) {

        if (parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        return drawdown.calculateRequiredStartingBalance(parameters.getRealRateOfReturn(), parameters.getInflation(), parameters.getDesiredTimeInYears(), parameters.getInitialWithdrawal());

    }
}
