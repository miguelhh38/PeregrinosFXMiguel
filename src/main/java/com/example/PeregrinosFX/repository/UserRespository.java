package com.example.PeregrinosFX.repository;

import com.example.PeregrinosFX.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    User findByUsuario(String usuario);

}
