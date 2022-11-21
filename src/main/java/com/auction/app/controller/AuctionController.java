package com.auction.app.controller;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;
import com.auction.app.service.IAuctionService;
import com.auction.app.service.ICategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {
    @NonNull protected IAuctionService auctionService;
    @NonNull protected ICategoryService categoryService;


    @GetMapping("")
    public String index() {

        return "client/auctions-page";
    }


    @GetMapping("/new")
    public String newAuctionPage(Model model){
        if (!model.containsAttribute("auction")) {
            // Objekti bosh duhet per te mbushur strukturen e formes <form>
            CreateAuctionDTO auction = new CreateAuctionDTO();

            model.addAttribute("auction", auction);
        }
        model.addAttribute("categories", categoryService.getCategories());
        return "client/create-auction";
    }

   /* @PostMapping("/new")
    public String newAuctionPage(@ModelAttribute("auctionDTO") CreateAuctionDTO auctionDTO){
        auctionService.createAuction(auctionDTO);
        return "client/index";

    }*/

    @PostMapping("/save")
    public RedirectView saveCourse(@ModelAttribute @Valid CreateAuctionDTO auctionDTO, Errors errors, RedirectAttributes modelMap) {
        if (errors.hasErrors()) {
            String error = String.format("Object is not valid: %s",
                    errors.getAllErrors().stream()
                            .map(o -> String.format("%s: %s", ((FieldError) o).getField(), o.getDefaultMessage()))
                            .collect(Collectors.joining(",")));
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("message", error);
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("model", auctionDTO);
            return new RedirectView("/auctions/new");
        }
        auctionService.createAuction(auctionDTO);
        modelMap.addFlashAttribute("message", auctionDTO.getTitle() == null ? "Course created successfully" : "Course updated successfully");
        return new RedirectView("/auctions");
    }

}
