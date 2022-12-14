package com.auction.app.controller;

import com.auction.app.dto.BidWrite;
import com.auction.app.dto.CreateAuctionDTO;
import com.auction.app.model.Auction;
import com.auction.app.model.Bid;
import com.auction.app.model.User;
import com.auction.app.repository.BidRepository;
import com.auction.app.service.IAuctionService;
import com.auction.app.service.ICategoryService;
import com.auction.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.validation.Path;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Auction auction;


        auctionService.createAuction(auctionDTO);
        modelMap.addFlashAttribute("message", auctionDTO.getTitle() == null ? "Course created successfully" : "Course updated successfully");
        return new RedirectView("/auctions");
    }

    @GetMapping ("/{auctionId}")
    public String auctionView(@PathVariable(value = "auctionId") UUID auctionId, Model model) throws IOException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userService.findUserByUsername(username);


        Auction auction=auctionService.getById(auctionId);
        BidWrite bid = new BidWrite();

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
            model.addAttribute("bid", bid);
            return "AuctionView";

       // }


       // return "CustomeError";
    }
    @DeleteMapping("/{auctionId}")
    public String auctionDelete(@PathVariable(value = "auctionId") UUID auctionId, Model model) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        if (user.getRoles().contains("admin")){
            auctionService.deleteById(auctionId);
        }
        return "redirect:/auctions";
    }

    @GetMapping("/listAuctions")
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Auction> auctionPage = auctionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("auctionPage", auctionPage);

        int totalPages = auctionPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listAuctions.html";
    }

    @PostMapping("/add-bid")
    public String postBid(@ModelAttribute BidWrite bidWrite, Model model) {
        System.out.println(model);
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User whoAmI = userService.findUserByUsername(username);
        Auction auction=auctionService.getById(bidWrite.getAuctionId());

        if(auction!=null && whoAmI.getId() != auction.getAuthor().getId()) {
            Bid bid=new Bid();
            bid.setAuctionId(auction.getId());
            bid.setBidOn(LocalDateTime.now());
            bid.setPrice(bidWrite.getPrice());
            bid.setCustomerId(whoAmI.getId());

            bidRepository.save(bid);
            return "redirect:/auctions";
        }
        return "error,Given parameter are not valid";
    }

    @PostMapping("/deleteBid/{bidId}")
    public String deleteBid(@PathVariable(value = "bidId") UUID bidId) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User whoAmI=userService.findUserByUsername(username);

        Bid bid= bidRepository.findById(bidId).get();

        if(bid.getCustomer().getId() == whoAmI.getId()) {
            bidRepository.delete(bid);
            return "Bid Deleted";
        }

        return "Given Parameter are not valid";
    }
}

