package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ashokit.entities.CityMaster;

public interface CityMasterRepository extends JpaRepository<CityMaster, Integer>{

	List<CityMaster> findByStateId(Integer cityId);
	
}
