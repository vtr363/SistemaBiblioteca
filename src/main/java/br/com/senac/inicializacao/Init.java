package br.com.senac.inicializacao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.AlunoDisciplina;
import br.com.senac.entity.Avaliacao;
import br.com.senac.entity.Disciplina;
import br.com.senac.entity.Turma;
import br.com.senac.repository.AlunoRepository;
import br.com.senac.repository.DisciplinaRepository;
import br.com.senac.service.AvaliacaoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;
import br.com.senac.service.TurmaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	TurmaService turmaService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	ProfessorService profService;
	
	@Autowired
	DisciplinaRepository disciplinaRepo;
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Arthur");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("João");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Pedro");
		
		Disciplina java = new Disciplina();
		java.setNome("Java 1");
		
		Disciplina java2 = new Disciplina();
		java2.setNome("Java 2");
		
		disciplinaService.salvar(java);
		disciplinaService.salvar(java2);
		

		Disciplina arquitetura = new Disciplina();
		arquitetura.setNome("Arquitetura");
		
		disciplinaService.salvar(arquitetura);
		
		Turma ads = new Turma();
		ads.setNome("ADS");
		
		turmaService.salvar(ads);
		
		Turma rede = new Turma();
		rede.setNome("Rede");
		
		turmaService.salvar(rede);
		
		aluno1.setTurma(ads);
		aluno2.setTurma(ads);
		aluno3.setTurma(rede);
		aluno4.setTurma(rede);
		
		aluno1.setDisciplinas(Arrays.asList(java,arquitetura,java2));
		aluno2.setDisciplinas(Arrays.asList(java,java2));
		aluno3.setDisciplinas(Arrays.asList(arquitetura,java2));
		aluno4.setDisciplinas(Arrays.asList(java,arquitetura));

		alunoRepo.save(aluno1);
		alunoRepo.save(aluno2);
		alunoRepo.save(aluno3);
		alunoRepo.save(aluno4);
		
		Avaliacao avaliacaoAluno1 = new Avaliacao();
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno1);
		alunoDisciplina.setDisciplina(arquitetura);
		
		avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina);
		avaliacaoAluno1.setConceito("A");
		avaliacaoService.save(avaliacaoAluno1);
		
		AlunoDisciplina joaoJava = new AlunoDisciplina();
		joaoJava.setAluno(aluno3);
		joaoJava.setDisciplina(java);
		
		Avaliacao avaliacaoJoaoJava = new Avaliacao();
		avaliacaoJoaoJava.setAlunoDisciplina(joaoJava);
		avaliacaoJoaoJava.setConceito("B");
		avaliacaoService.save(avaliacaoJoaoJava);
		
		Avaliacao av1 = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
		System.out.println("Aluno: " + av1.getAlunoDisciplina().getAluno().getNome());
		System.out.println("Disciplina: " + av1.getAlunoDisciplina().getDisciplina().getNome());
		System.out.println("Avaliação: " + av1.getConceito());
		
	}
	
}