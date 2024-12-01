package com.project.catchspot.JWT;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JwtRequest {
    private String email;
    private String password;
}