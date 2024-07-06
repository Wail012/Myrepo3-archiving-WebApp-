package com.example.demo.Service;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DocService {
    @Autowired
    private DocEtudRepository docEtudRepository;

    @Autowired
    private AdminDocRepository admindocRepository;
    @Autowired
    private ArchivisteRepository archivisteRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private Long loginId;

    private int loginRole;
    @Autowired
    private CategorieRepository categorieRepository;


    public void setLoggedInUser(Long userId, int role) {
        this.loginId = userId;
        this.loginRole = role;
    }

    public Long getLoginId() {
        return loginId;
    }

    public int getLoginRole() {
        return loginRole;
    }




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
                setLoggedInUser(id,1);
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
                setLoggedInUser(id,2);
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
                setLoggedInUser(id,3);
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
                setLoggedInUser(id,4);
                return 1;
            }
        }
      return -1;
    }
    public void AddEtud(Etudiant a) {
       etudiantRepository.save(a);

    }
    public void AddArchiviste(Archiviste a) {
        archivisteRepository.save(a);

    }
    public void AddAdmin(Administrator a) {
        administratorRepository.save(a);

    }
    public void AddAdminDoc(AdminDoc a) {
        admindocRepository.save(a);

    }
    public void AddEtudDoc(DocEtud a) {
        docEtudRepository.save(a);

    }
    public List<Etudiant> listEtud( ) {
        return etudiantRepository.findAll();

    }
    public List<Administrator> listAdmin( ) {
        return administratorRepository.findAll();

    }
    public List<Categorie> listCategorie( ) {
        return categorieRepository.findAll();

    }
    public List<Archiviste> listArchiviste( ) {
        return archivisteRepository.findAll();

    }
    public List<AdminDoc> listAdminDoc( ) {
        return admindocRepository.findAll();

    }
    public List<DocEtud> listDocEtud( ) {
        return docEtudRepository.findAll();

    }



    public void suppEtud( Long id) {
        etudiantRepository.deleteById(id);

    }
    public void suppAdmin( Long id) {
        administratorRepository.deleteById(id);

    }
    public void suppArchiviste( Long id) {
        archivisteRepository.deleteById(id);

    }
    public Optional<Etudiant> findEtudById(Long id) {
        return etudiantRepository.findById(id);

    }
    public Optional<AdminDoc> findAdminDocById(Long id) {
        return admindocRepository.findById(id);

    }
    public Optional<Categorie> findCategorieById(Long id) {
        return categorieRepository.findById(id);

    }
    public Optional<DocEtud> findDocEtudById(Long id) {
        return docEtudRepository.findById(id);

    }

    public Optional<Administrator> findAdminById(Long id) {
        return administratorRepository.findById(id);

    }
    public Optional<Archiviste> findArchivisteById(Long id) {
        return archivisteRepository.findById(id);

    }
    public String getBase64EncodedFile(Long id) {
        AdminDoc adminDoc = findAdminDocById(id).get();
        if (adminDoc != null && adminDoc.getFile() != null) {
            return Base64.getEncoder().encodeToString(adminDoc.getFile());
        }
        return null;
    }
}
