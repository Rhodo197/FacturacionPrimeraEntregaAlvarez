package com.coderhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderhouse.entidades.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
