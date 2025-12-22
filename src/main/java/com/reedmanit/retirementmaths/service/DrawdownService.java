package com.reedmanit.retirementmaths.service;

import com.reedmanit.retirementmaths.data.DrawDownParameters;
import org.springframework.stereotype.Service;

@Service
public class DrawdownService {
    
    private final Drawdown drawdown;
    public DrawdownService(Drawdown drawdown) {
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
}
