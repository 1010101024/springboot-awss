package ks.ac.springboot.term.boardreply;

import org.springframework.data.repository.CrudRepository;
import kr.ac.springboot.term.resume.Resume;
import java.util.List;

public interface WebBoardReplyRepository extends CrudRepository<WebBoardReply, Long> {
	 List<WebBoardReply> findAllByBoardOrderByUpdatedateDesc(Resume resume);
}






