package com.api.vehicules.repository;

import com.api.vehicules.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Annonce_repository extends JpaRepository<Annonce, Integer> {

}
