package com.br.una.airport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_airports")
@Getter
@Setter
public class Airport { // É uma entidade, (MODELO) "É a classe que representa uma tabela do banco de dados".

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeroporto")
    private Long airportId;

    @Column(name = "nome_aeroporto")
    private String airportName;

    @Column(name = "codigo_iata")
    private String iataCode;

    @Column(name = "cidade")
    private String city;

    @Column(name = "codigo_pais_iso")
    private String isoCountryCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Double height;

}
