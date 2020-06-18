package com.api.serasa.repositorio;

import org.springframework.data.repository.Repository;

import com.api.serasa.modelo.Empresa;

public interface EmpresaRepositorio extends Repository<Empresa, Integer>{

	Empresa save(Empresa e);
	Empresa findByCodigo(int codigo);
	
}
