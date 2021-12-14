package com.example.perpustakaan.service;

import com.example.perpustakaan.dao.BaseDAO;
import com.example.perpustakaan.dao.BookDAO;
import com.example.perpustakaan.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseService<Book>{

    @Autowired
    private BookDAO dao;

    @Override
    protected BaseDAO<Book> getDAO() {
        return dao;
    }
}
