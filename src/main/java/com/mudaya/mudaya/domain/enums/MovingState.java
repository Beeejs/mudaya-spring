package com.mudaya.mudaya.domain.enums;

public enum MovingState {
  PENDING,         // La mudanza fue solicitada pero aún no fue confirmada
  IN_PROGRESS,     // El traslado está en curso
  COMPLETED,       // La mudanza se completó exitosamente
  CANCELLED        // Fue cancelada por el cliente o la empresa
}
