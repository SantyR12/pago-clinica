package com.example.pagoclinica.pagoclinica.domain.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumenDiarioIngresosDTO {
    private LocalDate fecha;
    private BigDecimal totalIngresos;
    private Long numeroPagos;

    // Constructor
    public ResumenDiarioIngresosDTO(LocalDate fecha, BigDecimal totalIngresos, Long numeroPagos) {
        this.fecha = fecha;
        this.totalIngresos = totalIngresos != null ? totalIngresos : BigDecimal.ZERO; 
        this.numeroPagos = numeroPagos != null ? numeroPagos : 0L; 
    }

    // Getters
    public LocalDate getFecha() {
        return fecha;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public Long getNumeroPagos() {
        return numeroPagos;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos != null ? totalIngresos : BigDecimal.ZERO;
    }

    public void setNumeroPagos(Long numeroPagos) {
        this.numeroPagos = numeroPagos != null ? numeroPagos : 0L;
    }
}