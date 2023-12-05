package com.example.redisjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "authority")
public class Authority {
    @Id
    @GeneratedValue
    private Long authorityId;
    @Column(length = 50)
    private String authorityName;
}
