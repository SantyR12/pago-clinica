package com.example.pagoclinica.pagoclinica.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresosPorFechaDTO {
    private LocalDate fecha;
    private BigDecimal totalIngresos;

    public IngresosPorFechaDTO(LocalDate fecha, BigDecimal totalIngresos) {
        this.fecha = fecha;
        this.totalIngresos = totalIngresos;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }
    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
    
}
