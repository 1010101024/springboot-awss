package ks.ac.springboot.term.boardreply;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.springboot.term.resume.Resume;

@RestController
@RequestMapping("/replies/*")
public class WebBoardReplyController {

    @Autowired
    private WebBoardReplyRepository replyRepo;

    @Autowired
    private WebBoardReplyRepository boardRepo;

    @GetMapping("/{rno}")
    public ResponseEntity<List<WebBoardReply>> getReplies(@PathVariable("rno") Long rno) {

        Resume resume = new Resume();
        resume.setRno(rno);
        return new ResponseEntity<>(getListByResume(resume), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/{rno}")
    public ResponseEntity<List<WebBoardReply>> addReply(@PathVariable("rno") Long rno, @RequestBody WebBoardReply reply) {

    	Resume resume = new Resume();
        resume.setRno(rno);

        reply.setResume(resume);

        replyRepo.save(reply);

        return new ResponseEntity<>(getListByResume(resume), HttpStatus.CREATED);

    }

    @Transactional
    @DeleteMapping("/{rno}/{dno}")
    public ResponseEntity<List<WebBoardReply>> remove(
            @PathVariable("rno") Long rno,
            @PathVariable("dno") Long dno) {

        replyRepo.deleteById(dno);

        Resume resume = new Resume();
        resume.setRno(rno);

        return new ResponseEntity<>(getListByResume(resume), HttpStatus.OK);

    }


    @Transactional
    @PutMapping("/{rno}")
    public ResponseEntity<List<WebBoardReply>> modify(@PathVariable("rno") Long rno, @RequestBody WebBoardReply reply) {

        replyRepo.findById(reply.getDno()).ifPresent(origin -> {
            origin.setReplyText(reply.getReplyText());
            replyRepo.save(origin);
        });

        Resume resume = new Resume();;
        resume.setRno(rno);

        return new ResponseEntity<>(getListByResume(resume), HttpStatus.OK);
    }

    private List<WebBoardReply> getListByResume(Resume resume) throws RuntimeException {
    	return replyRepo.findAllByBoardOrderByUpdatedateDesc(resume);
    }
}
