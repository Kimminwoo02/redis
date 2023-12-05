package com.example.redisjwt.repository;

import com.example.redisjwt.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser,Long> {

}
