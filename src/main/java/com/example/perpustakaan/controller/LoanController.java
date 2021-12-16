package com.example.perpustakaan.controller;

import com.example.perpustakaan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Loans")
public class LoanController extends BaseController {
    @Autowired
    private LoanService service;
}
