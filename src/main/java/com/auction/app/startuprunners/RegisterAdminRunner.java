package com.auction.app.startuprunners;

import com.auction.app.dto.CategoryWrite;
import com.auction.app.dto.UserWrite;
import com.auction.app.model.Category;
import com.auction.app.repository.CategoryRepository;
import com.auction.app.service.CategoryService;
import com.auction.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class RegisterAdminRunner implements CommandLineRunner {
    @NonNull protected UserService userService;
    @NonNull protected CategoryService categoryService;

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
    }
}
