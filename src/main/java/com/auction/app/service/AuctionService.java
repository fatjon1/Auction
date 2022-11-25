package com.auction.app.service;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;
import com.auction.app.model.User;
import com.auction.app.repository.AuctionRepository;
import com.auction.app.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionService implements IAuctionService {
    @NonNull protected AuctionRepository auctionRepository;
    @NonNull protected UserRepository userRepository;

    @Override
    public void createAuction(CreateAuctionDTO auctionDTO) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username);
        Auction auction = new Auction();
        auction.setTitle(auctionDTO.getTitle());
        auction.setStartPrice(auctionDTO.getStartingPrice());
        auction.setCreatedDate(LocalDateTime.now());
        auction.setEndDate(LocalDateTime.now().plusDays(1));
        auction.setDescription(auctionDTO.getDescription());
        auction.setAuthorId(user.getId());
        auction.setCategoryId(auctionDTO.getCategory().getId());
        auction.setImage("/images/" + auctionDTO.getImage());
        auctionRepository.save(auction);

    }

    @Override
    public List<Auction> getLatest() {
        Page<Auction> auctions = auctionRepository.getAllByDeletedFalse(PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdDate")));
        return auctions.getContent();
    }

    @Override
    public List<Auction> getFeatured() {
        return auctionRepository.getByFeaturedTrueAndDeletedFalseOrderByCreatedAtDesc();
    }

    @Override
    public List<Auction> getAllAuctions(){
       return (List<Auction>) auctionRepository.findAll();
    }

    @Override
    public Auction getById(UUID id) {
       return auctionRepository.findById(id).get();
    }


}
