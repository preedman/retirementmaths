package com.reedmanit.retirementmaths.controller;

import com.reedmanit.retirementmaths.data.DrawDownParameters;
import com.reedmanit.retirementmaths.service.DrawdownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    private final DrawdownService drawdownService;

    public DashboardController(DrawdownService drawdownService) {
        this.drawdownService = drawdownService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Initialize an empty parameters object for the form
        model.addAttribute("drawdownParams", new DrawDownParameters(0.05, 0.02, 300000, 30000));
        return "dashboard/dashboard";
    }

    @PostMapping("/calculateDrawdown")
    public String calculateDrawdown(@ModelAttribute("drawdownParams") DrawDownParameters parameters, Model model) {
        double time = drawdownService.calculateDrawdownTime(parameters);
        
        String result;
        if (time == Double.POSITIVE_INFINITY) {
            result = "Sustainable (Money lasts forever!)";
        } else {
            result = String.format("%.2f years", time);
        }
        
        model.addAttribute("drawdownResult", result);
        return "dashboard/dashboard";
    }
}
