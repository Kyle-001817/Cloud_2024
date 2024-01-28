package com.api.vehicules.repository;

import com.api.vehicules.model.Ville;
import com.api.vehicules.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ville_repository extends JpaRepository<Ville, Integer> {
}
