package com.cancino.denunciaservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cancino.denunciaservice.entity.denuncia;

public interface denunciaService {
	public List<denuncia> findAll(Pageable page);
	public List<denuncia> findByDni(String dni, Pageable page);
	public denuncia findById(int id);
	public denuncia save(denuncia Denuncia);
	public denuncia update(denuncia Denuncia);
	public void delete(int id);

}
