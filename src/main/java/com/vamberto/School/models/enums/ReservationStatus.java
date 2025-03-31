package com.vamberto.School.models.enums;

public enum ReservationStatus {
    PENDING,     // Reserva criada, aguardando confirmação
    CONFIRMED,   // Reserva confirmada, aguardando retirada
    CANCELLED,   // Reserva cancelada pelo usuário
    EXPIRED,     // Tempo expirado, reserva não retirada
    COMPLETED    // Livro retirado, reserva finalizada
}
