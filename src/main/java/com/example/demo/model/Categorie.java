package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titrecategorie;

    String Departement;


    public String getTitrecategorie() {
        return titrecategorie;
    }

    public void setTitrecategorie(String titrecategorie) {
        this.titrecategorie = titrecategorie;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String departement) {
        Departement = departement;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


}
