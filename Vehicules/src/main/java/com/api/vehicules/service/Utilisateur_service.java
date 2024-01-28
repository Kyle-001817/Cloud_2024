package com.api.vehicules.service;

import com.api.vehicules.model.Annonce;
import com.api.vehicules.model.Utilisateur;
import com.api.vehicules.model.Voiture;
import com.api.vehicules.repository.Utilisateur_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Utilisateur_service {
    @Autowired
    private Utilisateur_repository utilisateur_repository;

    public Utilisateur createUtilisateur(Utilisateur utilisateur){
        Utilisateur createUser = utilisateur_repository.save(utilisateur);
        return createUser;
    }
    public Optional<Utilisateur> getUtilisateurbyId(int id){
        return utilisateur_repository.findById(id);
    }
    public Utilisateur updateUtilisateur(Utilisateur utilisateur){
        return utilisateur_repository.save(utilisateur);
    }
    public void deleteUtilisateur(int id){
        utilisateur_repository.deleteById(id);
    }

    public boolean loginUtilisateur(String email, String mdp){
        List<Utilisateur> source = utilisateur_repository.findAll();
        boolean valiny = false;
        for(int i = 0; i<source.size(); i++){
            if(source.get(i).getEmail().equals(email) && source.get(i).getMdp().equals(mdp)){
                valiny = true;
            }
        }
        return valiny;
    }
    public Optional<Utilisateur> getUtilisateurby(int id){
        return utilisateur_repository.findById(id);
    }

}
