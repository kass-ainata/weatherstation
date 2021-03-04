package com.bankcanada.climate.station.core.repo;

import com.bankcanada.climate.station.core.model.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {
    WeatherStation findByNameAndProvinceAndDate(String name, String province, LocalDate date);
    List<WeatherStation> findByDate(LocalDate date);
    List<WeatherStation> findAllByDateBetweenOrderByDate (LocalDate dateStart, LocalDate dateEnd);
    List<WeatherStation> findAllByOrderByDateAsc();
}