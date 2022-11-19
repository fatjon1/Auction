package com.auction.app.service;

import com.auction.app.model.Auction;

import java.util.List;

public interface IAuctionService {
    List<Auction> getLatest();
    List<Auction> getFeatured();
}
