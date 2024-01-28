package com.api.vehicules.repository;

import com.api.vehicules.model.Marque;
import com.api.vehicules.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Utilisateur_repository extends JpaRepository<Utilisateur, Integer> {

}