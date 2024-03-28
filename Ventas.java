package com.coderhouse.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "ventas")
public class Ventas {

	@Id
	@Column(name = "Id_operacion")
	private Integer Id_Operacion;
	@Column(name = "forma_Pago")
	private String formaPago;
	@Column(name = "clase_producto")
	private Integer volumenVenta;
	
	@OneToMany(mappedBy = "Ventas")
	private List<Ventas> ventas;
	
	public Ventas() {
		
	}

	public Integer getId_Operacion() {
		return Id_Operacion;
	}

	public void setId_Operacion(Integer id_Operacion) {
		Id_Operacion = id_Operacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getVolumenVenta() {
		return volumenVenta;
	}

	public void setVolumenVenta(Integer volumenVenta) {
		this.volumenVenta = volumenVenta;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}
	
	
	
}
