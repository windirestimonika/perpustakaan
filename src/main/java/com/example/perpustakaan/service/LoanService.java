package com.example.perpustakaan.service;

import com.example.perpustakaan.dao.BaseDAO;
import com.example.perpustakaan.dao.LoanDAO;
import com.example.perpustakaan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoanService extends BaseService<Loan> {

    @Autowired
    private LoanDAO dao;

    @Override
    protected BaseDAO<Loan> getDAO() {
        return dao;
    }

    @Transactional
    public Loan save(Loan entity){
        entity.setLoanDate(new Date());

        return dao.save(entity);
    }

    @Transactional
    public Loan uodate(Loan entity){
        if (entity.getId() != null){
            Loan reference = getDAO().findReference(entity.getId());

            reference.setReturnDate(entity.getReturnDate() != null
                    ? entity.getReturnDate()
                    : new Date());

            reference.setStatus(reference.getStatus().equals(Loan.StatusLoan.BORROWED)
                    ? Loan.StatusLoan.RETURNED
                    : reference.getStatus());

            entity.setLoanDate(reference.getLoanDate());
            entity.setReturnDate(reference.getReturnDate());
            entity.setStatus(reference.getStatus());
            return entity;
        }
        return null;
    }
}
