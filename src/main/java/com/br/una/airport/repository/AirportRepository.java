package com.br.una.airport.repository;

import com.br.una.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> { // O repositório liga a minha aplicação com o banco de dados.
    Optional<Airport> findByIataCode(String iata);

    @Transactional
    @Modifying
    @Query(value = "DELETE from tb_airports where codigo_iata = :iata", nativeQuery = true)
    void deleteByIataCode(String iata); // Caso o método de cima não funcione.

}
