package com.example.perpustakaan.service;

import com.example.perpustakaan.dao.BaseDAO;
import com.example.perpustakaan.dao.UserDAO;
import com.example.perpustakaan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }
}
