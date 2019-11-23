package kr.ac.springboot.term.experience;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExperienceController {
	
	@Autowired
	private ExperienceRepository repo;
	
	
    @GetMapping("/experience")
    public void experienceIndex(Model model){
    	model.addAttribute("result", repo.findAllByOrderByBnoDesc());
    }
    
    @GetMapping("/{bno}")
    public String view(@PathVariable("bno") long bno, Model model){
        if (repo.findById(bno).isPresent()) {
            model.addAttribute("result", repo.findById(bno).get());
        } else {
            return "errors/404";
        }
        return "/item";
    }
    
    @GetMapping("/register")
    public void registerGET(@ModelAttribute("vo") Experience vo) {
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") Experience vo){
        repo.save(vo);
        return "redirect:/experience";
    }
    
    @GetMapping("/{bno}/delete")
    public String delete(@PathVariable("bno") long bno) {
        if (repo.findById(bno).isPresent()) {
            repo.deleteById(bno);
        } else {
            return "errors/404";
        }
        return "redirect:/experience";
    }
    
    
    @GetMapping("/{bno}/edit")
    public String editGet(@PathVariable("bno") long bno,@ModelAttribute("vo") Experience vo, Model model){
        if (repo.findById(bno).isPresent()) {
            model.addAttribute("vo", repo.findById(bno).get());
        } else {
            return "errors/404";
        }
        return "/edit";
    }

    
    @PostMapping("/edit")
    public String editPost(@ModelAttribute("vo") Experience vo) {
        Optional<Experience> experience = repo.findById(vo.getBno());
        if (experience.isPresent()) {         	
        	experience.get().setTitle(vo.getTitle());
        	experience.get().setContent(vo.getContent());  
        	experience.get().setRole(vo.getRole());  
        	experience.get().setName(vo.getName());           
            repo.save(experience.get());
            
        } else {
            repo.save(vo);
        }
        return "redirect:/experience";
    }
    
}


