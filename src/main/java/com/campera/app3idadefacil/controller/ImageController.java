package com.campera.app3idadefacil.controller;

import com.campera.app3idadefacil.model.AppUser;
import com.campera.app3idadefacil.model.Drug;
import com.campera.app3idadefacil.model.Image;
import com.campera.app3idadefacil.service.DrugService;
import com.campera.app3idadefacil.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/images")
@SecurityRequirement(name = "bearerAuth")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DrugService drugService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "obtains image by id")
    public ResponseEntity<Resource> getImageWithType(@PathVariable Long id , Authentication authentication){
        AppUser user = (AppUser) authentication.getPrincipal();
        Image image = imageService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada"));
        Resource imageResource = imageService.getResource(image, user);
        MediaType mediaType = imageService.getMediaType(image);
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageResource);
    }
}
