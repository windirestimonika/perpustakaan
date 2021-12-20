package com.example.perpustakaan.service;

import com.example.perpustakaan.common.RestResult;
import com.example.perpustakaan.common.StatusCode;
import com.example.perpustakaan.dao.BaseDAO;
import com.example.perpustakaan.dao.UserDAO;
import com.example.perpustakaan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }

    @Transactional
    public User register(User param, User.Role role){
        User reference = dao.findOne(new User(param.getUserName()));

        if (reference != null){
            return null;
        }else {
            param.setRole(role);
            param.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));

            dao.save(param);

            return param;
        }
    }

    @Transactional
    public RestResult login(User param){
        RestResult result = new RestResult(StatusCode.PASSWORD_OR_USER_NOT_REGISTERED);

        User currentUSer = dao.findOne(param);

        if (currentUSer == null){
            return result;
        }else if (currentUSer.getPassword() != null && BCrypt.checkpw(param.getPassword(), currentUSer.getPassword())){
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(currentUSer.getUserName(), currentUSer.getPassword(), new ArrayList<>());

            currentUSer.setToken(jwtTokenService.generateToken(userDetails));

            result.setData(currentUSer);
            result.setStatus(StatusCode.LOGIN_SUCCESS);
        }else {
            result.setStatus(StatusCode.LOGIN_FAILED);
        }

        return result;
    }
}
