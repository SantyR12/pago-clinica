package com.example.pagoclinica.pagoclinica.domain.dto;

public class EstadoPagoCitaRequestDTO {
    private String estadoPago;


    public EstadoPagoCitaRequestDTO() {
    }

    public EstadoPagoCitaRequestDTO(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}