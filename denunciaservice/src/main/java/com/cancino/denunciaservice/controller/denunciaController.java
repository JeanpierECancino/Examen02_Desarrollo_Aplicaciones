package com.cancino.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cancino.denunciaservice.converter.denunciaConverter;
import com.cancino.denunciaservice.dto.denunciaDTO;
import com.cancino.denunciaservice.entity.denuncia;
import com.cancino.denunciaservice.service.denunciaService;
import com.cancino.denunciaservice.utils.WrapperResponse;


@RestController
@RequestMapping("/v1/denuncias")
public class denunciaController {

	@Autowired
	private denunciaService service;
	
	@Autowired
	private denunciaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<denunciaDTO>> findAll(
			@RequestParam(value="dni",required=false,defaultValue = "")String dni,
			@RequestParam(value="offset",required=false,defaultValue = "0")int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue = "5")int pageSize
			){
		Pageable page= PageRequest.of(pageNumber,pageSize);
		List<denuncia> denuncias;
		if (dni==null) {
			denuncias=service.findAll(page);
			
		}else {
			denuncias=service.findByDni(dni, page);
		}

		List<denunciaDTO> denunciasDTO=converter.fromEntity(denuncias);
		return new WrapperResponse(true,"success",denunciasDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<denunciaDTO>> findById(@PathVariable("id")int id){
		denuncia Denuncia=service.findById(id);
		denunciaDTO denunciaDTO=converter.fromEntity(Denuncia);
		return new WrapperResponse<denunciaDTO>(true,"success",denunciaDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<denunciaDTO> create(@RequestBody denunciaDTO denunciaDTO){
		denuncia registro=service.save(converter.fromDTO(denunciaDTO));
		denunciaDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<denunciaDTO> update(@PathVariable("id")int id,@RequestBody denunciaDTO denunciaDTO){
		denuncia registro=service.update(converter.fromDTO(denunciaDTO));
		denunciaDTO registroDTO=converter.fromEntity(registro);
		return  new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<denunciaDTO>delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
