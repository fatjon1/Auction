package com.auction.app.service;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;
import com.auction.app.model.User;
import com.auction.app.repository.AuctionRepository;
import com.auction.app.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
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
        Auction auction = Auction.builder().build();
        auction.setTitle(auctionDTO.getTitle());
        auction.setStartPrice(auctionDTO.getStartingPrice());
        auction.setCreatedDate(LocalDateTime.now());
        auction.setEndDate(LocalDateTime.now().plusDays(1));
        auction.setDescription(auctionDTO.getDescription());
        auction.setAuthorId(user.getId());
        auction.setCategoryId(auctionDTO.getCategory().getId());
        auction.setImage("/images/" + "Book.png");
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

    @Override
    public void deleteById(UUID id) {
        auctionRepository.deleteById(id);
    }

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public Page<Auction> findPaginated(Pageable pageable) {
        List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Auction> list;

        if (auctions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, auctions.size());
            list = auctions.subList(startItem, toIndex);
        }

        Page<Auction> auctionPage
                = new PageImpl<Auction>(list, PageRequest.of(currentPage, pageSize), auctions.size());

        return auctionPage;
    }

}
