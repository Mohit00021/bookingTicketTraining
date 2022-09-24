package com.trainbooking.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    public String message ="";

    private Object data;

    public ResponseDto(Object data){this.data = data;}

    public ResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseDto setData(Object data) {
        this.data = data;
        return this;
    }
}