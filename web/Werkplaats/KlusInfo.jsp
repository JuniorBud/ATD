<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Klus Info</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
<%@ page import="Domain.Werkplaats"%>
<%@ page import="Domain.Monteur"%>
<%@ page import="Domain.User"%>
<%@ page import="Domain.Klus"%>
<%@ page import="Domain.Weekplanning"%>
<%@ page import="Domain.Klant"%>
<%@ page import="Domain.Auto"%>
<%@ page import="java.util.ArrayList"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<form action="KlusAfronden.jsp" method="get">
			<div>
				<%
					Object msgs = request.getAttribute("error");
					if (msgs != null) {
						out.println("<div class='error'>" + msgs + "</div>");
					}
				%>
				<%
					Werkplaats werkplaats = (Werkplaats) application.getAttribute("werkplaats");
					Klus werk;
					Object dag = request.getParameter("dag");
					Object dagDeel = request.getParameter("dagDeel");
					Object week = request.getParameter("week");
					Object jaar = request.getParameter("jaar");
					Monteur monteur = (Monteur) request.getSession().getAttribute("monteur");
					if (dag == null || dagDeel == null || week == null || jaar == null || monteur == null) {
						RequestDispatcher rd = request.getRequestDispatcher("WeekplanningOverzicht.jsp");
						request.setAttribute("error", "1");
						rd.forward(request, response);
					} else {
						Weekplanning w = werkplaats.zoekWeekplanning(Integer.parseInt(week.toString()), Integer.parseInt(jaar.toString()));
						if (w == null) {
							RequestDispatcher rd = request.getRequestDispatcher("WeekplanningOverzicht.jsp");
							request.setAttribute("error", "2");
							rd.forward(request, response);
						} else {
							werk = w.getKlus(monteur, Integer.parseInt(dag.toString()), Integer.parseInt(dagDeel.toString()));
							request.getSession().setAttribute("Klus", werk);
							if (werk == null) {
								RequestDispatcher rd = request.getRequestDispatcher("WeekplanningOverzicht.jsp");
								request.setAttribute("error", "3" + Integer.parseInt(dag.toString()) + Integer.parseInt(dagDeel.toString()));
								rd.forward(request, response);
							} else {
								Klant k = null;
								Auto a = null;
								boolean afspraak = true;
								if (werk.getAfspraak() == null) {
									k = werk.getKlant();
									a = werk.getAuto();
									afspraak = false;
								}
				%>
				<div>
					<label class="label">Klus type:</label>
					<div><%=werk.getKlusType()%></div>
				</div>
				<div>
					<label class="label">Klant:</label>
					<div>
						<%
							if (!afspraak) {
						%>
						<%=(k.getNaam() + " " + k.getTussenvoegsel() + " " + k.getAchternaam())%>
						<%
							} else {
						%>
						<%=(werk.getAfspraak().getVoornaam() + " " + werk.getAfspraak().getTussenvoegsel() + " " + werk.getAfspraak().getAchternaam())%>
						<%
							}
						%>
					</div>
				</div>
				<%
					if (!afspraak) {
				%>
				<div>
					<label class="label">Auto merk:</label>
					<div><%=a.getMerk()%></div>
				</div>
				<div>
					<label class="label">Auto model:</label>
					<div><%=a.getModel()%></div>
				</div>
				<%
					}
				%>
				<div>
					<label class="label">Kenteken:</label>
					<%
						if (!afspraak) {
					%>
					<div><%=a.getKenteken()%></div>
					<%
						} else {
					%>
					<div><%=werk.getAfspraak().getKenteken()%></div>
					<%
						}
					%>
				</div>
				<div>

					<label class="label">Tijd:</label>
					<div><%=werkplaats.getTijd(werk.getBeginTijd(), werk.getEindTijd())%></div>
				</div>
				<div id="monteurs">
					<label class="label">Uitvoerende monteurs:</label>
					<div id="monteursList">
						<ul>
							<%
								for (Monteur m : werk.getMonteurs()) {
							%>
							<li><%=m.getNaam() + " " + m.getTussenvoegsel() + " " + m.getAchternaam()%></li>
							<%
								}
							%>
						</ul>
					</div>

				</div>
				<%
					if (!afspraak) {
				%>
				<div>
					<label class="label">Opmerking aanmaken:</label>
					<div><%=werk.getOpmerkingAanmaken()%></div>
				</div>
				<%
					if (!werk.getOpmerkingAfronden().equals("")) {
				%>
				<div>
					<label class="label">Opmerking afronden:</label>
					<div><%=werk.getOpmerkingAfronden()%></div>
				</div>
				<%
					}
								}
								if (!afspraak) {
									User u = (User) request.getSession().getAttribute("user");
									int perm = u.getPermission();
									if (werk.getManuren() == 0.0 && (perm == 1 || perm == 3)) {
				%>
				<div class="button">
					<input type="submit" name="afronden" value="Klus afronden" />
				</div>

				<%
					}
								}
				%>
			</div>
		</form>
		<%
			User u = (User) request.getSession().getAttribute("user");
						int perm = u.getPermission();
						if (werk.getManuren() != 0 && !werk.isHeeftFactuur() && perm < 3) {
		%>
		<form action="/THO4/Facturatie/FactuurBeheer.do"
			method="post">
			<div>
				<div class="button">
					<input type='submit' value='Maak Factuur aan' name='toevoegen' />
				</div>
				<input type='hidden' value='<%=dag.toString()%>' name='dag'>
				<input type='hidden' value='<%=dagDeel.toString()%>' name='dagDeel'>
				<input type='hidden' value='<%=week.toString()%>' name='week'>
				<input type='hidden' value='<%=jaar.toString()%>' name='jaar'>
			</div>
		</form>
		<%
			}

					}
				}
			}
		%>
	</div>
</body>
</html>
