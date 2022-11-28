package com.br.una.airport.service;

import com.br.una.airport.dto.AirportResponseDTO;
import com.br.una.airport.dto.AirportRequestDTO;
import com.br.una.airport.entity.Airport;
import com.br.una.airport.repository.AirportRepository;
import com.br.una.airport.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AirportService { // É a classe que é chamada pelo controller e busca as informações do repository. Classe onde é desenvolvida a lógica.

    private final AirportRepository airportRepository;

    public Page<AirportResponseDTO> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable).map(AirportResponseDTO::new);
    }

    public AirportResponseDTO findByIataCode(String iata) {

        Airport airport = returnAirportByIataCode(iata);

        return new AirportResponseDTO(airport);
    }

    public AirportResponseDTO save(AirportRequestDTO airportDTO) {

        Airport airport = convertFromDTOToEntity(airportDTO);

        airport = airportRepository.save(airport);

        return new AirportResponseDTO(airport);
    }

    public AirportResponseDTO update(AirportRequestDTO airportDTO , String iata) {

        Airport airportDataBase = returnAirportByIataCode(iata);

        updateAirport(airportDataBase , airportDTO);

        airportDataBase = airportRepository.save(airportDataBase);

        return new AirportResponseDTO(airportDataBase);
    }

    public String delete(String iata) {

        airportRepository.deleteByIataCode(iata);

        return "Airoport with iata code: "+iata+" was deleted!";
    }

    private Airport returnAirportByIataCode(String iata) {
       return airportRepository.findByIataCode(iata).orElseThrow(()-> new ResourceNotFoundException("Resource iata_code: "+iata+" not found"));
    }

    private Airport convertFromDTOToEntity(AirportRequestDTO airportDTO) {
        Airport airport = new Airport();
        airport.setAirportName(airportDTO.getAirportName());
        airport.setCity(airportDTO.getCity());
        airport.setHeight(airportDTO.getHeight());
        airport.setIataCode(airportDTO.getIataCode());
        airport.setLatitude(airportDTO.getLatitude());
        airport.setLongitude(airportDTO.getLongitude());
        airport.setIsoCountryCode(airportDTO.getIsoCountryCode());

        return airport;
    }

    private void updateAirport(Airport airport , AirportRequestDTO airportDTO) {

        if(airportDTO.getAirportName()!=null) {
            airport.setAirportName(airportDTO.getAirportName());
        }
        if(airportDTO.getCity()!=null) {
            airport.setCity(airportDTO.getCity());
        }
        if(airportDTO.getIataCode()!=null) {
            airport.setIataCode(airportDTO.getIataCode());
        }
        if(airportDTO.getIsoCountryCode()!=null) {
            airport.setIsoCountryCode(airportDTO.getIsoCountryCode());
        }

    }

}
