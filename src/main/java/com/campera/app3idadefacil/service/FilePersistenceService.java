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
                deleteFiles(uuids, storagePath);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return uuids;
    }

    private void persistFile(MultipartFile file, UUID uuid, Path storagePath) throws IOException {
        Path filePath = storagePath.resolve(uuid.toString());
        Files.write(filePath, file.getBytes());
    }

    private void deleteFiles(List<UUID> uuids, Path storagePath) {
        uuids.stream().forEach(image -> {
            Path imagePath = storagePath.resolve(uuids.toString());
            try {
                Files.delete(imagePath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        });
    }
}
