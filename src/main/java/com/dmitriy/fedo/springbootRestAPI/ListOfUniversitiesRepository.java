package com.dmitriy.fedo.springbootRestAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ListOfUniversitiesRepository extends JpaRepository<ListOfUniversities, Integer>{
}