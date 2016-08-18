/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gestionEtudiants.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.gestionEtudiants.dao.EtudiantRepository;
import org.gestionEtudiants.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ayoub
 */
@Controller
@RequestMapping(value = "etudiant")
public class EtudiantController
{
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    /*
    @Value("${dir.images}")
    private String imageDir;
    */
    
    @RequestMapping(value = "/index")
    public String Index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int p,
                        @RequestParam(name = "motCle",defaultValue = "") String mc)
    {
        //Page<Etudiant> pageEtudiants = etudiantRepository.findAll(new PageRequest(p,5));
        Page<Etudiant> pageEtudiants = etudiantRepository.chercherEtudiants("%"+mc+"%",new PageRequest(p,5));
        
        
        int pageCount = pageEtudiants.getTotalPages();
        int[] pages = new int[pageCount];
        
        for(int i=0 ; i<pageCount ; i++)
        {
            pages[i]=i;
        }
        //object accessble a travers la session
        model.addAttribute("pageEtudiants",pageEtudiants);
        model.addAttribute("pages",pages);
        model.addAttribute("pageCourante",p);
        model.addAttribute("motCle",mc);
        
        return "etudiants";
    }
    
    @RequestMapping(value = "/form" , method = RequestMethod.GET)
    public String formEtudiant(Model model)
    {
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";
    }
    
    @RequestMapping(value = "/saveEtudiant" , method = RequestMethod.POST)
    public String saveEtudiant(@Valid Etudiant etudiant,BindingResult bindingResult,@RequestParam(name = "avatar") MultipartFile photo) throws IOException
    {
        if(bindingResult.hasErrors())
        {
            return  "formEtudiant";
        }
        
        if(!photo.isEmpty())
        {
            //TODO : change path to upload location
            //System.getProperty("user.home")
            etudiant.setPhoto(photo.getOriginalFilename());
            photo.transferTo(new File(System.getProperty("user.home")+"/uploads/"+photo.getOriginalFilename()));
        }
        //faura un match entres les parametres des requette et l'objet fournis en parametres
        etudiantRepository.save(etudiant);
        
        return "redirect:index";
    }
}
