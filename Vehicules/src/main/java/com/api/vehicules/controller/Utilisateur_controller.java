package com.api.vehicules.controller;

import com.api.vehicules.model.Annonce;
import com.api.vehicules.model.Utilisateur;
import com.api.vehicules.model.Voiture;
import com.api.vehicules.service.Utilisateur_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class Utilisateur_controller {
    @Autowired
    private Utilisateur_service utilisateur_service;

    @PostMapping("/add_user")
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur){
        Utilisateur create_user = utilisateur_service.createUtilisateur(utilisateur);
        return new ResponseEntity<>(create_user, HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public boolean loginUtilisateur(String email, String mdp)
    {
        boolean login = utilisateur_service.loginUtilisateur(email,mdp);
        return login;
    }
    @PutMapping("/update_utilisateur")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur user) {
        return utilisateur_service.updateUtilisateur(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        utilisateur_service.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/id_utilisateur/{id}")
    public Optional<Utilisateur> getUtilisateurById(@PathVariable int id) {
        return utilisateur_service.getUtilisateurby(id);
    }
}
