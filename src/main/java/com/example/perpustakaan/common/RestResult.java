package com.example.perpustakaan.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult implements Serializable {

    private static final long serialVersionUID = -2850662391791920L;
    private Object data;

    private long rows;

    private String status = StatusCode.OPERATION_SUCCESS;

    public RestResult(Object data){
        this.data = data;
    }

    public RestResult(Object data, long rows){
        this.data = data;
        this.rows = rows;
    }

    public RestResult(Object data, String status) {
        this.data = data;
        this.status = status;
    }

    public RestResult(String status) {
        this.status = status;
    }
}
