package com.auction.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auction.app.model.Auction;

public interface AuctionRepository extends CrudRepository<Auction, UUID> {
    Page<Auction> getAllByDeletedFalse(Pageable pageable);
    List<Auction> getByFeaturedTrueAndDeletedFalseOrderByCreatedAtDesc();
}
