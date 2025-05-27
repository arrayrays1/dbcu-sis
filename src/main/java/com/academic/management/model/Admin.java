package com.academic.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    
    public Admin() {
        super();
    }
    
    public Admin(Long id, String name, String email, String password, Role role) {
        super(id, name, email, password, role);
    }
}
