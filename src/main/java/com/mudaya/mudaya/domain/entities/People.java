package com.mudaya.mudaya.domain.entities;

import com.mudaya.mudaya.domain.enums.Sexo;
import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class People {
  // Clase padre persona con propiedades generales
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30, nullable = false)
  private String surname;

  @Column(name = "phone_number", length = 30)
  private String phoneNumber;

  @Column(length = 20, nullable = false)
  private String dni;

  @Enumerated(EnumType.STRING)
  @Column(length = 1)
  private Sexo sexo;
  public People() {} // Lo generamos por JPA

  public People(UUID id, String name, String surname, String phoneNumber, String dni, Sexo Sexo) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.dni = dni;
    this.sexo = Sexo;
  }

   /* Getters */
  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getDni() {
    return dni;
  }

  public Sexo getSexo() {
    return sexo;
  }

  /* Setters */
  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }


  @Override
  public String toString() {
    return "People{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", DNI='" + dni + '\'' +
            ", sexo=" + sexo +
            '}';
  }
}
