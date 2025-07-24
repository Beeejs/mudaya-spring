package com.mudaya.mudaya.domain.entities;

import java.util.UUID;

import com.mudaya.mudaya.domain.enums.VehicleType;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(length = 50, nullable = false)
  private String brand;

  @Column(length = 50, nullable = false)
  private String model;

  @Column(name = "license_plate", length = 20, nullable = false, unique = true)
  private String licensePlate;

  @Column(length = 20)
  private String color;

  @Enumerated(EnumType.STRING)
  @Column(length = 50)
  private VehicleType type;

  @Column(name = "capacity_kg")
  private double capacityKg;

  @Column(name = "area_m2")
  private double areaM2;

  @Column(name = "volume_m3")
  private double volumeM3;

  public Vehicle() {} // Necesario para JPA

  public Vehicle(UUID id, String brand, String model, String licensePlate, String color, VehicleType type, double capacityKg, double areaM2, double volumeM3) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.licensePlate = licensePlate;
    this.color = color;
    this.type = type;
    this.capacityKg = capacityKg;
    this.areaM2 = areaM2;
    this.volumeM3 = volumeM3;
  }

  /* Getters */
  public UUID getId() {
    return id;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public String getColor() {
    return color;
  }

  public VehicleType getType() {
    return type;
  }

  public double getCapacityKg() {
    return capacityKg;
  }

  public double getAreaM2() {
    return areaM2;
  }

  public double getVolumeM3() {
    return volumeM3;
  }

  /* Setters */

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setType(VehicleType type) {
    this.type = type;
  }

  public void setCapacityKg(double capacityKg) {
    this.capacityKg = capacityKg;
  }

  public void setAreaM2(double areaM2) {
    this.areaM2 = areaM2;
  }

  public void setVolumeM3(double volumeM3) {
    this.volumeM3 = volumeM3;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
            "id=" + id +
            ", brand='" + brand + '\'' +
            ", model='" + model + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", color='" + color + '\'' +
            ", type=" + type +
            ", capacityKg=" + capacityKg +
            ", areaM2=" + areaM2 +
            ", volumeM3=" + volumeM3 +
            '}';
  }

}
