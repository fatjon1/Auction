package com.auction.app.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="bid")
public class Bid extends BaseEntity {

	@NotNull
	@Column(name = "auction_id",  nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	protected UUID auctionId;

	@ManyToOne
	@JoinColumn(name="auction_id", insertable = false, updatable = false)
	private Auction auction;

	@NotNull
	@Column(name = "customer_id")
	@Type(type = "org.hibernate.type.UUIDCharType")
	protected UUID customerId;

	@ManyToOne
	@JoinColumn(name="buyer_id", insertable = false, updatable = false)
	private User customer;
	
	@Column(name="price")
	private double price;

}
