package com.example.demo.Service;


import com.example.demo.model.*;
import com.example.demo.repository.DocEtudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocService {
    @Autowired
    private DocEtudRepository docEtudRepository;

    @Autowired
    private DocEtudRepository AdmindocRepository;
    @Autowired
    private DocEtudRepository ArchivisteRepository;
    @Autowired
    private DocEtudRepository AdministratorRepository;
    @Autowired
    private DocEtudRepository EtudiantRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public int Login(Long id, String password,int role) {
//this funnction is for login with roles
        if(role==1)
        {
            String jpql = "SELECT d FROM Etudiant d WHERE d.id = :id and d.password=:password";
            TypedQuery<Etudiant> query = entityManager.createQuery(jpql, Etudiant.class);
            query.setParameter("id", id);
            query.setParameter("password", password);
            if(query.getResultList().isEmpty())
            {
                return 0;
            }
            else
            {
                return 1;
            }

        } else if (role==2) {

            String jpql = "SELECT d FROM Administrator d WHERE d.id = :id and d.password=:password";
            TypedQuery<Administrator> query = entityManager.createQuery(jpql, Administrator.class);
            query.setParameter("id", id);
            query.setParameter("password", password);
            if(query.getResultList().isEmpty())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if (role==3) {

            String jpql = "SELECT d FROM Archiviste d WHERE d.id = :id and d.password=:password";
            TypedQuery<Archiviste> query = entityManager.createQuery(jpql, Archiviste.class);
            query.setParameter("id", id);
            query.setParameter("password", password);
            if(query.getResultList().isEmpty())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if (role==4){
            String jpql = "SELECT d FROM AdminDba d WHERE d.id = :id and d.password=:password";
            TypedQuery<AdminDba> query = entityManager.createQuery(jpql, AdminDba.class);
            query.setParameter("id", id);
            query.setParameter("password", password);
            if(query.getResultList().isEmpty())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
      return -1;
    }
}
