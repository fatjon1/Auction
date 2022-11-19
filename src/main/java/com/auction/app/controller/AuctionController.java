package com.auction.app.controller;

import com.auction.app.service.IAuctionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {
    @NonNull protected IAuctionService auctionService;

    @GetMapping("")
    public String index() {
        return "client/auctions-page";
    }
}
