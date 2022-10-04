package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransporRepository  extends JpaRepository<transport,Long> {


    List<transport> findByemployee_idEquals( long employeeID);


}
