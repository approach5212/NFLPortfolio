package kr.co.nfl;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.nfl.domain.SpringUser;

//Spring에서 JUnit4 라이브러리를 사용하기 위한 설정
@RunWith(SpringJUnit4ClassRunner.class)
//locations에 설정된 파일들을 실행시켜서 메모리에 로드하기 위한 설정
//프레임워크마다 설정 파일의 경로가 다르므로 web.xml 에 설정된 애용을 확인하고 	
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class PorfolioTest {
	//데이터베이스 연결을 확인핼 때 주입
	@Autowired
	private DataSource dataSource;
	
	//연결 테스트를 위한 메소드
	@Test
	public void connectTest() {
		try {
			System.out.println(dataSource.getConnection());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void sqlTest() {
		//email 중복검사
		//존해하는 이메일이므로 이메일이 출력
		System.out.println(sqlSession.selectOne("springuser.emailcheck", "ggangpae1@gmail.com"));
		//없는 이메일이므로 null 출력
		System.out.println(sqlSession.selectOne("springuser.emailcheck", "sblee5212@gmail.com"));
		System.out.println(sqlSession.selectOne("springuser.nicknamecheck","군계"));
		//없는 닉네임이므로 null 출력
		System.out.println(sqlSession.selectOne("springuser.nicknamecheck",	"관리자"));
		
		//데이터 삽입 확인
		SpringUser user = new SpringUser();
		user.setEmail("sblee5212@gmail.com");
		user.setPw("qwe123");
		user.setNickname("관리자");
		user.setImage("sample.png");
		//삽입 삭제 갱신은 정수를 리턴하는데 리턴되는 값은 영향받은 행의 개수
		System.out.println(sqlSession.insert("srpinguser.join", user));		
	}
}
