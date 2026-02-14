package com.reedmanit.retirementmaths.service;

import com.reedmanit.retirementmaths.data.DrawDownParameters;
import com.reedmanit.retirementmaths.data.MonteCarloParameters;
import com.reedmanit.retirementmaths.data.OptimalSpendingInAustraliaParameters;
import com.reedmanit.retirementmaths.data.StartingBalanceParameters;
import org.springframework.stereotype.Service;

import com.reedmanit.retirementmaths.service.MonteCarloDrawdownService;
import com.reedmanit.retirementmaths.montecarlo.MonteCarloResult;

@Service
public class RetirementMathsService {
    
    private final Drawdown drawdown;
    private final MonteCarloDrawdownService monteCarloDrawdownService;
    public RetirementMathsService(Drawdown drawdown, MonteCarloDrawdownService monteCarloDrawdownService ) {
        this.drawdown = drawdown;
        this.monteCarloDrawdownService = monteCarloDrawdownService;
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

    public double calculateOptimalSpendingInAustralia(OptimalSpendingInAustraliaParameters parameters) {

        if (parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        return drawdown.calculateYaariSpendingAustralia(parameters.getStartingBalance(), parameters.getSubjectiveDiscountRate(), parameters.getAge());
    }

    public MonteCarloResult simulateMonteCarloBootstrapAu(MonteCarloParameters parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        return monteCarloDrawdownService.simulateBootstrapAu(
                parameters.getStartingBalance(),
                parameters.getInitialWithdrawal(),
                parameters.getYears(),
                parameters.getTrials(),
                parameters.getSeed()
        );
    }
}
