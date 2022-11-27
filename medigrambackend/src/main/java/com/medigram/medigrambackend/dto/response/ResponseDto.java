package com.medigram.medigrambackend.dto.response;



import lombok.Data;

@Data
public class ResponseDto {
    private String message;
    private String status;

    public ResponseDto(String message, String status) {
        this.message = message;
        this.status = status;
    }

    
}
