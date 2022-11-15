package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.CityMaster;

@Repository
public interface CityRepository extends JpaRepository<CityMaster, Long> {

    @Query("SELECT city from CityMaster city WHERE city.name = :name AND city.stateName = :state AND city.deleted = :deleted")
    CityMaster findCityByNameAndState(@Param("name") String name, @Param("state") String state, @Param("deleted") boolean delete);
}
