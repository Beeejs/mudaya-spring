package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.Sexo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transporters")
public class Transporter extends Employee {

  @Column(nullable = false)
  private int license; // Agregamos la licencia de conducion (numero - mismo que doc)

  protected Transporter() {super();} // Requerido por JPA

  public Transporter(UUID id, String name, String surname, String phoneNumber, String DNI, Sexo sexo, int license) {
    super(id, name, surname, phoneNumber, DNI, sexo);
    this.license = license;
  }

  /* Getters */
  public int getLicense() {
    return license;
  }

  /* Setters */
  public void setLicense(int license) {
    this.license = license;
  }

}
