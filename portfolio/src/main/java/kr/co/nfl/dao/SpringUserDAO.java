package kr.co.nfl.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.nfl.domain.SpringUser;

@Repository
public class SpringUserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//이메일 중복 체크 메소드
	public List<String> emailCheck() {
		return sqlSession.selectOne("springuser.emailcheck");
	}
	
	//닉네임 중복 체크 메소드
	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("springuser.nicknamecheck", nickname);
	}
	
	//회원가입 처리 메소드
	public int join(SpringUser springUser) {
		return sqlSession.insert("springuser.join", springUser);
	}
}

