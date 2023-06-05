package com.campera.app3idadefacil.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilePersistenceService {

    public void createDirectories(Path storagePath) throws IOException {
        Files.createDirectories(storagePath);
    }

    public final String baseStorageDir = "persistence/";

    public List<UUID> saveFilesCreateUuids(List<MultipartFile> files, Path storagePath){
        List<UUID> uuids = new ArrayList<>();
        for(int i = 0; i < files.size(); i++) {
            UUID uuid = UUID.randomUUID();
            MultipartFile file = files.get(i);
            try {
                persistFile(file, uuid, storagePath);
                uuids.add(uuid);
            } catch (IOException e) {
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                String fullFilepath = uuid.toString() + "." + extension;
                deleteFiles(fullFilepath, storagePath);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return uuids;
    }

    private void persistFile(MultipartFile file, UUID uuid, Path storagePath) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        Path filePath = storagePath.resolve(uuid.toString() + "." + extension);
        Files.write(filePath, file.getBytes());
    }

    private void deleteFiles(String fullFilepath, Path storagePath){
        Path imagePath = storagePath.resolve(fullFilepath);
        try {
            Files.delete(imagePath);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void deleteFile(String filenameAndExtension, Path storagePath){
        Path imagePath = storagePath.resolve(filenameAndExtension);
        try {
            Files.delete(imagePath);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    public void deleteFiles(List<String> filenamesAndExtensions, Path storagePath) {
        filenamesAndExtensions.forEach((filenameAndExtension)->{
            deleteFile(filenameAndExtension, storagePath);
        });
    }
}
