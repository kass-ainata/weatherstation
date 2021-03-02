package com.bankcanada.climate.station.core.repo;

import com.bankcanada.climate.station.core.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByName(String name);
    List<Station> findAllByName(String name);
    Station findByNameAndProvinceAndDate(String name, String province, LocalDate date);
    List<Station> findByDate(LocalDate date);
    List<Station> findAllByDateBetweenOrderByDate (LocalDate dateStart, LocalDate dateEnd);
    List<Station> findAllByOrderByDateAsc();
}