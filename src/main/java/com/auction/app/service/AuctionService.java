package com.auction.app.service;

import com.auction.app.model.Auction;
import com.auction.app.repository.AuctionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService implements IAuctionService {
    @NonNull protected AuctionRepository auctionRepository;

    @Override
    public List<Auction> getLatest() {
        Page<Auction> auctions = auctionRepository.getAllByDeletedFalse(PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdAt")));
        return auctions.getContent();
    }

    @Override
    public List<Auction> getFeatured() {
        return auctionRepository.getByFeaturedTrueAndDeletedFalseOrderByCreatedAtDesc();
    }
}
