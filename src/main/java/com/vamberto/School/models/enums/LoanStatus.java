package com.vamberto.School.models.enums;

public enum LoanStatus {
    ACTIVE,         // Empréstimo ativo **
    OVERDUE,        // Atrasado (não devolvido no prazo ) **
    RETURNED,       // Livro devolvido no prazo
    LATE_RETURNED,  // Livro devolvido com atraso
    FINE_PAID,      // multa paga
    LOST,           // Livro perdido
    LOST_NO_PENALTY, // Livro perdido sem penalidades ao usuario
    ALL,
    FORRETURN
}
