package com.cancino.denunciaservice.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class denunciaDTO {
	private int id;
	private String dni;
	private Date fecha_denuncia;
	private String titulo;
	private String direccion;
	private String descripcion;

}
