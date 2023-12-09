package com.example.redisjwt.dto;



import com.example.redisjwt.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;

    private String name;

    private String password;

    private String nickname;

    private boolean checkVal;

    private Set<Authority> authoritySet;
}
