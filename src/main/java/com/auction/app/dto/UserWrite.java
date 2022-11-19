package com.auction.app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWrite {
    protected String username;
    protected String password;
    protected String repeatedPassword;
}

