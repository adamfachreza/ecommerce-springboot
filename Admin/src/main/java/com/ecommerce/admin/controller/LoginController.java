package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDTO;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
@Autowired
  private AdminServiceImpl adminService;
  @GetMapping("/login")
  public String loginForm(){
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model){
    model.addAttribute("adminDTO", new AdminDTO());
    return "register";
  }

  @GetMapping("/forgot-password")
  public String forgotPassword(Model model){
    return "forgot-password";
  }

  @PostMapping("/register")
  public String addNewAdmin(@ModelAttribute("adminDTO")AdminDTO adminDTO,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes){
    try{
      if(result.hasErrors()){
        model.addAttribute("adminDTO", adminDTO);
        return "register";
      }
      String username = adminDTO.getUsername();

    }catch (Exception e){

    }
    return null;
  }
}
