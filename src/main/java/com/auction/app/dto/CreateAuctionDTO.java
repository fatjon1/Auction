package com.auction.app.dto;

import java.time.LocalDateTime;

import com.auction.app.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionDTO {
	
	private String title;
	
	private double startingPrice;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME , pattern = ("dd/MM/yyyy HH:mm"))
	private LocalDateTime endingDate;

	private Category category;
	
	private String description;

	private String image;


	public boolean isDataValid() {
		if(title.isEmpty() || description.isEmpty() || startingPrice<=0 || category!=null || endingDate==null)
			return false;
		
		return true;
	}
	

	
}
