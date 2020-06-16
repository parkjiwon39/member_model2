package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;


public class LogoutAction implements Action {
	private String path;
	
	public LogoutAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse rep) {
	
	
		//세션 해제 후 이동
		//req.getSession(boolean); => 세션이 없는 경우 true,false 값에 의해서 세션 생성여부 결정
		//req.getSession(); => 세션이 없는 경우 새로운 세션을 무조건 생성
		HttpSession session = req.getSession(false);
		session.removeAttribute("login");
	
	//경로와 방법 설정
	return new ActionForward(path, true);
	}

}
