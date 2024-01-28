package com.api.vehicules.controller;

import com.api.vehicules.model.Admin;
import com.api.vehicules.model.Annonce;
import com.api.vehicules.model.Marque;
import com.api.vehicules.service.Admin_service;
import com.api.vehicules.service.Marque_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/")
@CrossOrigin
public class Admin_controller {
    @Autowired
    Admin_service admin_service;

    @GetMapping("all_admin")
    public List<Admin> getListAdmin(){
        return admin_service.getAllAdmin();
    }
    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable int id) {
        return admin_service.getAdminById(id);
    }
}
