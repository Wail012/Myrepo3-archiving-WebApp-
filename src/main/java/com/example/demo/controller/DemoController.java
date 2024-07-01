package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.Service.DocService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/detaildoccsubmited")
    public String docsubmited( @PathVariable("id") Long id,Model model) {

        AdminDoc a=docService.findAdminDocById(id).get();
        model.addAttribute("adminDocs", a);
        return "docsubmited";

    }


}
