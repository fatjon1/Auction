package com.auction.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.auction.app.model.Auction;
import com.auction.app.model.Bid;

public interface BidRepository extends CrudRepository<Bid, UUID> {
	
	List<Bid> findByAuction(Auction auction);
	/*List<Bid> findByAuctionOrderByBidOnDesc(Auction auction);
	List<Bid> findByAuctionOrderByBidPriceDesc(Auction auction);
	List<Bid> findByAuctionOrderByBidPriceAsc(Auction auction);*/
	
}
