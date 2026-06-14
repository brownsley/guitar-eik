package com.chord.server.services.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageStorageService {
    String imageUpload(MultipartFile file);
}