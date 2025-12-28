package com.reedmanit.retirementmaths.controller;

import com.reedmanit.retirementmaths.data.DrawDownParameters;
import com.reedmanit.retirementmaths.data.OptimalSpendingInAustraliaParameters;
import com.reedmanit.retirementmaths.data.StartingBalanceParameters;
import com.reedmanit.retirementmaths.service.RetirementMathsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    private final RetirementMathsService retirementMathsService;

    public DashboardController(RetirementMathsService retirementMathsService) {
        this.retirementMathsService = retirementMathsService;
    }



    @ModelAttribute("drawdownParams")
    public DrawDownParameters defaultDrawDownParameters() {
        return new DrawDownParameters(0.05, 0.02, 300000, 30000);
    }

    @ModelAttribute("startingBalanceParams")
    public StartingBalanceParameters defaultStartingBalanceParameters() {
        return new StartingBalanceParameters(0.05, 0.02, 25, 50000);
    }

    @ModelAttribute("optimalSpendingParams")
    public OptimalSpendingInAustraliaParameters defaultOptimalSpendingParams() {
        return new OptimalSpendingInAustraliaParameters(700000, 0.02, 65);
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard/dashboard";
    }

    @PostMapping("/calculateDrawdown")
    public String calculateDrawdown(@ModelAttribute("drawdownParams") DrawDownParameters parameters, Model model) {
        double time = retirementMathsService.calculateDrawdownTime(parameters);

        String result;
        if (time == Double.POSITIVE_INFINITY) {
            result = "Sustainable (Money lasts forever!)";
        } else {
            result = String.format("%.2f years", time);
        }

        model.addAttribute("drawdownResult", result);
        model.addAttribute("activeTab", "overview");
        return "dashboard/dashboard";
    }

    @PostMapping("/calculateStartingBalance")
    public String calculateStartingBalance(@ModelAttribute("startingBalanceParams") StartingBalanceParameters params, Model model) {
        double result = retirementMathsService.calculateStartingBalance(params);
        model.addAttribute("startingBalanceResult", String.format("%.2f", result));
        model.addAttribute("activeTab", "starting-balance");
        return "dashboard/dashboard";
    }

    @PostMapping("/calculateOptimalSpending")
    public String calculateOptimalSpending(@ModelAttribute("optimalSpendingParams") OptimalSpendingInAustraliaParameters params, Model model) {
        double result = retirementMathsService.calculateOptimalSpendingInAustralia(params);
        model.addAttribute("optimalSpendingResult", String.format("%.2f", result));
        model.addAttribute("activeTab", "spending");
        return "dashboard/dashboard";
    }
}
