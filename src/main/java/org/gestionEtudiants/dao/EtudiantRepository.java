/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gestionEtudiants.dao;

import java.util.Date;
import java.util.List;
import org.gestionEtudiants.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ayoub
 */
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>
{
    public List<Etudiant> findByNom(String nom);
    public Page<Etudiant> findByNom(String nom,Pageable pageable);
    
    @Query("select e from Etudiant e where e.nom like :x")
    public Page<Etudiant> chercherEtudiants(@Param("x") String mc,Pageable pageable);
    
    @Query("select e from Etudiant e where e.dateNaissance > :x and e.dateNaissance < :y")
    public List<Etudiant> chercherEtudiants(@Param("x") Date d1,@Param("y") Date d2);
}
