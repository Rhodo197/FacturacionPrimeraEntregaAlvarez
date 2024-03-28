package com.coderhouse.controlador;

public class MainTest {

	public static void main(String[] args) {
		
		JavaDataBaseController controller = new JavaDataBaseController();
		
		controller.getConnection();
		
		controller.mostrarMenu();
		
		controller.AgregarProductos();
		controller.buscarProductoPorId();
		controller.eliminarProductoPorId();
		controller.ListarTodosLosProductos();
		controller.modificarProductoPorId();
		
		
		controller.closeConnection();

	}

}
