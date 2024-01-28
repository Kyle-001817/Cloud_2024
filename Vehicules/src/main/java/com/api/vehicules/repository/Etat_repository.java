package com.api.vehicules.repository;

import com.api.vehicules.model.Etat;
import com.api.vehicules.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Etat_repository extends JpaRepository<Etat, Integer> {
}
