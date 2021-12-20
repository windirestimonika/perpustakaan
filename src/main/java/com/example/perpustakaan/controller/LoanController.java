package com.example.perpustakaan.controller;

import com.example.perpustakaan.common.RestResult;
import com.example.perpustakaan.common.StatusCode;
import com.example.perpustakaan.entity.Loan;
import com.example.perpustakaan.service.LoanService;
import com.example.perpustakaan.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("Loans")
public class LoanController extends BaseController {
    @Autowired
    private LoanService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult find(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "offset") int offset,
                           @RequestParam(value = "limit") int limit) throws JsonProcessingException{

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        Long rows = service.count(loan);

        return new RestResult(rows > 0 ? service.find(loan, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "by-date")
    public RestResult findByDate(@RequestParam(value = "param", required = false) String param,
                                 @RequestParam(value = "start-date") String startDate,
                                 @RequestParam(value = "end-date") String endDate) throws JsonProcessingException{

        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        result.setData(service.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)));
        result.setRows((long) service.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)).size());

        return result;
    }

    @PreAuthorize("permitAll")
    @PostMapping
    public RestResult save(@RequestBody Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }

        return result;
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(@RequestBody Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.update(entity));
        }
        return result;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id){
        return new RestResult(service.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
