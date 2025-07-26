package com.mudaya.mudaya.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mudaya.mudaya.domain.enums.MovingState;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movings")
public class Move {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private MovingState state;

  @Column(name = "movingdatetime", nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime movingDateTime;

  @ManyToOne
  @JoinColumn(name = "cotization_id")
  private Cotization cotization;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "move", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MoveAssignment> transporters = new ArrayList<>();

  public Move() {} // Constructor vacío para JPA

  public Move(UUID id, List<MoveAssignment> transporters, User user, Cotization cotization, LocalDateTime movingDateTime, MovingState state) {
    this.id = id;
    this.transporters = transporters;
    this.user = user;
    this.cotization = cotization;
    this.movingDateTime = movingDateTime;
    this.state = state;
  }

  /* Getters */
  public UUID getId() {
    return id;
  }

  public List<MoveAssignment> getTransporters() {
    return transporters;
  }

  public User getUser() {
    return user;
  }

  public Cotization getCotization() {
    return cotization;
  }

  public LocalDateTime getMovingDateTime() {
    return movingDateTime;
  }

  public MovingState getState() {
    return state;
  }

  /* Setters */

  public void setTransporters(ArrayList<MoveAssignment> transporters) {
    this.transporters = transporters != null ? transporters : new ArrayList<>();
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setCotization(Cotization cotization) {
    this.cotization = cotization;
  }
  
  public void setMovingDateTime(LocalDateTime movingDateTime) {
    this.movingDateTime = movingDateTime;
  }

  public void setState(MovingState state) {
    this.state = state;
  }
}
