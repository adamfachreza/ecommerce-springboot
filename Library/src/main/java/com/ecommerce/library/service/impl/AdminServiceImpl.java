package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.AdminDTO;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.repository.AdminRepository;
import com.ecommerce.library.repository.RoleRepository;
import com.ecommerce.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class AdminServiceImpl implements AdminService {

  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private RoleRepository roleRepository;
  @Override
  public Admin findByUsername(String username) {
    return adminRepository.findByUserName(username);
  }

  @Override
  public Admin save(AdminDTO adminDTO) {
    Admin admin = new Admin();
    admin.setFirstName(adminDTO.getFirstName());
    admin.setLastName(adminDTO.getLastName());
    admin.setUserName(admin.getUserName());
    admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
    admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
    return adminRepository.save(admin);
  }
}