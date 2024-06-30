package com.example.demo.repository;

import com.example.demo.model.Administrator;
import com.example.demo.model.DocEtud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocEtudRepository extends JpaRepository<DocEtud, Long> {
}
