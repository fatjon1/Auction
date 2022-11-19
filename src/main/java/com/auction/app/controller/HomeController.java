package com.auction.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String mainIndex(Model model) {
        // model.addAttribute("projects", auctionRepository.findByActive(1));
        // model.addAttribute("completedProjects",completedAuctionRepository.findAll() );
        return "Index";
    }
}
