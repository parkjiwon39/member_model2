package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberVO;
import persistence.MemberDAO;

public class JoinAction implements Action {
	private String path;
	
	public JoinAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		MemberVO member = new MemberVO();
		req.setCharacterEncoding("utf-8");
		member.setUserid(req.getParameter("userid"));
		String password = req.getParameter("password");
		String confirm_password = req.getParameter("confirm_password");
		member.setName(req.getParameter("name"));
		member.setGender(req.getParameter("gender"));
		member.setEmail(req.getParameter("email"));
		
		if(password.equals(confirm_password)){
			//DB작업한 후 회원가입 성공하면
			//로그인 페이지로 이동
			member.setPassword(password);
		}else {
			path="view/joinForm.jsp";
			return new ActionForward(path,true);
		}
		MemberDAO dao = new MemberDAO();
		int result = dao.register(member);
		if(result==0) {
			path="view/joinForm.jsp";
		}
		
		return new ActionForward(path,true);
	}
	
}
