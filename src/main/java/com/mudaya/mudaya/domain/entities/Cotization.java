package com.mudaya.mudaya.domain.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cotizations")
public class Cotization {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "originadress", length = 100, nullable = false)
  private String originAddress;

  @Column(name = "destinationaddress", length = 100, nullable = false)
  private String destinationAddress;

  @Column(name = "requesteddate", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime requestedDate;

  @ManyToMany
  @JoinTable(
          name = "cotizations_vehicles",
          joinColumns = @JoinColumn(name = "cotizacion_id"),
          inverseJoinColumns = @JoinColumn(name = "vehicle_id")
  )
  private List<Vehicle> vehicles;

  @Column(name = "numberofhelpers", nullable = false)
  private int numberOfHelpers;

  @Column(nullable = false)
  private double price;

  @Column(length = 250)
  private String notes;

  public Cotization() {} // Constructor vacío requerido por JPA

  public Cotization(UUID id, Client client, String originAddress, String destinationAddress, LocalDateTime requestedDate, List<Vehicle> vehicles, int numberOfHelpers, double price, String notes) {
    this.id = id;
    this.client = client;
    this.originAddress = originAddress;
    this.destinationAddress = destinationAddress;
    this.requestedDate = requestedDate;
    this.vehicles = vehicles;
    this.numberOfHelpers = numberOfHelpers;
    this.price = price;
    this.notes = notes;
  }

  /* Getters */
  public UUID getId() {
    return id;
  }

  public Client getClient() {
    return client;
  }

  public String getOriginAddress() {
    return originAddress;
  }

  public String getDestinationAddress() {
    return destinationAddress;
  }

  public LocalDateTime getRequestedDate() {
    return requestedDate;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public int getNumberOfHelpers() {
    return numberOfHelpers;
  }

  public double getPrice() {
    return price;
  }

  public String getNotes() {
    return notes;
  }

  /* Setters */
  public void setId(UUID id) {this.id = id;}

  public void setClient(Client client) {
    this.client = client;
  }

  public void setOriginAddress(String originAddress) {
    this.originAddress = originAddress;
  }

  public void setDestinationAddress(String destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public void setRequestedDate(LocalDateTime requestedDate) {
    this.requestedDate = requestedDate;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public void setNumberOfHelpers(int numberOfHelpers) {
    this.numberOfHelpers = numberOfHelpers;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "Cotization{" +
            "id=" + id +
            ", client=" + client +
            ", originAddress='" + originAddress + '\'' +
            ", destinationAddress='" + destinationAddress + '\'' +
            ", requestedDate=" + requestedDate +
            ", vehicles=" + vehicles +
            ", numberOfHelpers=" + numberOfHelpers +
            ", price=" + price +
            ", notes='" + notes + '\'' +
            '}';
  }
}
