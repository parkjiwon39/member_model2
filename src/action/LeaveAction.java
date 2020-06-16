package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;


public class LeaveAction implements Action {
	private String path;
	
	public LeaveAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse rep) {
	//leaveForm.jsp에서 넘긴 값 가져오기
	String userid = req.getParameter("userid");
	String password = req.getParameter("password");
	//DB처리
	//세션해제하기
	MemberDAO dao = new MemberDAO();
	int result = dao.leave(userid, password);
	if(result>0) {
		HttpSession session = req.getSession(false);
		session.removeAttribute("login");
	}else {
		path="view/leaveForm.jsp";
	}
		
	//경로와 방법 설정
	return new ActionForward(path,true);

}
}
