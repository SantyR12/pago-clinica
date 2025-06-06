package com.example.pagoclinica.pagoclinica.infraestructura.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.pagoclinica.pagoclinica.domain.dto.CitaDTO;
import com.example.pagoclinica.pagoclinica.domain.dto.EstadoPagoCitaRequestDTO;

import java.util.List; 

@FeignClient(name = "appointment-client", url = "${citas.service.url}")
public interface CitaCliente {

    @GetMapping("/citas/{id}")
    CitaDTO getAppointmentById(@PathVariable("id") Long id);

    @GetMapping("/citas")
    List<CitaDTO> getAllAppointments();


    @PutMapping("/citas/{id}/estado")
    CitaDTO updateAppointmentStatus(@PathVariable("id") Long id, @RequestBody EstadoPagoCitaRequestDTO estadoRequest);
}