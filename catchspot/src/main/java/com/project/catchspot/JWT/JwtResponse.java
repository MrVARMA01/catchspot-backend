package com.project.catchspot.JWT;

import com.project.catchspot.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private long userId;
    private String jwtToken;
}
