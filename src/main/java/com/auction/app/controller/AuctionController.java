package com.auction.app.controller;

import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;
import com.auction.app.model.User;
import com.auction.app.repository.BidRepository;
import com.auction.app.service.IAuctionService;
import com.auction.app.service.ICategoryService;
import com.auction.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {
    @NonNull protected IAuctionService auctionService;
    @NonNull protected ICategoryService categoryService;

    @NonNull protected UserService userService;

    @NonNull protected BidRepository bidRepository;


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());

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

    @GetMapping ("/{auctionId}")
    public String auctionView(@PathVariable(value = "auctionId") UUID auctionId, Model model) throws IOException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userService.findUserByUsername(username);


        Auction auction=auctionService.getById(auctionId);

        /*if(auction!=null) {

            CompletedAuction winner=completedAuctionRepository.findByAuction(auction);

            if(auction.getActive()==0 && winner==null)
                return "CustomeError";
*/

            /*List<String> images=new ArrayList<String>();
            for(AuctionImage image : auction.getImages()) {
                File file=new File(image.getPath());
                System.out.println(image.getPath());
                images.add(image.getImageId()+"");
            }*/

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss");
            //System.out.println(auction.getAuctionEndingDate());
            String formatDateTime = auction.getEndDate().format(formatter);
            System.out.println(formatDateTime);

            //model.addAttribute("isOpen", winner!=null);
            //model.addAttribute("winner",winner);
            model.addAttribute("auction", auction);
            //model.addAttribute("images", images);
            model.addAttribute("endDate", formatDateTime);
            model.addAttribute("owner",user.getId()==auction.getAuthorId());
            model.addAttribute("bidPlaced", bidRepository.findByAuction(auction).size());
            return "AuctionView";

       // }


       // return "CustomeError";
    }

}
