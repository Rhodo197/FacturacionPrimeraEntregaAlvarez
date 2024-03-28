package com.coderhouse.entidades;



import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
	@Column(name = "Id_producto")
	private Integer Id_Producto;
	@Column(name = "nombre_producto")
	private String nombreProducto;
	@Column(name = "clase_producto")
	private String claseProducto;
	@Column (name = "costo")
	private double costo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column (name = "dni", insertable = false, updatable = false)
	
	private Integer dni;
	
	@ManyToOne
	@JoinColumn(name = "Id_Operacion")
	private Ventas ventas;
	
	@Column (name = "Id_Operacion", insertable = false, updatable = false)
	private Integer Id_Operacion;
	
	@Override
	public int hashCode() {
		return Objects.hash(Id_Operacion, Id_Producto, claseProducto, cliente, costo, dni, nombreProducto, ventas);
	}



	


	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Integer getDni() {
		return dni;
	}



	public void setDni(Integer dni) {
		this.dni = dni;
	}



	public Ventas getVentas() {
		return ventas;
	}



	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}



	public Integer getId_Operacion() {
		return Id_Operacion;
	}



	public void setId_Operacion(Integer id_Operacion) {
		Id_Operacion = id_Operacion;
	}



	public Producto() {
		
	}
	
	

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	

	public Integer getId_Producto() {
		return Id_Producto;
	}

	public void setId_Producto(Integer id_Producto) {
		Id_Producto = id_Producto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getClaseProducto() {
		return claseProducto;
	}

	public void setClaseProducto(String claseProducto) {
		this.claseProducto = claseProducto;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(Id_Operacion, other.Id_Operacion) && Objects.equals(Id_Producto, other.Id_Producto)
				&& Objects.equals(claseProducto, other.claseProducto) && Objects.equals(cliente, other.cliente)
				&& Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(dni, other.dni) && Objects.equals(nombreProducto, other.nombreProducto)
				&& Objects.equals(ventas, other.ventas);
	}


}
