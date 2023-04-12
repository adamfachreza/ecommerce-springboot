package com.ecommerce.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "admin")
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "admin_id")
  private Long adminId;
  private String firstName;
  private String lastName;
  private String userName;
  private String password;

  @Lob
  @Column(columnDefinition = "bytea")
  private byte[] image;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name = "admin_roles", joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "admin_id"),inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role_id"))
  private Collection<Role> roles;
}
