package kr.ac.springboot.term.resume;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.springboot.term.experience.Experience;

@Entity
@Table(name ="resume")
public class Resume {
	
	public Resume() {
	}
	
	public Resume(String name) {
		this.name = name;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="resume", fetch=FetchType.LAZY)
    private List<Experience> experience;
    
    public Long getRno() {
        return rno;
    }

    public void setRno(Long rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Resume [rno=" + rno + ", name=" + name + ", experience=" + experience + "]";
	}

    
    
}
