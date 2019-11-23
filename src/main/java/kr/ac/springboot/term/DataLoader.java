package kr.ac.springboot.term;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import kr.ac.springboot.term.experience.Experience;
import kr.ac.springboot.term.experience.ExperienceRepository;
import kr.ac.springboot.term.resume.Resume;
import kr.ac.springboot.term.resume.ResumeRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private ExperienceRepository ExperienceRepository;

	
    @Override
    public void run(ApplicationArguments args) {
    	IntStream.range(1,20).forEach(i -> resumeRepository.save(new Resume("rno"+i)));  
    	IntStream.range(1,20).forEach(i -> ExperienceRepository.save(new Experience("title"+i, "content"+i,"role"+i,"name",resumeRepository.findById((long)i).orElse(null))));
    	
    }

}