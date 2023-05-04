package controller.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Post;
import repository.PostsDAO;

/*
 * 게시글 목록을 담아 커뮤니티 화면으로 넘겨줄 컨트롤러
 */

@WebServlet("/main/postlist")
public class PostListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8"); // 한글 인코딩

		// 페이징 파라미터 받기
		String page = req.getParameter("page");

		// 글 목록 페이징 처리
		int p;
		if (req.getParameter("page") == null) {
			p = 1;
		} else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		
		// 파라미터 a, b값 설계
		int a = (p - 1) * 10 + 1;
		int b = 10 * p;
		
//=========================================================================
		// 글 목록 가져오기
		List<Post> postList = PostsDAO.findByPostAtoB(a, b);

		// 글 목록 세팅
		req.setAttribute("postList", postList);

//=========================================================================
		int total = PostsDAO.postPasing();
		
		int totalPage = total / 10 + (total % 10 > 0 ? 1 : 0);
		int viewPage = 5;

		int endPage = (((p - 1) / viewPage) + 1) * viewPage + 1;
		if (totalPage < endPage) {
			endPage = totalPage;
		}
		int startPage = ((p - 1) / viewPage) * viewPage + 1;

		int idx = p * 10;

		req.setAttribute("idx", idx);
		req.setAttribute("start", startPage);
		req.setAttribute("last", endPage);
		boolean existPrev = p >= 6;
		boolean existNext = true;
		if (endPage >= totalPage) {
			existNext = false;
		}
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);

		req.getRequestDispatcher("/WEB-INF/views/main/postlist.jsp").forward(req, resp);
	}

}
