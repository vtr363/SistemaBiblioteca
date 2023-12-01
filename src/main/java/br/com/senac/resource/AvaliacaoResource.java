package br.com.senac.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.AlunoDisciplina;
import br.com.senac.entity.Avaliacao;
import br.com.senac.entity.Disciplina;
import br.com.senac.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {

	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao() {
		List<Avaliacao> listaAvaliacao = avaliacaoService.findAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao av) {
		
		av = avaliacaoService.save(av);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(av.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{idAluno}/{idDisciplina}", method=RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina) {
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disc = new Disciplina();
		disc.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disc);
		
		Avaliacao av = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(av);
	}
	
}
