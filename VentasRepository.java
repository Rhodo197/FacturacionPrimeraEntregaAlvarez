package com.coderhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.entidades.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{

}
