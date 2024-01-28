package com.api.vehicules.repository;

import com.api.vehicules.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Marque_repository extends JpaRepository<Marque, Integer> {
}
