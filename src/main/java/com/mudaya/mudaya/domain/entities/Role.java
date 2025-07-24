package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.UserRol;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private UserRol name; // ADMIN | STAFF

  public Role(){}; // Lo generamos por JPA

  public Role(UserRol name) { // JPA crea automaticamente el UUID
    this.name = name;
  }

  public Role(UUID id, UserRol name) {
    this.id = id;
    this.name = name;
  }

  /* Getters */
  public UUID getId() {
    return id;
  }

  public UserRol getName() {
    return name;
  }

  /* Setters */

  public void setName(UserRol name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", name=" + name +
            '}';
  }
}
