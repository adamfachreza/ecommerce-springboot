package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDTO;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
@Autowired
  private AdminServiceImpl adminService;
  @GetMapping("/login")
  public String loginForm(){
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model,HttpSession session){
    session.removeAttribute("message");
    model.addAttribute("adminDTO", new AdminDTO());
    return "register";
  }

  @GetMapping("/forgot-password")
  public String forgotPassword(Model model){
    return "forgot-password";
  }

  @PostMapping("/register-new")
  public String addNewAdmin(@Valid @ModelAttribute("adminDTO")AdminDTO adminDTO,
                            BindingResult result,
                            Model model,
                            HttpSession session){
    try{
      if(result.hasErrors()){
        model.addAttribute("adminDTO", adminDTO);
        result.toString();
        return "register";
      }
      String username = adminDTO.getUserName();
      Admin admin = adminService.findByUsername(username);
      if(admin != null){
        model.addAttribute("adminDTO", adminDTO);
        System.out.println("admin not null");
        session.setAttribute("message","Your email has been registered");
        return "register";
      }
      if(adminDTO.getPassword().equals(adminDTO.getRepeatPassword())){
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        adminService.save(adminDTO);
        System.out.println("success");
        session.setAttribute("message","Register successfully");
        model.addAttribute("adminDTO",adminDTO);
      }else{
        model.addAttribute("adminDTO", adminDTO);
        session.setAttribute("message","Password is not same!");
        System.out.println("password not same");
        return "register";
      }
    }catch (Exception e){
      e.printStackTrace();
      session.setAttribute("message","Can not register because error server!");
    }
    return "register";
  }
}
