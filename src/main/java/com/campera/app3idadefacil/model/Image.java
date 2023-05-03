package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Entity
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "filename", nullable = false)
    @Getter @Setter
    private String filename;

    @Column(name = "relative_path")
    @Getter @Setter
    private String relativePath;

    @Column(name = "extension")
    @Getter @Setter
    private String extension;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = true)
    @Getter @Setter
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    @Getter @Setter
    private Patient patient;

    public Image(String filename, String extension, String relativePath) {
        this.filename = filename;
        this.extension = extension;
        this.relativePath = relativePath;
    }

    public String getDirectoryAndFilenameAndExtension(){
        return getDeepestDirectory() + getFilenameAndExtension();
    }
    public String getDeepestDirectory(){
        if(relativePath == null || relativePath.isEmpty()){
            return "";
        }
        String pathWithoutTrailingSeparator = relativePath;
        if(pathWithoutTrailingSeparator.endsWith(File.separator)){
            pathWithoutTrailingSeparator
                    = pathWithoutTrailingSeparator.substring(0, pathWithoutTrailingSeparator.length() - 1);
        }
        String returnValue =
        pathWithoutTrailingSeparator
                .substring(pathWithoutTrailingSeparator.lastIndexOf(File.separator) + 1);
        if(!returnValue.endsWith(File.separator)){
            returnValue += File.separator;
        }
        return returnValue;
    }

    public String getFilenameAndExtension() {
        return filename + "." + extension;
    }
}
