package ch.makery.address.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Empleado {
	private SimpleStringProperty nombre;
	private SimpleStringProperty apellidos;
	private SimpleStringProperty correo;
	private ArrayList<SimpleStringProperty> responsabilidades;
	private SimpleStringProperty contrasenia;
	private SimpleStringProperty departamento;
	private SimpleStringProperty posicion;
	private SimpleStringProperty ciudad;
	private SimpleStringProperty puesto;
	private SimpleStringProperty fechaInicio;

	
	public Empleado(String nombre, String apellidos, String correo, ArrayList<String> responsabilidades, String contrasenia, String posicion, String ciudad, String puesto, String fechaInicio) {
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.correo = new SimpleStringProperty(correo);
		for (String responsabilidad : responsabilidades) {
			this.responsabilidades.add(new SimpleStringProperty(responsabilidad));
		}
		this.contrasenia = new SimpleStringProperty(contrasenia);
		this.posicion = new SimpleStringProperty(posicion);
		this.ciudad = new SimpleStringProperty(ciudad);
		this.puesto = new SimpleStringProperty(puesto);
		this.fechaInicio = new SimpleStringProperty(fechaInicio);
	}



	public String getNombre() {
		return nombre.get();
	}



	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}



	public String getApellidos() {
		return apellidos.get();
	}



	public void setApellidos(String apellidos) {
		this.apellidos.set(apellidos);
	}



	public String getCorreo() {
		return correo.get();
	}



	public void setCorreo(String correo) {
		this.correo.set(correo);
	}



	public ArrayList<String> getResponsabilidades() {
		ArrayList<String> resp = new ArrayList<>();
		for (SimpleStringProperty responsabilidad : responsabilidades) {
			resp.add(responsabilidad.get());
		}
		return resp;
	}



	public void setResponsabilidades(ArrayList<String> responsabilidades) {
		this.responsabilidades.clear();
		for (String responsabilidad : responsabilidades) {
			this.responsabilidades.add(new SimpleStringProperty(responsabilidad));
		}
	}



	public String getContrasenia() {
		return contrasenia.get();
	}



	public void setContrasenia(String contrasenia) {
		this.contrasenia.set(contrasenia);
	}



	public String getDepartamento() {
		return departamento.get();
	}



	public void setDepartamento(String departamento) {
		this.departamento.set(departamento);
	}



	public String getPosicion() {
		return posicion.get();
	}



	public void setPosicion(String posicion) {
		this.posicion.set(posicion);
	}



	public String getCiudad() {
		return ciudad.get();
	}



	public void setCiudad(String ciudad) {
		this.ciudad.set(ciudad);
	}
	
	public String getPuesto() {
		return puesto.get();
	}



	public void setPuesto(String puesto) {
		this.puesto.set(puesto);
	}



	public String getFechaInicio() {
		return fechaInicio.get();
	}



	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio.set(fechaInicio);
	}
	
	
	public SimpleStringProperty getNombreSimple() {
		return nombre;
	}


	public void setNombre(SimpleStringProperty nombre) {
		this.nombre = nombre;
	}


	public SimpleStringProperty getApellidosSimple() {
		return apellidos;
	}


	public void setApellidos(SimpleStringProperty apellidos) {
		this.apellidos = apellidos;
	}


	public SimpleStringProperty getCorreoSimple() {
		return correo;
	}


	public void setCorreo(SimpleStringProperty correo) {
		this.correo = correo;
	}


	public ArrayList<SimpleStringProperty> getResponsabilidadesSimple() {
		return responsabilidades;
	}


	public void setResponsabilidadesSimple(ArrayList<SimpleStringProperty> responsabilidades) {
		this.responsabilidades = responsabilidades;
	}


	public SimpleStringProperty getContraseniaSimple() {
		return contrasenia;
	}


	public void setContrasenia(SimpleStringProperty contrasenia) {
		this.contrasenia = contrasenia;
	}


	public SimpleStringProperty getDepartamentoSimple() {
		return departamento;
	}


	public void setDepartamento(SimpleStringProperty departamento) {
		this.departamento = departamento;
	}


	public SimpleStringProperty getPosicionSimple() {
		return posicion;
	}


	public void setPosicion(SimpleStringProperty posicion) {
		this.posicion = posicion;
	}


	public SimpleStringProperty getCiudadSimple() {
		return ciudad;
	}


	public void setCiudad(SimpleStringProperty ciudad) {
		this.ciudad = ciudad;
	}



	public SimpleStringProperty getPuestoSimple() {
		return puesto;
	}



	public void setPuesto(SimpleStringProperty puesto) {
		this.puesto = puesto;
	}



	public SimpleStringProperty getFechaInicioSimple() {
		return fechaInicio;
	}



	public void setFechaInicio(SimpleStringProperty fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	
	


}
