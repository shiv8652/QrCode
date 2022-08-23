package com.example.qrcode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.qrcode.responses.QRCodeApiResponse;

@RestController
@CrossOrigin(origins = "*")
public interface QRCodeController {
	@GetMapping(value = "/generateAndDownloadQRCode/{codeText}/{width}/{height}")
	public void download(@PathVariable String codeText, @PathVariable Integer width, @PathVariable Integer height)
			throws Exception;

	@GetMapping(value = "/generateQRCode/{codeText}/{width}/{height}")
	public ResponseEntity<QRCodeApiResponse> generateQRCode(@PathVariable Integer width, @PathVariable Integer height)
			throws Exception;
}
