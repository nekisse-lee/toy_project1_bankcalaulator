package com.nekisse.controllers;


import com.nekisse.domain.dto.FileUploadResponse;
import com.nekisse.service.BankAccountService;
import com.nekisse.service.FileUploadDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private final FileUploadDownloadService service;

    public FileUploadController(FileUploadDownloadService service, BankAccountService bankAccountService) {
        this.service = service;
        this.bankAccountService = bankAccountService;
    }

    private final BankAccountService bankAccountService;

//    @GetMapping("/")
//    public String controllerMain() {
//        return "Hello~ File Upload Test.";
//    }

    @PostMapping("/uploadFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String attach_path = "/static/UPLOAD_FILES/";
        String filename11 = file.getOriginalFilename();
        System.out.println("filename111 = " + rootPath + attach_path + filename11);

        String fileName = service.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        System.out.println("fileName = " + fileName);
//        bankAccountService.lll(filename11);

        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        // Load file as Resource
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
