package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Disciplina;
import br.com.senac.repository.DisciplinaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository drepo;
	
	public List<Disciplina> listaTodasDisciplinas() {
		return drepo.findAll();
	}
	
	public Disciplina salvar(Disciplina disciplina) {
		return drepo.save(disciplina);
	}
	
	public Disciplina buscaPorId(Integer id) throws ObjectNotFoundException {
		Optional<Disciplina> disciplina = drepo.findById(id);
		return disciplina.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com o ID: " + id));
	}

	public void excluir(Integer id) {
		drepo.deleteById(id);
	}
	
	public Disciplina alterar(Disciplina objDisc) {
		Disciplina disciplina = buscaPorId(objDisc.getId());
		disciplina.setNome(objDisc.getNome());
		return salvar(disciplina);
		
	}
	
}
