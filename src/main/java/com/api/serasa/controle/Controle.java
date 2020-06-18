package com.api.serasa.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.serasa.modelo.Empresa;
import com.api.serasa.repositorio.EmpresaRepositorio;


@RestController
@RequestMapping("/api")
public class Controle {
	
	// Repositório
	@Autowired
	private EmpresaRepositorio repositorioEmpresa;
	
	
	// Início
	@RequestMapping(value="", method = RequestMethod.GET)
	public @ResponseBody String inicio() {
		return "<h1>Seja bem vindo a API da Serasa</h1>";
	}
	
	// Cadastrar 3 empresas
	@RequestMapping(value="/empresa", method = RequestMethod.POST)
	public @ResponseBody String cadastrar3Empresas() {

		Empresa e1 = new Empresa();
		e1.setEmpresa("Empresa A");
		e1.setPontuacao(50);
		
		Empresa e2 = new Empresa();
		e2.setEmpresa("Empresa B");
		e2.setPontuacao(50);
		
		Empresa e3 = new Empresa();
		e3.setEmpresa("Empresa C");
		e3.setPontuacao(50);
		
		repositorioEmpresa.save(e1);
		repositorioEmpresa.save(e2);
		repositorioEmpresa.save(e3);
		return "Ok";		
	}

	// Cadastrar nova Nota Fiscal
	@RequestMapping(value="/nf/{codigo}", method = RequestMethod.PUT)
	public @ResponseBody Empresa cadastrarNF(@PathVariable("codigo") int codigo) {
		Empresa e = repositorioEmpresa.findByCodigo(codigo);
		
		int percentual = (int) (e.getPontuacao() * 0.02) + e.getPontuacao();
		
		e.setPontuacao(percentual);
		
		return repositorioEmpresa.save(e);
	}
	
	// Cadastrar nova pendência
	@RequestMapping(value="/pendencia/{codigo}", method = RequestMethod.PUT)
	public @ResponseBody Empresa pendencia(@PathVariable("codigo") int codigo) {
		Empresa e = repositorioEmpresa.findByCodigo(codigo);
		
		int percentual = (int) Math.ceil((int) e.getPontuacao() * 0.04);
		
		int novaPontuacao = e.getPontuacao() - percentual;
		
		e.setPontuacao(novaPontuacao);
		
		return repositorioEmpresa.save(e);
	}

	
}
