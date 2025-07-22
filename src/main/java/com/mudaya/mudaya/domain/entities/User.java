package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.Sexo;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends People { // Usuario general de la app
  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false, length = 100)
  private String password;

  @ManyToOne(optional = false)
  @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
  private Role role; // El tipo de rol del usuario

  public User(){ super(); } // Lo generamos por JPA
  
  public User(UUID id, String email, String password, Role role, String name, String surname, String telephoneNumber, String DNI, Sexo sexo) {
    super(id, name, surname, telephoneNumber, DNI, sexo);
    this.email = email;
    this.password = password;
    this.role = role;
  }

  // Constructor solo con email y password (para login)
  public User(String email, String password) {
    super(null, null, null, null, null, null); // Llama al constructor de People con valores nulos
    this.email = email;
    this.password = password;
    this.role = null;
  }

  // Constructor solo con name, surname, email, password, telefono, DNI y sexo (para registro)
  public User(String name, String surname, String email, String password, String telephoneNumber, String DNI, Sexo sexo) {
    super(null, name, surname, telephoneNumber, DNI, sexo); // Llama al constructor de People con valores nulos
    this.email = email;
    this.password = password;
    this.role = null;
  }


  /* Getters */
  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }

  /* Setters */
  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + super.getId() +
            ", email='" + email + '\'' +
            ", role=" + role +
            ", name='" + super.getName() + '\'' +
            ", surname='" + super.getSurname() + '\'' +
            ", sexo=" + super.getSexo() +
            '}';
  }
}
