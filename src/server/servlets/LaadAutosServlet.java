package server.servlets;

import Domain.Klant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LaadAutosServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		Klant k = (Klant)req.getSession().getAttribute("klant");
		for(int i = 0;i < k.getAutoList().size(); i++){
			req.setAttribute("auto"+i, k.getAutoList().get(i).toString());
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("klant_auto's.jsp");
		rd.forward(req,resp);
	}
}
