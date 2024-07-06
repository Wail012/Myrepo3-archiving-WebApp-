package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Entity
public class AdminDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     LocalDate dateCreation;
     int accepted;
     String filiere;
     int anneeEtud;
     LocalDate dateSupp;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
     Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_administrator")
    private Administrator admin;

    @ManyToOne
    @JoinColumn(name = "id_archiviste")
    private Archiviste archiviste; ;


    // Getters and Setters
    public Long getId() {
        return id;
    }
    public int getAccept() {
        return accepted;
    }
    public void setAccept(int accepted) {
        this.accepted = accepted;
    }
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
    public LocalDate getDateSupp() {
        return dateSupp;
    }

    public void setDateSupp(LocalDate dateSupp) {
        this.dateSupp = dateSupp;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public int getAnneeEtud() {
        return anneeEtud;
    }

    public void setAnneeEtud(int anneeEtud) {
        this.anneeEtud = anneeEtud;
    }
    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin (Administrator etudiant) {
        this.admin = etudiant;
    }
    public void setArchiviste (Archiviste etudiant) {
        this.archiviste = etudiant;
    }
}
