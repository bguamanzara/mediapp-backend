package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ISignoVitalDAO;
import com.mitocode.model.SignoVital;
import com.mitocode.service.ISignoVitalService;

@Service
public class SignoVitalServiceImpl implements ISignoVitalService {

	@Autowired
	private ISignoVitalDAO dao;

	@Override
	public SignoVital registrar(SignoVital signoVital) {
		return dao.save(signoVital);
	}

	@Override
	public SignoVital modificar(SignoVital signoVital) {
		return dao.save(signoVital);
	}

	@Override
	public void eliminar(int idSignoVital) {
		//INI-CAMBIO PARA SPRING BOOT 2
		dao.deleteById(idSignoVital);
	}

	@Override
	public SignoVital listarId(int idPaciente) {
		//INI-CAMBIO PARA SPRING BOOT 2
		Optional<SignoVital> opt = dao.findById(idPaciente);
		return opt.isPresent() ? opt.get() : new SignoVital();
	}

	@Override
	public List<SignoVital> listar() {
		return dao.findAll();
	}

	@Override
	public Page<SignoVital> listarPageable(Pageable pageable) {		
		return dao.findAll(pageable);
	}

}
