package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.Sexo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends People{ // Cliente que pidió la mudanza

  protected Client() {super();} // requerido por JPA

  public Client(UUID id, String name, String surname, String phoneNumber, String dni, Sexo Sexo) {
    super(id, name, surname, phoneNumber, dni, Sexo);
  }

  @Override
  public String toString() {
    return "Client{" + super.toString() + "}";
  }
}
