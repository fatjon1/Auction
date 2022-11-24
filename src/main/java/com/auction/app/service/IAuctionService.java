package com.auction.app.service;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;

import java.util.List;
import java.util.UUID;

public interface IAuctionService {

    void createAuction(CreateAuctionDTO auctionDTO);
    List<Auction> getLatest();
    List<Auction> getFeatured();
    public List<Auction> getAllAuctions();

    Auction getById(UUID id);
}
