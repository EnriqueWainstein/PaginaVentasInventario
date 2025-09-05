package com._ip.pagina.Entidades;

import org.springframework.context.annotation.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
  private static final  String mensajeMail= "El correo ingresado debe ser valido";
  private static final String mensajeNombre= "el nombre debe tener al menos 5 caracteres";
  private static final String mensajeContraseña="La contraseña debe tener 5 letras y un numero";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(nullable = false, unique = true)
	@Size(min = 5, message = mensajeNombre)
	private String userName;
	
	@Column(nullable = false, unique = true)
	@Pattern(regexp = ".*\\d.*", message = mensajeContraseña)
	@Size(min =5, message= mensajeContraseña)
	private String contrasenia;
	
	@Email(message = mensajeMail)
	@Column(nullable = false, unique = true)
	private String mail;
	
	 @Enumerated(EnumType.STRING)
	private Rol rol;
	
	
	public enum Rol{
		ADMINISTRADOR,EMPLEADO,CLIENTE
		
	}


	public static String getMensajemail() {
		return mensajeMail;
	}


	public static String getMensajenombre() {
		return mensajeNombre;
	}


	public int getId() {
		return Id;
	}


	public String getUserName() {
		return userName;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}


	public String getMail() {
		return mail;
	}


	public Rol getRol() {
		return rol;
	}
	
	
}