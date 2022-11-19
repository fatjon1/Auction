package com.auction.app.startuprunners;

import com.auction.app.dto.UserWrite;
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

    @Override
    public void run(String... args) throws Exception {
        UserWrite admin = UserWrite.builder()
                .username("admin")
                .password("&pass123")
                .repeatedPassword("&pass123")
                .build();
        String result = userService.register(admin, "admin");
        log.info(String.format("Admin creation result: %s", result));
    }
}
