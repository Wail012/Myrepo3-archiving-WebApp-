package com.example.demo.repository;

import com.example.demo.model.Administrator;
import com.example.demo.model.Archiviste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivisteRepository extends JpaRepository<Archiviste, Long> {
}
