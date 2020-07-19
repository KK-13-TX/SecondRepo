package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

	Client findById(int id);

	List<Client> findByNameLike(String name);

	List<Client> findByAgeGreaterThan(int age);

	Client findByAdressLike(String adress);

	@Query("SELECT id, name, age, enrollmentDate FROM Client c WHERE c.enrollmentDate < :limitDate ")
	List<Client> searchEnrollmentBeforeDate(@Param("limitDate") String date);

}
