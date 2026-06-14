package com.chord.server.services.storage;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.webp.WebpWriter;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class ImageStorageSuperBase implements ImageStorageService {

    private final S3Client s3Client;

    ImageStorageSuperBase(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String imageUpload(MultipartFile file) {
        try {
            byte[] data = file.getBytes();

            ImmutableImage image = ImmutableImage.loader().fromBytes(data);
            ImmutableImage resizedImage = image.scaleTo(500, 500);

            byte[] optimizedData = resizedImage.bytes(WebpWriter.DEFAULT
                    .withQ(80));

            int size = 11;
            String alphabetString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            char[] alphabet = alphabetString.toCharArray();

            String fileName = NanoIdUtils.randomNanoId(new Random(), alphabet, size);
            String targetPath = fileName + ".webp";

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("hee")
                    .key(targetPath)
                    .contentType("image/webp")
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(optimizedData));
            return fileName;
        } catch (Exception e) {
            return "Upload Failed: " + e.getMessage();
        }
    }

}
