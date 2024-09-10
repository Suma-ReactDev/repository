package io.demoprojects.dak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demoprojects.dak.model.Upload;
import io.demoprojects.dak.service.IRequisitionService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	 @Autowired
	 private IRequisitionService requisitionService;

	 
//    @GetMapping("/{id}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
//        Upload file = requisitionService.getFileById(id).orElseThrow(() -> new RuntimeException("File not found"));
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .body(new ByteArrayResource(file.getFileData()));
//    }
    
//    @GetMapping("/{id}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
//        Upload file = requisitionService.getFileById(id)
//                .orElseThrow(() -> new RuntimeException("File not found"));
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .body(new ByteArrayResource(file.getFileData()));
//    }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<ByteArrayResource> viewFile(@PathVariable Long id) {
	     Upload file = requisitionService.getFileById(id)
	             .orElseThrow(() -> new RuntimeException("File not found"));

	     // Ensure MIME type is not null or empty
	     String mimeType = file.getFileType();
	     if (mimeType == null || mimeType.isEmpty()) {
	         mimeType = "application/octet-stream"; // Fallback MIME type
	     }

	     return ResponseEntity.ok()
	             .contentType(MediaType.parseMediaType(mimeType)) // Use the stored MIME type
	             .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
	             .body(new ByteArrayResource(file.getFileData()));
	 }


}
