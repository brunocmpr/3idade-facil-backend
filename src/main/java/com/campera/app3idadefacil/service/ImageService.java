package com.campera.app3idadefacil.service;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ImageService {

    private final Path imageStoragePath;
    private final String rootImageStorageDir = "images/";
    public final String drugStorageDir = "drugs/";
    public final String patientStorageDir = "patients/";
    private final List<String> childDirs = List.of(drugStorageDir, patientStorageDir);
    private FilePersistenceService persistenceService;
    @Autowired
    private ImageRepository repository;

    public ImageService(FilePersistenceService persistenceService) throws IOException {
        this.persistenceService = persistenceService;
        this.imageStoragePath = Paths.get(persistenceService.baseStorageDir + rootImageStorageDir);
        childDirs.forEach((dir)->{
            try {
                persistenceService.createDirectories(this.imageStoragePath.resolve(dir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Resource getResource(Image image, AppUser user) {
        guardClausesGetResource(image, user);

        Path imagePath = imageStoragePath.resolve(image.getDirectoryAndFilenameAndExtension());
        Resource imageResource = null;
        try {
            imageResource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (!imageResource.exists() || !imageResource.isReadable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada: " + image);
        }
        return imageResource;
    }

    private void guardClausesGetResource(Image image, AppUser user) {
        if((image.getDrug() != null && !image.getDrug().getCaretaker().equals(user)
        ||(image.getPatient() != null && !image.getPatient().getAdmin().equals(user)))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não possui direito sobre este recurso.");
        }
    }

    public MediaType getMediaType(Image image){
        Path imagePath = imageStoragePath.resolve(image.getDirectoryAndFilenameAndExtension());
        String contentType = null;
        try {
            contentType = Files.probeContentType(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        MediaType mediaType = MediaType.parseMediaType(contentType);
        return  mediaType;
    }

    public List<Image> findByDrug(Drug drug){
        return repository.findByDrug(drug);
    }

    public List<Image> save(List<Image> images){
        return repository.saveAll(images);
    }

    public List<Image> persistFilesAndGenerateNonPersistedImages(List<MultipartFile> multipartFiles
            , String childStorageDir) {
        Path finalStoragePath = this.imageStoragePath.resolve(childStorageDir);
        List<UUID> uuids = persistenceService.saveFilesCreateUuids(multipartFiles, finalStoragePath);
        List<Image> images = IntStream.range(0, uuids.size()).mapToObj(i -> new Image(uuids.get(i).toString()
                , extractExtension(multipartFiles.get(i).getOriginalFilename())
                , persistenceService.baseStorageDir + rootImageStorageDir + childStorageDir)
                ).collect(Collectors.toList());
        return images;
    }

    private String extractFilename(String filenameAndExtension){
        return filenameAndExtension.substring(0, filenameAndExtension.lastIndexOf(".") + 1);
    }

    private String extractExtension(String filenameAndExtension){
        return filenameAndExtension.substring(filenameAndExtension.lastIndexOf(".") + 1);
    }


    public void guardClausesCreateImages(List<MultipartFile> files) {
        if(isNotListOfImages(files)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas imagens são permitidas");
        }
    }

    private boolean isNotListOfImages(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image == null) {
                    return true;
                }
            } catch (IOException e) {
                return true;
            }
        }
        return false;
    }

    public List<Image> saveAll(List<Image> images) {
        return repository.saveAll(images);
    }

    public Optional<Image> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteAll(List<Image> images) {
        persistenceService.deleteFiles(
                images.stream().map(Image::getDirectoryAndFilenameAndExtension).toList(), this.imageStoragePath);
        repository.deleteAll(images);
    }
}
