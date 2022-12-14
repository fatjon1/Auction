package com.auction.app.controller;

import com.auction.app.service.IAuctionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    @NonNull protected IAuctionService auctionService;

    @GetMapping("")
    public String mainIndex(Model model) {
        model.addAttribute("latestAuctions", auctionService.getLatest());
        model.addAttribute("featuredAuctions", auctionService.getFeatured());
        return "client/index";
    }
}
