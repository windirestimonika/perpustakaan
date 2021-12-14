package com.example.perpustakaan.service;

import com.example.perpustakaan.dao.BaseDAO;
import com.example.perpustakaan.dao.LoanDAO;
import com.example.perpustakaan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends BaseService<Loan> {

    @Autowired
    private LoanDAO dao;

    @Override
    protected BaseDAO<Loan> getDAO() {
        return dao;
    }
}
