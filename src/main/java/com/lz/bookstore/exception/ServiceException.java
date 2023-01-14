package com.lz.bookstore.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
public class ServiceException extends  RuntimeException{
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
