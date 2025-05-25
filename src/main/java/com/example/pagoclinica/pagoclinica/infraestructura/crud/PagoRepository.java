package com.example.pagoclinica.pagoclinica.infraestructura.crud;

import com.example.pagoclinica.pagoclinica.infraestructura.entity.PagoClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoClinica, Long> {
    List<PagoClinica> findByEstado(String estado);
    

    
    List<PagoClinica> findByCitaId(Long citaId);

    @Query("SELECT SUM(pc.monto) FROM PagoClinica pc WHERE pc.fechaPago = :fecha")
    BigDecimal sumMontoByFechaPago(@Param("fecha") LocalDate fecha);

    @Query("SELECT pc.metodoPago, COUNT(pc) FROM PagoClinica pc GROUP BY pc.metodoPago ORDER BY COUNT(pc) DESC")
    List<Object[]> findMetodosPagoMasUsados();

    @Query("SELECT pc.fechaPago, SUM(pc.monto) AS totalIngresos, COUNT(pc.id) AS numeroPagos " +
        "FROM PagoClinica pc " +
        "GROUP BY pc.fechaPago " +
        "ORDER BY pc.fechaPago DESC")
    List<Object[]> findResumenIngresosAgrupadoPorFecha();
}