package com.theshoes.jsp.board.contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theshoes.jsp.board.model.dto.BoardDTO;
import com.theshoes.jsp.board.model.service.BoardService;

@WebServlet("/board/detail")
public class DetailBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 4391912972246555440L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("categoryOrder"));
		
		int categoryOrder = Integer.parseInt(request.getParameter("categoryOrder"));
		
		System.out.println("detailSer - categoryOrder : " + categoryOrder);
		
		BoardService boardService = new BoardService();
		BoardDTO noticeDetail = boardService.selectNoticeDetail(categoryOrder);
		
		System.out.println("noticeDetail : " + noticeDetail);
		
		String path = "";
		if(noticeDetail != null) {
			path = "/WEB-INF/views/board/boardDetail.jsp";
			request.setAttribute("notice", noticeDetail);
		} else {
			path = "/WEB-INF/views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}