package com.cancino.denunciaservice.validator;

import  com.cancino.denunciaservice.entity.denuncia;
import  com.cancino.denunciaservice.exception.ValidateServiceException;
public class denunciaValidator {
	public static void save(denuncia Denuncia) {
		if (Denuncia.getDni()==null||Denuncia.getDni().isEmpty()){
			throw new ValidateServiceException("El DNI es requerido");
		}
		if(Denuncia.getDni().length()>8) {
			throw new ValidateServiceException("El DNI es muy largo");
		}

		if (Denuncia.getFecha_denuncia()==null){
			throw new ValidateServiceException("la fecha es requerido");
		}

		if (Denuncia.getTitulo()==null||Denuncia.getTitulo().isEmpty()){
			throw new ValidateServiceException("El titulo es requerido");
		}
		if(Denuncia.getTitulo().length()>30) {
			throw new ValidateServiceException("El titulo es muy largo");}
		
		if (Denuncia.getDireccion()==null||Denuncia.getDireccion().isEmpty()){
			throw new ValidateServiceException("La direccion es requerida");
		}
		if(Denuncia.getDireccion().length()>200) {
			throw new ValidateServiceException("La direccion es muy larga");
		}
		if (Denuncia.getDescripcion()==null||Denuncia.getDescripcion().isEmpty()){
			throw new ValidateServiceException("La descripcion es requerido");
		}
		if(Denuncia.getDescripcion().length()>255) {
			throw new ValidateServiceException("La descripcion es muy largo");
		}
		
	}
}
