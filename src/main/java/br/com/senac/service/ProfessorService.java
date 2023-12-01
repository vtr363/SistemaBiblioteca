package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository profRepo;
	
	public List<Professor> listaTodosProfessores() {
		return profRepo.findAll();
	}
	
	public Professor buscaPorId(Integer id) throws ObjectNotFoundException {
		Optional<Professor> professor = profRepo.findById(id);
		return professor.orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com o ID: " + id));
	}
	
	public Professor salvar(Professor prof) {
		return profRepo.save(prof);
	}
	
	public void excluir(Integer id) {
		profRepo.deleteById(id);
	}

	public Professor alterar(Professor objProf) {
		Professor professor = buscaPorId(objProf.getId());
		professor.setNome(objProf.getNome());
		return salvar(professor);
	}
	
}
