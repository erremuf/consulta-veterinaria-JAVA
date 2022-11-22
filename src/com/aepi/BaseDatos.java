package com.aepi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {
	

		private Connection conexion = null;
		private PreparedStatement preparedStatement = null;

		public BaseDatos() {

		}

// METODO PARA CONECTAR BBDD.
		public Connection conectar(String ruta) throws ClassNotFoundException, SQLException {
			Class.forName("org.sqlite.JDBC"); 
			Connection conexionBD = DriverManager.getConnection(ruta); 
			return conexionBD;
		}


// METODO PARA INSERTAR EN BBDD.
		public void insertar(String nombre, String tipo, String sexo, String peso, String telefono_duenio) throws ClassNotFoundException {
			
			try {
				// indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
				conexion = this.conectar("jdbc:sqlite:clinica.sqlite");

				// Introducimos en la tabla animales las columnas
				preparedStatement = conexion.prepareStatement("INSERT INTO animales(nombre, tipo, sexo, peso, telefono_duenio) VALUES (?,?,?,?,?)");
				
				// Introducimos los valores
				preparedStatement.setString(1, nombre);
				preparedStatement.setString(2, tipo);
				preparedStatement.setString(3, sexo);
				preparedStatement.setString(4, peso);
				preparedStatement.setString(5, telefono_duenio);

				// Insertamos info
				preparedStatement.executeUpdate();

				// Cerramos
				preparedStatement.close();
				System.out.println("Registro insertado correctamente");
				
			} catch (SQLException ex) {
				System.out.println("No se ha podido insertar el registro: " + ex);
			}
		}


// METODO PARA ACTUALIZAR EN BBDD.
		public void actualizar(String telefono_duenio, String peso) throws ClassNotFoundException {
			try {
				conexion = this.conectar("jdbc:sqlite:clinica.sqlite");
				preparedStatement = conexion.prepareStatement("UPDATE animales SET peso=? WHERE telefono_duenio=?");
				preparedStatement.setString(1, telefono_duenio);
				preparedStatement.setString(2, peso);
				preparedStatement.executeUpdate();
				System.out.println("Registro modificado correctamente");
				preparedStatement.close();
			} catch (SQLException ex) {
				System.out.println("No se ha podido realizar la actualizacion: " + ex);
			}
		}

	
// METODO PARA ELIMINAR EN BBDD.
		public void eliminar(String telefono_duenio) throws ClassNotFoundException {
			try {
				conexion = this.conectar("jdbc:sqlite:clinica.sqlite");
				preparedStatement = conexion.prepareStatement("DELETE FROM animales WHERE telefono_duenio=?");
				preparedStatement.setString(1, telefono_duenio);
				preparedStatement.executeUpdate();
				System.out.println("Registro eliminado correctamente");
				preparedStatement.close();
			} catch (SQLException ex) {
				System.out.println("No se ha podido realizar la eliminacion: " + ex);
			}
		}
		
		
// METODO PARA VISUALIZAR EN BBDD POR NOMBRE.	
		public void visualizarPorNombre(String nombre) throws ClassNotFoundException {
			try {
				conexion = this.conectar("jdbc:sqlite:clinica.sqlite");
				preparedStatement = conexion.prepareStatement("SELECT * FROM animales WHERE nombre=?");
				preparedStatement.setString(1, nombre);
				
				// try con recursos
				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
						System.out.println(
								"ID: " + rs.getInt(1) + " Nombre: " + rs.getString(2) + " Tipo: " + rs.getString(3) + " Sexo: " + rs.getString(4) + " Peso: " + rs.getString(5) + " Telefono del Dueño: " + rs.getString(6));
					}
				}
				preparedStatement.close();
			} catch (SQLException ex) {
				System.out.println("No se ha podido realizar la consulta: " + ex);
			}

		}
		
		
		
// METODO PARA VISUALIZAR TODOS LOS DATOS EN BBDD.		
		public void visualizarTodo() throws ClassNotFoundException {
			try {
				conexion = this.conectar("jdbc:sqlite:clinica.sqlite");
				preparedStatement = conexion.prepareStatement("SELECT * FROM animales");
				// try con recursos
				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
						System.out.print(
								"ID: " + rs.getInt(1) + " Nombre: " + rs.getString(2) + " Tipo: " + rs.getString(3) + " Sexo: " + rs.getString(4) + " Peso: " + rs.getString(5) + " Telefono del Dueño: " + rs.getString(6));
					}
				}
				preparedStatement.close();
			} catch (SQLException ex) {
				System.out.println("No se ha podido realizar la consulta: " + ex);
			}
		}


		
		
}
