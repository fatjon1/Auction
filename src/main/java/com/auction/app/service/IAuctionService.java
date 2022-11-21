package com.auction.app.service;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;

import java.util.List;

public interface IAuctionService {

    void createAuction(CreateAuctionDTO auctionDTO);
    List<Auction> getLatest();
    List<Auction> getFeatured();
}
