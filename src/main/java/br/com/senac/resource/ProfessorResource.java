package br.com.senac.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorResource {

	@Autowired
	private ProfessorService profService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Professor>> listarProfessor() {
		List<Professor> profs = profService.listaTodosProfessores();
		return ResponseEntity.ok().body(profs);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Professor> buscaPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Professor prof = profService.buscaPorId(id);
		return ResponseEntity.ok().body(prof);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Professor objProf) {
		Professor prof = profService.salvar(objProf);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prof.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		profService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Professor objProf, @PathVariable Integer id) {
		objProf.setId(id);
		profService.alterar(objProf);
		return ResponseEntity.noContent().build();
	}
	
}
