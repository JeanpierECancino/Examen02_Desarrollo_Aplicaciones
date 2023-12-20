package com.cancino.denunciaservice.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cancino.denunciaservice.entity.denuncia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="Denuncia")
public class denuncia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true,nullable=false,length=8)
	private String dni;
	
	@Column(name="fecha",nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fecha_denuncia;
	
	@Column(unique=false,nullable=false,length=30)
	private String titulo;
	
	@Column(unique=false,nullable=false,length=200)
	private String direccion;

	@Column(unique=true,nullable=true,length=250)
	private String descripcion;

	@Column(name="activo",nullable=false)
	private Boolean activo;
	
}
