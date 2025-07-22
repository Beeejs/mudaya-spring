package com.mudaya.mudaya.domain.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "move_vehicle_transporter")
public class MoveAssignment {
  // Asignacion de vehiculo y transportador auna mudanza, pueden ser varias
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "move_id", nullable = false)
  private Move move;

  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private Transporter transporter;

  public MoveAssignment() {} // Constructor requerido por JPA

  public MoveAssignment(UUID id, Move move, Vehicle vehicle, Transporter transporter) {
    this.id = id;
    this.move = move;
    this.vehicle = vehicle;
    this.transporter = transporter;
  }

  /* Getters */
  public UUID getId() {
    return id;
  }

  public Move getMove() {
    return move;
  }
  
  public Vehicle getVehicle() {
    return vehicle;
  }

  public Transporter getTransporter() {
    return transporter;
  }

  /* Setters */
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public void setTransporter(Transporter transporter) {
    this.transporter = transporter;
  }
}
