package org.gestionEtudiants;

import java.text.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionEtudiantsApplication
{
    public static void main(String[] args) throws ParseException 
    {
         /*
        ApplicationContext ctx = SpringApplication.run(GestionEtudiantsApplication.class, args);
        EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
        
       
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        etudiantRepository.save(new Etudiant("ahmed",df.parse("1991-11-05"), "mail@mail.com", "photo.jpg"));
        etudiantRepository.save(new Etudiant("ayoub",df.parse("1991-12-15"), "mail2@mail.com", "photo2.jpg"));
        
        
        List<Etudiant> listEtu = etudiantRepository.findByNom("ayoub");
        System.out.println("list etudiant nommé ayoub :"+listEtu);
        
        // pagestart , size : like 
        Page<Etudiant> pageEtu = etudiantRepository.chercherEtudiants("%A%",new PageRequest(0, 3));
        pageEtu.forEach(e -> System.out.println("page etudiant : "+e));
        */
        
        SpringApplication.run(GestionEtudiantsApplication.class, args);
    }
}
