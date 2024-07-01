package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class DocEtud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String filiere;
    private int anneeRealisation;
    private String intitule;
    private LocalDate DateSupp;
    int accepted;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "id_administrator")
    private Administrator admin;
    // Getters and Setters

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;

    public int getAccept() {
        return accepted;
    }
    public void setAccept(int accepted) {
        this.accepted = accepted;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public int getAnneeRealisation() {
        return anneeRealisation;
    }

    public void setAnneeRealisation(int anneeRealisation) {
        this.anneeRealisation = anneeRealisation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin (Administrator etudiant) {
        this.admin = etudiant;
    }
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
