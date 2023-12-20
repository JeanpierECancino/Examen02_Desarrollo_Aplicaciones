package com.cancino.denunciaservice.converter;
import org.springframework.stereotype.Component;

import com.cancino.denunciaservice.dto.denunciaDTO;
import com.cancino.denunciaservice.entity.denuncia;

@Component
public class denunciaConverter  extends AbstractConverter<denuncia, denunciaDTO> {

	@Override
	public denunciaDTO fromEntity(denuncia entity) {
		if (entity==null) return null;
		return denunciaDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha_denuncia(entity.getFecha_denuncia())
				.titulo(entity.getTitulo())
				.direccion(entity.getDireccion())
				.descripcion(entity.getDescripcion())
				.build();	
		}

	@Override
	public denuncia fromDTO(denunciaDTO dto) {
		if (dto==null) return null;
		return denuncia.builder()
				.id(dto.getId())
				.dni(dto.getDni())
				.fecha_denuncia(dto.getFecha_denuncia())
				.titulo(dto.getTitulo())
				.direccion(dto.getDireccion())
				.descripcion(dto.getDescripcion())
				.build();
		}
}
