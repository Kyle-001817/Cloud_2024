package com.api.vehicules.service;

import com.api.vehicules.model.Admin;
import com.api.vehicules.model.Annonce;
import com.api.vehicules.model.Categorie;
import com.api.vehicules.repository.Admin_repository;
import com.api.vehicules.repository.Categorie_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Admin_service {
    @Autowired
    private Admin_repository admin_repository;

    public List<Admin> getAllAdmin(){
        return admin_repository.findAll();
    }
    public Admin createAdmin(Admin admin)
    {
        Admin create_admin = admin_repository.save(admin);
        return create_admin;
    }
    public Optional<Admin> getAdminById(int id){
        return admin_repository.findById(id);
    }
}
