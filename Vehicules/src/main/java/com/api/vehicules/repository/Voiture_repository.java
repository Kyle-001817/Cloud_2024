package com.api.vehicules.repository;

import com.api.vehicules.model.Utilisateur;
import com.api.vehicules.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Voiture_repository extends JpaRepository<Voiture, Integer> {
}
