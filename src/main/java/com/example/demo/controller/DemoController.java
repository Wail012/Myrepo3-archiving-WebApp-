package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.Service.DocService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/")
public class DemoController {


    @Autowired
    private DocService docService;

    @PersistenceContext
EntityManager entityManager;

    @GetMapping
    public String Login( ) {

        return "Login";

    }


    @PostMapping
    public String Loginpost( @RequestParam("id") String id,@RequestParam("password") String pwd,@RequestParam("role") String role,Model model) {

        int trouve;

        trouve= docService.Login( Long.parseLong(id),pwd,Integer.parseInt(role));
        if(trouve==1) {
            if(role.equals("4")) {

                return "WelcomeAdminDba";
            }
            else if(role.equals("2")) {

                return "WelcomeAdmin";
            }
            else if(role.equals("3")) {

                return "WelcomeArchiviste";
            }
        }
        return "Login";
    }
    @GetMapping("/ajoutetud")
    public String ajoutetud( ) {

        return "ajoutetudformulaire";

    }
    @PostMapping("/ajoutetud")
    public String ajoutetudpost(@ModelAttribute Etudiant a, Model model) {
    docService.AddEtud(a);
        return "ajoutetudformulaire";

    }
    @GetMapping("/listetud")
    public String listetud( Model model) {
        List<Etudiant> etudiants = docService.listEtud();
        model.addAttribute("etudiants", etudiants);
        return "ListEtud";

    }
    @GetMapping("/suppetud/{id}")
    public String suppetud( @PathVariable("id") Long id) {
         docService.suppEtud(id);

        return "redirect:/listetud";

    }
    @GetMapping("/modifetud/{id}")
    public String modifetud( @PathVariable("id") Long id,Model model) {
        Optional<Etudiant> etudiant=docService.findEtudById(id);
        Etudiant etudiant1=etudiant.get();
        model.addAttribute("etudiant", etudiant1);

        return "ModifEtudFormulaire";

    }
    @PostMapping("/modifetud")
    public String modifetud(@ModelAttribute Etudiant a, Model model) {
        docService.AddEtud(a);

        model.addAttribute("etudiant", a);

        return "ModifEtudFormulaire";

    }
    @GetMapping("/submitdoc")
    public String submitdoc() {


        return "SubmitDocFormulaire";

    }
    @PostMapping("/submitdoc")
    public String submitdocpost(@RequestParam("file") MultipartFile file,
                                @RequestParam("categorie") String categorie,
                                @RequestParam("filiere") String filiere,
                                @RequestParam("anneeEtud") int anneeEtud, @RequestParam("dateCreation") LocalDate d) throws IOException {

AdminDoc b=new AdminDoc();

b.setCategorie(categorie);
b.setFiliere(filiere);
b.setAnneeEtud(anneeEtud);
b.setDateCreation(d);
b.setAccept(0);
        Optional<Administrator> c=docService.findAdminById(docService.getLoginId());
        b.setFile(file.getBytes());
        b.setAdmin(c.get());
        docService.AddAdminDoc(b);



        return "SubmitDocFormulaire";

    }
    @GetMapping("/ajoutadmin")
    public String ajoutadmin() {


        return "AjoutAdminFormulaire";

    }
    @PostMapping("/ajoutadmin")
    public String ajoutadminpost(@ModelAttribute Administrator a, Model model) {
        docService.AddAdmin(a);
        return "AjoutAdminformulaire";

}
    @GetMapping("/submitdocetud")
    public String submitdocetud(Model model) {

       List<Etudiant> a=docService.listEtud();
       model.addAttribute("etudiants", a);
        return "SubmitDocEtudFormulaire";

    }
    @PostMapping("/submitdocetud")
    public String submitdocetudpost( @RequestParam("type") String type,
                                     @RequestParam("filiere") String filiere,
                                     @RequestParam("anneeRealisation") int anneeRealisation,
                                     @RequestParam("intitule") String intitule,
                                     @RequestParam("dateSupp") LocalDate dateSupp,
                                     @RequestParam("etudiant") Long etudiantId,

                                     @RequestParam("file") MultipartFile file,Model model) throws IOException {

        DocEtud docEtud=new DocEtud();

        docEtud.setType(type);
        docEtud.setFiliere(filiere);
        docEtud.setAnneeRealisation(anneeRealisation);
        docEtud.setIntitule(intitule);
        docEtud.setAccept(0);

        docEtud.setEtudiant(docService.findEtudById(etudiantId).orElse(null));
        docEtud.setAdmin(docService.findAdminById(docService.getLoginId()).orElse(null));
        docEtud.setFile(file.getBytes());
        docService.AddEtudDoc(docEtud);

        List<Etudiant> a=docService.listEtud();
        model.addAttribute("etudiants", a);


        return "SubmitDocEtudFormulaire";

    }
    @GetMapping("/ajoutarchiviste")
    public String ajoutarchiviste() {


        return "AjoutArchivisteFormulaire";

    }
    @PostMapping("/ajoutarchiviste")
    public String ajoutarchivistepost(@ModelAttribute Archiviste a, Model model) {
        docService.AddArchiviste(a);
        return "AjoutArchivisteformulaire";

    }
    @GetMapping("/docsubmited")
    public String docsubmited(Model model) {

        String jpql = "SELECT d FROM AdminDoc d WHERE d.accepted = :accept ";
        TypedQuery<AdminDoc> query = entityManager.createQuery(jpql,AdminDoc.class);
        query.setParameter("accept", 0);
        List<AdminDoc> adminDocs = query.getResultList();
model.addAttribute("adminDocs", adminDocs);
        return "docsubmited";

    }
    @GetMapping("/detailadmindocsubmited/{id}")
    public String docsubmited( @PathVariable("id") Long id,Model model) {

        AdminDoc a=docService.findAdminDocById(id).get();
        model.addAttribute("adminDoc", a);
        return "DetailSubmitedAdminDoc";

    }
    @GetMapping("/admindocsaccept/{id}")
    public String docsubmitedacccept( @PathVariable("id")Long id,@RequestParam("dateSupp") LocalDate dateSupp) {

        AdminDoc a=docService.findAdminDocById(id).get();
        a.setAccept(1);
        a.setDateSupp(dateSupp);
        docService.AddAdminDoc(a);

        return "redirect:/docsubmited";

    }
    @GetMapping("/admindocsrefuse/{id}")
    public String docsubmitedrefuse( @PathVariable("id")Long id) {

        AdminDoc a=docService.findAdminDocById(id).get();
        a.setAccept(-1);
        docService.AddAdminDoc(a);
        return "redirect:/docsubmited";

    }
    @GetMapping("/admindoccontenu/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<AdminDoc> adminDocOptional = docService.findAdminDocById(id);
        if (adminDocOptional.isPresent()) {
            AdminDoc adminDoc = adminDocOptional.get();
            byte[] fileBytes = adminDoc.getFile();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("file.pdf").build());
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/mysubmitions")
    public String mysubmitions( Model model) {
        String jpql = "SELECT d FROM AdminDoc d WHERE d.admin = :accept ";
        TypedQuery<AdminDoc> query = entityManager.createQuery(jpql,AdminDoc.class);
        query.setParameter("accept", docService.findAdminById(docService.getLoginId()).orElse(null));
        List<AdminDoc> adminDocs = query.getResultList();

        model.addAttribute("adminDocs", adminDocs);

        String jpql1 = "SELECT d FROM DocEtud d WHERE d.admin = :accept ";
        TypedQuery<DocEtud> query1 = entityManager.createQuery(jpql1,DocEtud.class);
        query1.setParameter("accept", docService.findAdminById(docService.getLoginId()).orElse(null));
        List<DocEtud> docEtuds = query1.getResultList();
        model.addAttribute("docEtuds", docEtuds);
        return "mysubmitions";

    }
    @GetMapping("/docstudentsubmited")
    public String docstudentsubmited(Model model) {

        String jpql = "SELECT d FROM DocEtud d WHERE d.accepted = :accept ";
        TypedQuery<DocEtud> query = entityManager.createQuery(jpql,DocEtud.class);
        query.setParameter("accept", 0);
        List<DocEtud> adminDocs = query.getResultList();
        model.addAttribute("DocEtud", adminDocs);
        return "docstudentsubmited";

    }
    @GetMapping("/detaildocstudentsubmited/{id}")
    public String docstudentsubmited( @PathVariable("id") Long id,Model model) {

        DocEtud a=docService.findDocEtudById(id).get();
        model.addAttribute("DocEtud", a);
        return "DetailSubmitedDocEtud";

    }
    @GetMapping("/docstudentsaccept/{id}")
    public String docstudentsubmitedacccept( @PathVariable("id")Long id,@RequestParam("dateSupp") LocalDate dateSupp) {

        DocEtud a=docService.findDocEtudById(id).get();
        a.setAccept(1);
        a.setDateSupp(dateSupp);
        a.setArchiviste(docService.findArchivisteById(docService.getLoginId()).orElse(null));
        docService.AddEtudDoc(a);

        return "redirect:/docstudentsubmited";

    }
    @GetMapping("/docstudentsrefuse/{id}")
    public String docsubmitedrefuse1( @PathVariable("id")Long id) {

        DocEtud a=docService.findDocEtudById(id).get();
        a.setAccept(-1);
        a.setArchiviste(docService.findArchivisteById(docService.getLoginId()).orElse(null));
        docService.AddEtudDoc(a);
        return "redirect:/docstudentsubmited";

    }
    @GetMapping("/docstudentcontenu/{id}")
    public ResponseEntity<byte[]> getFile1(@PathVariable Long id) {
        Optional<DocEtud> adminDocOptional = docService.findDocEtudById(id);
        if (adminDocOptional.isPresent()) {
            DocEtud adminDoc = adminDocOptional.get();
            byte[] fileBytes = adminDoc.getFile();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("file.pdf").build());
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listadmindoc")
    public String getFile1(Model model) {
        String jpql = "SELECT d FROM AdminDoc d WHERE d.accepted = :accept ";
        TypedQuery<AdminDoc> query = entityManager.createQuery(jpql,AdminDoc.class);
        query.setParameter("accept", 1);
        List<AdminDoc> a = query.getResultList();
        model.addAttribute("adminDocs", a);
        return "listadmindoc";


    }
    @GetMapping("/searchadmindoc")
    public String getFile2(@RequestParam("keyword") String search,Model model) {

        String jpql = "SELECT d FROM AdminDoc d WHERE (d.filiere like :accept or d.categorie like :accept) and d.accepted = :accept1";
        TypedQuery<AdminDoc> query = entityManager.createQuery(jpql,AdminDoc.class);
        query.setParameter("accept", search);
        query.setParameter("accept1", 1);
        List<AdminDoc> a = query.getResultList();
        model.addAttribute("adminDocs", a);
        return "listadmindoc";


    }
    @GetMapping("/admindocfilter")
    public String getFile3(@RequestParam("categorie") String categorie,@RequestParam("filiere") String filiere,Model model) {
        String jpql;
        TypedQuery<AdminDoc> query = null;
        if(categorie.equals("All") && (filiere.equals("All")))
        {

            jpql = "SELECT d FROM AdminDoc d WHERE  d.accepted = :accept1";
            query = entityManager.createQuery(jpql,AdminDoc.class);
            
        }
        else if (categorie.equals("All") && (!filiere.equals("All")))
        {
            jpql = "SELECT d FROM AdminDoc d WHERE d.filiere like :accept2  and d.accepted = :accept1";
            query = entityManager.createQuery(jpql,AdminDoc.class);
            query.setParameter("accept2", filiere);
        }
        else if (!categorie.equals("All") && (filiere.equals("All")))
        {
            jpql = "SELECT d FROM AdminDoc d WHERE d.categorie like :accept2  and d.accepted = :accept1";
            query = entityManager.createQuery(jpql,AdminDoc.class);
            query.setParameter("accept2", categorie);
        }
        else {
            jpql = "SELECT d FROM AdminDoc d WHERE (d.filiere like :accept or d.categorie like :accept2) and d.accepted = :accept1";
            query = entityManager.createQuery(jpql,AdminDoc.class);
            query.setParameter("accept", filiere);
            query.setParameter("accept2", categorie);
        }

        query.setParameter("accept1", 1);
        List<AdminDoc> a = query.getResultList();
        model.addAttribute("adminDocs", a);
        return "listadmindoc";


    }
}
