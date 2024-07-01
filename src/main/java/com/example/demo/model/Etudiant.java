package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int anneeAccess;
    private String filiere;
    private int accessLevelAnnee;
    private int accessLevelFiliere;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAnneeAccess() {
        return anneeAccess;
    }

    public void setAnneeAccess(int anneeAccess) {
        this.anneeAccess = anneeAccess;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public int getAccessLevelAnnee() {
        return accessLevelAnnee;
    }

    public void setAccessLevelAnnee(int accessLevelAnnee) {
        this.accessLevelAnnee = accessLevelAnnee;
    }

    public int getAccessLevelFiliere() {
        return accessLevelFiliere;
    }

    public void setAccessLevelFiliere(int accessLevelFiliere) {
        this.accessLevelFiliere = accessLevelFiliere;
    }
}

