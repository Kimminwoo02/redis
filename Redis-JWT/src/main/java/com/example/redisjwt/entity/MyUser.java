package com.example.redisjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;

    @JsonIgnore
    private String password;
    private String nickname;
    @JsonIgnore
    private String checkVal;

    @ManyToMany
    @JoinTable(
            name="user_authority",
            joinColumns = {@JoinColumn(name="userId",referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "authorityId",referencedColumnName = "authorityId")}
    )
    private Set<Authority> authorityList;

}
