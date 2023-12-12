package com.courseModel.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
}
