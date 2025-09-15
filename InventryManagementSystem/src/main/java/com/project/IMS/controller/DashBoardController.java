package com.project.IMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.IMS.service.DashBoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashBoardController {
	@GetMapping("/home")
	public String homePage() 
	{
		return "home";
	}
    @Autowired
    private DashBoardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,HttpServletRequest req) {
        // Assume logged-in user ID is stored in session
    	Integer userId= Integer.parseInt(req.getSession(false).getAttribute("userId").toString());
        String username = req.getSession(false).getAttribute("username").toString();

        model.addAttribute("username", username);
        model.addAttribute("products", dashboardService.getProductsByUser(userId));
        model.addAttribute("suppliers", dashboardService.getSuppliersByUser(userId));
        model.addAttribute("customers", dashboardService.getCustomersByUser(userId));
        model.addAttribute("orders", dashboardService.getOrdersByUser(userId));
        model.addAttribute("inventory", dashboardService.getInventoryByUser(userId));
        model.addAttribute("logs", dashboardService.getLogsByUser(userId));

        // Chart Data
        model.addAttribute("inventoryLabelsJson", dashboardService.getInventoryLabelsJson(userId));
        model.addAttribute("inventoryDataJson", dashboardService.getInventoryDataJson(userId));
        model.addAttribute("orderCountsJson", dashboardService.getOrderCountsJson(userId));

        return "Dashboard";
}
}
