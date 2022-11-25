package com.auction.app.startuprunners;

import com.auction.app.dto.CategoryWrite;
import com.auction.app.dto.UserWrite;
import com.auction.app.enums.AuctionStatus;
import com.auction.app.model.Auction;
import com.auction.app.model.Category;
import com.auction.app.repository.CategoryRepository;
import com.auction.app.service.AuctionService;
import com.auction.app.service.CategoryService;
import com.auction.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
@RequiredArgsConstructor
public class RegisterAdminRunner implements CommandLineRunner {
    @NonNull protected UserService userService;
    @NonNull protected CategoryService categoryService;

    @NonNull protected AuctionService auctionService;

    @Override
    public void run(String... args) throws Exception {
        UserWrite admin = UserWrite.builder()
                .username("admin")
                .password("admin")
                .repeatedPassword("admin")
                .build();
        String result = userService.register(admin, "admin");
        log.info(String.format("Admin creation result: %s", result));

        Category category = Category.builder()
                .title("Electronics")
                .build();
        categoryService.save(category);

        Auction auction1 = Auction.builder()
                .title("MacBook Pro")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("macbook.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Laptop")
                .featured(false)
                .startPrice(100)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction1);
        Auction auction2 = Auction.builder()
                .title("Lenovo")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("lenovo.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Laptop")
                .featured(false)
                .startPrice(200)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction2);
        Auction auction3 = Auction.builder()
                .title("Hp")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("hp.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Laptop")
                .featured(false)
                .startPrice(120)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction3);
        Auction auction4 = Auction.builder()
                .title("iphone")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("iphone.png")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Smartphone")
                .featured(false)
                .startPrice(400)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction4);
        Auction auction5 = Auction.builder()
                .title("Samsung")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("samsung.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Smartphone")
                .featured(false)
                .startPrice(300)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction5);
        Auction auction6 = Auction.builder()
                .title("Panasonic")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("panasonic.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Tv")
                .featured(false)
                .startPrice(700)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction6);
        Auction auction7 = Auction.builder()
                .title("Sony")
                .author(userService.findUserByUsername("admin"))
                .authorId(userService.findUserByUsername("admin").getId())
                .image("sony.jpg")
                .createdDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .category(categoryService.getCategories().stream().findFirst().get())
                .categoryId(categoryService.getCategories().stream().findFirst().get().getId())
                .deleted(false)
                .description("Tv")
                .featured(false)
                .startPrice(500)
                .status(AuctionStatus.INSERTED)
                .build();
        auctionService.save(auction7);
    }
}
