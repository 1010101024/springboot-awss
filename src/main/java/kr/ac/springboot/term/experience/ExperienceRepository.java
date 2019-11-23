package kr.ac.springboot.term.experience;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ExperienceRepository extends CrudRepository<Experience, Long>{
	List<Experience> findAllByOrderByBnoDesc();
}
