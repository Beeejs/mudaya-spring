package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.Sexo;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee extends People {

  public Employee() {super();} // necesario por JPA

  // EMpleado, en este caso enfoque a administrativo
  public Employee(UUID id, String name, String surname, String telephoneNumber, String dni, Sexo sexo) {
    super(id, name, surname, telephoneNumber, dni, sexo);
  }

}
