package com.br.una.airport.controller;

import com.br.una.airport.dto.AirportResponseDTO;
import com.br.una.airport.dto.AirportRequestDTO;
import com.br.una.airport.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/aeroportos") // Controller -> Essa é a classe responsável por receber a requisição e retornar uma resposta para o client com os end-points.
public class AirportController {

   private final AirportService airportService;

   @GetMapping
   public ResponseEntity<Page<AirportResponseDTO>> findAll(Pageable pageable) { // END-POINT que me retorna uma página com uma lista de aeroportos.

       Page<AirportResponseDTO> airports = airportService.findAll(pageable);

       return ResponseEntity.ok().body(airports);
   }

   @GetMapping(value = "/{iata}")
    public ResponseEntity<AirportResponseDTO> findByIataCode(@PathVariable String iata) { // END-POINT que me retorna um aeroporto baseado no código IATA

       AirportResponseDTO airportDTO = airportService.findByIataCode(iata);

       return ResponseEntity.ok().body(airportDTO);
   }

    @PostMapping
    public ResponseEntity<AirportResponseDTO> save(@RequestBody @Valid AirportRequestDTO airportDTO, UriComponentsBuilder uriBuilder) { // END-POINT que salva um aeroporto no banco.

       AirportResponseDTO airportResponseDTO = airportService.save(airportDTO);

        URI uri = uriBuilder.path("/v1/aeroportos/{iata}").buildAndExpand(airportResponseDTO.getAirportId()).toUri();

        return ResponseEntity.created(uri).body(airportResponseDTO);
    }

    @PutMapping(value = "/{iata}")
    public ResponseEntity<AirportResponseDTO> update(@RequestBody AirportRequestDTO airportDTO, @PathVariable String iata) { // END-POINT que atualiza um aeroporto no banco através do código IATA.

       AirportResponseDTO airportResponseDTO = airportService.update(airportDTO, iata);

       return ResponseEntity.ok().body(airportResponseDTO);
    }

    @DeleteMapping(value = "/{iata}")
    public ResponseEntity<String> delete(@PathVariable String iata) { // END-POINT que deleta um aeroporto no banco através do código IATA.

       String message = airportService.delete(iata);

       return ResponseEntity.ok().body(message);
    }

}
