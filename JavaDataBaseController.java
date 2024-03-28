package com.coderhouse.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.coderhouse.entidades.Cliente;
import com.coderhouse.entidades.Producto;
import com.coderhouse.repository.ClienteRepository;
import com.coderhouse.repository.ProductoRepository;
import com.coderhouse.repository.VentasRepository;

public class JavaDataBaseController {
	
	private static final String DATA_BASE = "coderhouse";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private Connection connection;
	public Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Conexion exitosa a la bbdd" + DATA_BASE);
			} catch(SQLException e) {
				System.err.println("Error al establecer la conexion con la bbdd" + e.getMessage());
			}
		}
		return connection;
	}
	//metodo para cerrar la conexion
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				System.out.println("La conexion ha sido cerrada");
				
			} catch(SQLException e) {
				System.err.println("Error al cerrar la conexion " + e.getMessage());
			}
		}
	}
	
	//metodos
	public void mostrarMenu() {
		try {
			Scanner scanner = new Scanner(System.in);
			
			int opcion = -1;
			do {
				try {
					System.out.println("Menu: ");
					System.out.println("1. Listar productos");
					System.out.println("2. Agregar productos");
					System.out.println("3. Buscar productods por codigo");
					System.out.println("4. Modificar procutos por codigo");
					System.out.println("5. Eliminar productos");
					System.out.println("6. Agregar clientes");
					System.out.println("7: Salir");
					System.out.println("Ingresar opcion: ");
					
					if (scanner.hasNextInt()) {
						opcion = scanner.nextInt();
						scanner.nextLine();
					}else {
						System.out.println("Entrada invalida, debe ingresar una de las opciones disponibles");
						scanner.nextLine();
						continue; //vuelve a iniciar otra vez
					}
					
					switch (opcion) {
					case 1: ListarTodosLosProductos();
					break;
					case 2: AgregarProductos();
					break;
					case 3: buscarProductoPorId();
					break;
					case 4: modificarProductoPorId();
					break;
					case 5: eliminarProductoPorId();
					break;
					case 6: agregarCliente();
					break;
					case 0 : System.out.println("Saliendo del programa");
					break;
					default: System.err.println("Opcion invalida, intente otra vez");
					}
				}catch (Exception inputMissmatchException) {
					System.err.println("Error, ingrese un numero valido");
					scanner.nextLine();
					opcion = -1;
				}
			} while (opcion != 0);
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	
	
	@Autowired
	private ProductoRepository productoRepository;
	private ClienteRepository clienteRepository;
	private VentasRepository ventasRepository;
	
	//metodos
	public void ListarTodosLosProductos() {
		List<Producto> listaProductos = productoRepository.findAll();
		if (listaProductos.isEmpty()) {
			System.out.println("No hay productos para mostrar");	
}else {
	System.out.println("Lista de productos: ");
	for (Producto producto : listaProductos) { //para lista los productos con su precio
		System.out.println("El producto es " + producto.getNombreProducto() + 
			" , y vale $" + producto.getCosto()	);
	}
}
}

	public void AgregarProductos() {  //para agregar productos al cliente que compra
		
		List<Cliente> listaCliente = clienteRepository.findAll(); 
		if (listaCliente.isEmpty()) {
			System.out.println("No hay clientes existentes");
			Scanner scanner = new Scanner(System.in);
			Cliente cliente = new Cliente();
			System.out.println("Ingrese el dni del cliente");
			cliente.setDni(null);
			scanner.nextLine();
			cliente.setNombre(scanner.nextLine());
			scanner.nextLine();
			cliente.setApellido(scanner.nextLine ());
			scanner.nextLine();
			System.out.println("Clientes existentes: ");
			for (Cliente cliente1 : listaCliente) {
				System.out.println(" " + cliente1.getNombre() + " " + cliente1.getApellido());
			}
			int clienteId;
			Cliente clienteSeleccionado = null;
			boolean clienteValido = false;
			
			while(!clienteValido) {
				try {
					System.out.println("Seleccione el id del cliente:");
					clienteId = scanner.nextInt();
					clienteSeleccionado = clienteRepository.findById(clienteId).orElse(null);
					if(clienteSeleccionado != null) {
						clienteValido = true;
					} else {
						System.out.println("El id del cliente no es valido");
					}
					} catch(Exception inputMismatchException) {
						System.err.println("El id del  cliente seleccionado no es valido");
						scanner.nextLine();						
				}
				
				Producto producto = new Producto();
				producto.setCliente(clienteSeleccionado);
				productoRepository.save(producto);
				System.out.println("Producto agregado a la lista del cliente.");
			}
		}
	}

	
	public void buscarProductoPorId() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el codigo del producto buscado:");
		Integer codigo = scanner.nextInt();
		Producto producto = productoRepository.findById(codigo).orElse(null);
		if(producto != null) {
			System.out.println("Producto seleccionado " + producto.getNombreProducto());
		} else {
			System.out.println("El producto de codigo " + codigo + " no fue encontrado");
		}
	}
	
	public void modificarProductoPorId() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el codigo del producto a modificar:");
		Integer codigo = scanner.nextInt();
		Producto producto = productoRepository.findById(codigo).orElse(null);
		if(producto != null) {
			System.out.println("Producto encontrado " + producto.getNombreProducto());
			System.out.println("Ingrese el nuevo nombre:");
			String nuevoNombre = scanner.nextLine();
			producto.setNombreProducto(nuevoNombre);
			productoRepository.save(producto);
			System.out.println("El producto fue modificado correctamente");
		} else {
			System.out.println("El producto de codigo " + codigo + " no fue encontrado");
		}
	}

	public void eliminarProductoPorId() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el codigo del producto a modificar:");
		Integer codigo = scanner.nextInt();
		Producto producto = productoRepository.findById(codigo).orElse(null);
		if(producto != null) {
			productoRepository.delete(producto);
			System.out.println("El producto fue eliminado correctamente");
		} else {
			System.out.println("El producto de codigo " + codigo + " no fue encontrado");
		}
	}
	
	public void agregarCliente() {
		Cliente cliente = new Cliente();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre del cliente:");
		cliente.setNombre(scanner.nextLine());
		cliente.setApellido(scanner.nextLine());
		clienteRepository.save(cliente);
		System.out.println("Cliente guardado exitosamente");
	}
	

}
