package ru.wxw.coursemodel.wxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wxw.coursemodel.wxw.entity.Professor;
import ru.wxw.coursemodel.wxw.repository.ProfessorRepository;

import java.util.List;

@Service
@Transactional
public class ProfessorService {
    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    @Transactional(readOnly = true)
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Professor findById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }
    public Professor save(Professor professor){
        return professorRepository.save(professor);
    }
    public Professor update(Professor professor, Long id){
        professor.setId(id);
        return professorRepository.save(professor);
    }
    public void delete(Long id) {
        professorRepository.deleteById(id);
    }
}
