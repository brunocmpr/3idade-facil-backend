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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final Path storagePath;
    private final String imageStorageDir = "images/";
    private FilePersistenceService persistenceService;
    @Autowired
    private ImageRepository repository;
    public ImageService(FilePersistenceService persistenceService) throws IOException {
        this.persistenceService = persistenceService;
        this.storagePath = Paths.get(persistenceService.baseStorageDir + imageStorageDir);
        persistenceService.createDirectiories(this.storagePath);
    }

    public List<Image> findByDrug(Drug drug){
        return repository.findByDrug(drug);
    }

    public List<Image> save(List<Image> images){
        return repository.saveAll(images);
    }

    public List<Image> persistFilesAndGenerateNonPersistedImages(List<MultipartFile> multipartFiles) {
        List<UUID> uuids = persistenceService.saveFilesCreateUuids(multipartFiles, this.storagePath);
        List<Image> images = uuids.stream().map(UUID::toString).map(Image::new).collect(Collectors.toList());
        return images;
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
