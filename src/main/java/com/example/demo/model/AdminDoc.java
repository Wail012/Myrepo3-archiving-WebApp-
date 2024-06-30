package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class AdminDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String categorie;
     LocalDate dateCreation;

     String filiere;
     int anneeEtud;
     LocalDate dateSupp;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
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
}
