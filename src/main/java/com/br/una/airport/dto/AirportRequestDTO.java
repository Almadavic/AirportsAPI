package com.br.una.airport.dto;

import com.br.una.airport.entity.Airport;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class AirportRequestDTO {     // DTO é utilizado para converter dados do banco de dados e/ou limitar para retornar ao client.

    @NotNull
    private String airportName;

    @NotNull
    private String iataCode;

    @NotNull
    private String city;

    @NotNull
    private String isoCountryCode;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Double height;

    public AirportRequestDTO(Airport airport) {
        this.airportName = airport.getAirportName();
        this.iataCode = airport.getIataCode();
        this.city = airport.getCity();
        this.isoCountryCode = airport.getIsoCountryCode();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        this.height = airport.getHeight();
    }
}
