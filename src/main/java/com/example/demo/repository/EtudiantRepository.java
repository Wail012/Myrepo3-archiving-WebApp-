package com.example.demo.repository;

import com.example.demo.model.Administrator;
import com.example.demo.model.DocEtud;
import com.example.demo.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
