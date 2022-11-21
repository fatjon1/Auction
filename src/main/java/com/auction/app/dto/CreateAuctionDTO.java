package com.auction.app.dto;

import java.time.LocalDateTime;

import com.auction.app.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionDTO {
	
	private String title;
	
	private double startingPrice;
	
	private LocalDateTime endDate;

	private Category category;
	
	private String description;


	public boolean isDataValid() {
		if(title.isEmpty() || description.isEmpty() || startingPrice<=0 || category!=null || endDate==null)
			return false;
		
		return true;
	}
	

	
}
