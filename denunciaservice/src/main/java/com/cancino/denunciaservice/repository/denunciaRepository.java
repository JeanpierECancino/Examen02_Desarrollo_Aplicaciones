package com.cancino.denunciaservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cancino.denunciaservice.entity.denuncia;

@Repository
public interface denunciaRepository extends JpaRepository<denuncia, Integer> {
	List<denuncia> findByDniContaining(String dni,Pageable page);
	denuncia findByDni(String dni);
}
