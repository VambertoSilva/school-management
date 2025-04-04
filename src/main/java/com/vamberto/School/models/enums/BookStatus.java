package com.vamberto.School.models.enums;

public enum BookStatus {
    AVAILABLE,  // Disponível para empréstimo
    RESERVED,   // Reservado por alguém
    LOST,       // Perdido
    CHECKED_OUT,// Emprestado
    DAMAGED,     // Danificado
    ALL
}