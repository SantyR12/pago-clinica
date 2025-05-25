package com.example.pagoclinica.pagoclinica.controller;

import com.example.pagoclinica.pagoclinica.domain.dto.CitaDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.EstadoPagoCitaRequestDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.IngresosPorFechaDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.PagoDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.ResumenDiarioIngresosDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.PacienteDTO; 
import com.example.pagoclinica.pagoclinica.domain.service.PayClinicalService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagos") 
public class PagoClinicaController {

    @Autowired
    private PayClinicalService payClinicalService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> obtenerTodosLosPagos() {
        return ResponseEntity.ok(payClinicalService.obtenerTodosLosPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        PagoDTO pago = payClinicalService.obtenerPagoPorId(id);
        if (pago != null) {
            return ResponseEntity.ok(pago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PagoDTO> registrarPago(@RequestBody PagoDTO payClinicalDTO) {
        PagoDTO nuevoPago = payClinicalService.registrarPago(payClinicalDTO);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(
            @PathVariable Long id,
            @RequestBody PagoDTO payClinicalDTO) {
        PagoDTO pagoActualizado = payClinicalService.actualizarPago(id, payClinicalDTO);
        if (pagoActualizado != null) {
            return ResponseEntity.ok(pagoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        boolean eliminado = payClinicalService.eliminarPago(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<PagoDTO> procesarPago(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String metodoPago = payload.getOrDefault("metodoPago", "EFECTIVO");
        PagoDTO pagoActualizado = payClinicalService.procesarPago(id, metodoPago);
        if (pagoActualizado != null) {
            return ResponseEntity.ok(pagoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPorCitaId(@PathVariable Long citaId) {
        return ResponseEntity.ok(payClinicalService.obtenerPagosPorCitaId(citaId));
    }

    @GetMapping("/reporte/ingresos")
    public ResponseEntity<String> generarReporteIngresos() {
        return ResponseEntity.ok(payClinicalService.generarReporteIngresos());
    }

    @GetMapping("/reporte/ingresos/por-fecha")
    public ResponseEntity<IngresosPorFechaDTO> obtenerIngresosPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fecha) {
        IngresosPorFechaDTO ingresos = payClinicalService.obtenerTotalIngresosPorFechaEspecifica(fecha);
            return ResponseEntity.ok(ingresos);
    }

    @GetMapping("/reporte/ingresos/desglose-fechas")
    public ResponseEntity<List<ResumenDiarioIngresosDTO>> obtenerDesgloseIngresosPorFechas() {
        List<ResumenDiarioIngresosDTO> resumen = payClinicalService.obtenerResumenIngresosDiarios();
        if (resumen.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPendientes() {
        return ResponseEntity.ok(payClinicalService.obtenerPagosPendientes());
    }

    @GetMapping("/citas-externas")
    public ResponseEntity<List<CitaDTO>> obtenerTodasLasCitasExternas() {
        List<CitaDTO> citas = payClinicalService.obtenerTodasLasCitasExternas();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/citas-externas/{id}")
    public ResponseEntity<CitaDTO> obtenerCitaExternaPorId(@PathVariable Long id) {
        CitaDTO cita = payClinicalService.obtenerCitaExternaPorId(id);
        return ResponseEntity.ok(cita);
    }

    @PutMapping("/citas-externas/{citaId}/estado")
    public ResponseEntity<CitaDTO> actualizarEstadoCitaExterna(
            @PathVariable Long citaId,
            @RequestBody EstadoPagoCitaRequestDTO estadoPagoRequest) {
        CitaDTO citaActualizada = payClinicalService.actualizarEstadoCitaExterna(citaId, estadoPagoRequest);
        return ResponseEntity.ok(citaActualizada);
    }
    @GetMapping("/pacientes-externos")
    public ResponseEntity<List<PacienteDTO>> obtenerTodosLosPacientesExternos() {
        List<PacienteDTO> pacientes = payClinicalService.obtenerTodosLosPacientesExternos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/pacientes-externos/{id}")
    public ResponseEntity<PacienteDTO> obtenerPacienteExternoPorId(@PathVariable Long id) {
        PacienteDTO paciente = payClinicalService.obtenerPacienteExternoPorId(id);
        return ResponseEntity.ok(paciente); 
    }

}