package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;


public class ModifyAction implements Action {
	private String path;
	
	public ModifyAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse rep) {
	//modifyForm.jsp에서 넘긴 값 가져오기
	String current_password = req.getParameter("current_password");
	String new_password = req.getParameter("new_password");
	String confirm_password = req.getParameter("confirm_password");
	
	if(new_password.equals(confirm_password)) {
		//현재 비밀번호가 일치하면
		//새로운 비밀번호로 수정후
		HttpSession session = req.getSession(false);
		MemberVO login = (MemberVO)session.getAttribute("login");
		String userid = login.getUserid();
		//세션 삭제 후 로그인 페이지로 이동
		MemberDAO dao = new MemberDAO();
		int result = dao.passwordUpdate(userid, new_password, current_password);
		if(result>0) {
			session.invalidate();
		}else {
			path="view/moidfyForm.jsp";
		}
	}else {
	//비밀번호 두개가 서로 틀린경우 다시 modifyForm.jsp를 보여주기
		path="view/modifyForm.jsp";
	}
	//경로와 방법 설정
	return new ActionForward(path, true);
	}

}
