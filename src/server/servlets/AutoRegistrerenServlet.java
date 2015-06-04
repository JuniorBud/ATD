package server.servlets;

import Domain.Auto;
import Domain.Klant;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoRegistrerenServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		Klant k = (Klant)req.getSession().getAttribute("klant");
		String merk = req.getParameter("merk");
		String type = req.getParameter("type");
		String kenteken = req.getParameter("kenteken");
		
		if(merk != null && type != null && kenteken != null){
			k.voegAutoToe(new Auto(kenteken,merk,type));
			for(int i = 0; i < k.getAutoList().size(); i++){
				req.setAttribute("auto"+i, k.getAutoList().get(i).toString());
			}
			RequestDispatcher rd = req.getRequestDispatcher("klant_auto_s.jsp");
			rd.forward(req,resp);
		}
		else{
			req.setAttribute("error","Niet alle velden zijn ingevuld!");
			RequestDispatcher rd = req.getRequestDispatcher("nieuwe_auto.jsp");
			rd.forward(req,resp);
		}
	}
}
