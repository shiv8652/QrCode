package com.example.qrcode.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.qrcode.dtos.QRCodeDto;
import com.example.qrcode.responses.QRCodeApiResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGenerator {

	private String QR_CODE_IMAGE_PATH = "D:\\logs\\QR_CODE\\QRCode.png";
	
	//to download qrcode image
	public  void generateQRCodeImageAndDownload(String text, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);  
    }
	
	//qrcode image String byteArray
	public  QRCodeApiResponse getQRCodeImageInStringByteArray(int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(Long.toString(generateRandom(12)), BarcodeFormat.QR_CODE, width, height);	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray();
	    QRCodeApiResponse qrCodeApiResponse = new QRCodeApiResponse(new QRCodeDto(new String(new Base64().encode(pngData))), HttpStatus.CREATED, "Created Succesfully", false);
	    return qrCodeApiResponse;
	}
	
	private long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
}
