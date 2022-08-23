package com.example.qrcode.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.qrcode.controller.QRCodeController;
import com.example.qrcode.responses.QRCodeApiResponse;
import com.example.qrcode.services.QRCodeGenerator;

@Service
public class QRCodeControllerImpl implements QRCodeController {

	@Autowired
	private QRCodeGenerator qrCodeGenerator;

	public void download(@PathVariable String codeText, @PathVariable Integer width, @PathVariable Integer height) throws Exception {
		qrCodeGenerator.generateQRCodeImageAndDownload(codeText, width, height);
	}

	public ResponseEntity<QRCodeApiResponse> generateQRCode(@PathVariable Integer width,@PathVariable Integer height) throws Exception {
		return new ResponseEntity<QRCodeApiResponse>(qrCodeGenerator.getQRCodeImageInStringByteArray( width, height), HttpStatus.OK);
	}

}
