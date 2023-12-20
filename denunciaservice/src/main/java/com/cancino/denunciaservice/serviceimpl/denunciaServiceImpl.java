package com.cancino.denunciaservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cancino.denunciaservice.entity.denuncia;
import com.cancino.denunciaservice.exception.GeneralServiceException;
import com.cancino.denunciaservice.exception.NoDataFoundException;
import com.cancino.denunciaservice.exception.ValidateServiceException;
import com.cancino.denunciaservice.repository.denunciaRepository;
import com.cancino.denunciaservice.service.denunciaService;
import com.cancino.denunciaservice.validator.denunciaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class denunciaServiceImpl implements denunciaService{

	 @Autowired
	private denunciaRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<denuncia> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<denuncia> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni,page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public denuncia findById(int id) {
		try {
			denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public denuncia save(denuncia Denuncia) {
		try {
			denunciaValidator.save(Denuncia);
			Denuncia.setActivo(true);
			denuncia registro=repository.save(Denuncia);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public denuncia update(denuncia Denuncia) {
		try {
			denunciaValidator.save(Denuncia);
			denuncia registro=repository.findById(Denuncia.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			registro.setDni(Denuncia.getDni());
			registro.setFecha_denuncia(Denuncia.getFecha_denuncia());
			registro.setTitulo(Denuncia.getTitulo());
			registro.setDireccion(Denuncia.getDireccion());
			registro.setDescripcion(Denuncia.getDescripcion());
			repository.save(registro);
			return registro;
		}catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}
	 
}
