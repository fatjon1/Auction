package com.auction.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BidWrite {
    protected UUID auctionId;
    protected Double price;
}
