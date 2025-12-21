package com.reedmanit.retirementmaths.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Maps to /WEB-INF/views/dashboard/dashboard.jsp
        return "dashboard/dashboard";
    }
}
