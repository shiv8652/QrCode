package com.example.qrcode.responses;

import org.springframework.http.HttpStatus;

import com.example.qrcode.dtos.QRCodeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeApiResponse {
private QRCodeDto qrCodeDto;
private HttpStatus status;
private String message;
private Boolean error;
}
