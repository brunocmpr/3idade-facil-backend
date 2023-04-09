package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final Path storePath;

    @Autowired
    private ImageRepository repository;

    public ImageService(@Value("${image_store_path}") String storePath) throws IOException {
        this.storePath = Paths.get(storePath);
        System.out.println(this.storePath);
        Files.createDirectories(this.storePath);
    }

    public List<Image> findByDrug(Drug drug){
        return repository.findByDrug(drug);
    }

    public List<Image> save(List<Image> images){
        return repository.saveAll(images);
    }

    public List<UUID> saveFilesCreateUuids(List<MultipartFile> files){
        List<UUID> uuids = new ArrayList<>();
        for(int i = 0; i < files.size(); i++) {
            UUID uuid = UUID.randomUUID();
            MultipartFile file = files.get(i);
            try {
                persistFile(file, uuid);
                uuids.add(uuid);
            } catch (IOException e) {
                deleteFiles(uuids);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return uuids;
    }

    private void persistFile(MultipartFile file, UUID uuid) throws IOException {
        Path filePath = storePath.resolve(uuid.toString());
        Files.write(filePath, file.getBytes());
    }

    private void deleteFiles(List<UUID> uuids) {
        uuids.stream().forEach(image -> {
            Path imagePath = storePath.resolve(uuids.toString());
            try {
                Files.delete(imagePath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        });
    }

    public void guardClausesCreateImages(List<MultipartFile> files) {
        if(isNotListOfImages(files)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas imagens são permitidas");
        }
    }

    private boolean isNotListOfImages(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            try {
                ImageIO.read(file.getInputStream());
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    public List<Image> saveAll(List<Image> images) {
        return repository.saveAll(images);
    }
}
