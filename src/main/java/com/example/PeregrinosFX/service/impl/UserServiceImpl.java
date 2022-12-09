package com.example.PeregrinosFX.service.impl;

import com.example.PeregrinosFX.bean.User;
import com.example.PeregrinosFX.repository.UserRespository;
import com.example.PeregrinosFX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public boolean verificarcredenciales(String usuario, String contrasenia) {
        User user = findByUsuario(usuario);
        if (user == null) {
            return false;
        } else {
            if ((contrasenia.equals(user.getContrasenia()))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public User findByUsuario(String username) {
        return userRespository.findByUsuario(username);
    }

    @Override
    public boolean comprobarUsuario(String usuario) {
        User user = findByUsuario(usuario);
        if ( user != null) {
            return true;
        } else
            return false;
    }

    @Override
    public User addUser(User user) {
        return userRespository.save(user);
    }


    @Override
    public User save(User entity) {
        return userRespository.save(entity);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteInBatch(List<User> entities) {

    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


}
