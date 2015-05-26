package server.servlets;

import Domain.Brandstof;
import Domain.Onderdeel;
import Domain.Voorraad;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Frits on 18-5-2015.
 */
@WebServlet(name = "VoorraadBeheerServlet")
    public class VoorraadBeheerServlet extends HttpServlet {
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Voorraad voorraad = null;

            ServletContext sc = getServletContext();

            synchronized (sc) {
                voorraad = (Voorraad) sc.getAttribute("voorraad");
            }

            RequestDispatcher rd = null;
            if (req.getParameter("nieuw") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    rd = req.getRequestDispatcher("BrandstofToevoegen.jsp");
                } else {
                    rd = req.getRequestDispatcher("OnderdeelToevoegen.jsp");
                }
            } else if (req.getParameter("wijzigen") != null || req.getParameter("verwijderen") != null || req.getParameter("bestellen") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    ArrayList<Brandstof> brandstoffen = new ArrayList<Brandstof>();
                    for (int i = 0; i < voorraad.getAlleBrandstof().size(); i++) {
                        if (req.getParameter("" + i) != null) {
                            brandstoffen.add(voorraad.getAlleBrandstof().get(i));
                        }
                    }
                    req.getSession().setAttribute("brandstoffen", brandstoffen);
                    if (req.getParameter("wijzigen") != null) {
                        rd = req.getRequestDispatcher("BrandstofWijzigen.jsp");
                    } else if (req.getParameter("verwijderen") != null) {
                        rd = req.getRequestDispatcher("BrandstofVerwijderen.jsp");
                    } else {
                        rd = req.getRequestDispatcher("BrandstofBestellen.jsp");
                    }
                } else {
                    ArrayList<Onderdeel> onderdelen = new ArrayList<Onderdeel>();
                    for (int i = 0; i < voorraad.getAlleOnderdelen().size(); i++) {
                        if (req.getParameter("" + i) != null) {
                            onderdelen.add(voorraad.getAlleOnderdelen().get(i));
                        }
                    }
                    req.getSession().setAttribute("onderdelen", onderdelen);
                    if (req.getParameter("wijzigen") != null) {
                        rd = req.getRequestDispatcher("OnderdelenWijzigen.jsp");
                    } else if (req.getParameter("verwijderen") != null) {
                        rd = req.getRequestDispatcher("OnderdelenVerwijderen.jsp");
                    } else {
                        rd = req.getRequestDispatcher("OnderdelenBestellen.jsp");
                    }
                }
            } else if (req.getParameter("gewijzigd") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    ArrayList<Brandstof> brandstoffen = (ArrayList<Brandstof>) req.getSession().getAttribute("brandstoffen");
                    boolean correct = true;
                    for (Brandstof b : brandstoffen) {
                        if (isInt(req.getParameter(b.getBrandstofType() + b.getTankstationID() + "0")) > 0) {
                            if (isInt(req.getParameter(b.getBrandstofType() + b.getTankstationID() + "1")) > 0) {
                                b.setMinimaalAantalLiters(Integer.parseInt(req.getParameter(b.getBrandstofType() + b.getTankstationID() + "0")));
                                b.setAantalLiters(Integer.parseInt(req.getParameter(b.getBrandstofType() + b.getTankstationID() + "1")));
                            } else {
                                correct = false;
                            }
                        } else {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        rd = req.getRequestDispatcher("BrandstofWijzigen.jsp");
                        req.setAttribute("error", "Niet overal zijn correcte getallen ingevoerd");
                    } else {
                        req.getSession().setAttribute("brandstoffen", null);
                        req.setAttribute("success", "De brandstoffen zijn succesvol gewijzigd in het systeem");
                        rd = req.getRequestDispatcher("BrandstofOverzicht.jsp");
                    }
                } else {
                    ArrayList<Onderdeel> onderdelen = (ArrayList<Onderdeel>) req.getSession().getAttribute("onderdelen");
                    boolean correct = true;
                    for (Onderdeel o : onderdelen) {
                        if (isInt(req.getParameter(o.getOnderdeelNummer() + "0")) > 0) {
                            if (isInt(req.getParameter(o.getOnderdeelNummer() + "1")) > 0) {
                                o.setOnderdeelVoorraad(Integer.parseInt(req.getParameter(o.getOnderdeelNummer() + "0")));
                                o.setOnderdeelMinimaleVoorraad(Integer.parseInt(req.getParameter(o.getOnderdeelNummer() + "1")));
                            } else {
                                correct = false;
                            }
                        } else {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        rd = req.getRequestDispatcher("OnderdelenWijzigen.jsp");
                        req.setAttribute("error", "Niet overal zijn correcte getallen ingevoerd");
                    } else {
                        req.getSession().setAttribute("onderdelen", null);
                        req.setAttribute("success", "De onderdelen zijn succesvol gewijzigd in het systeem");
                        rd = req.getRequestDispatcher("OnderdelenOverzicht.jsp");
                    }
                }
            } else if (req.getParameter("verwijderd") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    ArrayList<Brandstof> brandstoffen = (ArrayList<Brandstof>) req.getSession().getAttribute("brandstoffen");
                    for (Brandstof b : brandstoffen) {
                        if (req.getParameter(b.getBrandstofType() + b.getTankstationID()) != null) {
                            voorraad.verwijderBrandstof(b);
                        }
                    }
                    req.getSession().setAttribute("brandstoffen", null);
                    req.setAttribute("success", "De brandstoffen zijn succesvol verwijderd uit het systeem");
                    rd = req.getRequestDispatcher("BrandstofOverzicht.jsp");
                } else {
                    ArrayList<Onderdeel> onderdelen = (ArrayList<Onderdeel>) req.getSession().getAttribute("onderdelen");
                    for (Onderdeel o : onderdelen) {
                        if (req.getParameter("" + o.getOnderdeelNummer()) != null) {
                            voorraad.verwijderOnderdeel(o.getOnderdeelNummer());
                        }
                    }
                    req.getSession().setAttribute("onderdelen", null);
                    req.setAttribute("success", "De onderdelen zijn succesvol verwijderd uit het systeem");
                    rd = req.getRequestDispatcher("OnderdelenOverzicht.jsp");
                }
            } else if (req.getParameter("besteld") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    boolean correct = true;
                    ArrayList<Brandstof> brandstoffen = (ArrayList<Brandstof>) req.getSession().getAttribute("brandstoffen");
                    ArrayList<Brandstof> brandstofBestellen = new ArrayList<Brandstof>();
                    ArrayList<Integer> aantallen = new ArrayList<Integer>();
                    for (Brandstof b : brandstoffen) {
                        if (isInt(req.getParameter(b.getBrandstofType() + b.getTankstationID())) > 0) {
                            brandstofBestellen.add(b);
                            aantallen.add(Integer.parseInt(req.getParameter(b.getBrandstofType() + b.getTankstationID())));
                        } else {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        rd = req.getRequestDispatcher("BrandstofWijzigen.jsp");
                        req.setAttribute("error", "Niet overal zijn correcte getallen ingevoerd");
                    } else {
                        req.getSession().setAttribute("brandstoffen", null);
                        voorraad.bestelBrandstof(brandstofBestellen, aantallen);
                        req.setAttribute("success", "De bestellijst is succesvol gegenereert");
                        rd = req.getRequestDispatcher("BrandstofOverzicht.jsp");
                    }
                } else {
                    boolean correct = true;
                    ArrayList<Onderdeel> onderdelen = (ArrayList<Onderdeel>) req.getSession().getAttribute("onderdelen");
                    ArrayList<Onderdeel> onderdelenBestellen = new ArrayList<Onderdeel>();
                    ArrayList<Integer> aantallen = new ArrayList<Integer>();
                    for (Onderdeel o : onderdelen) {
                        if (isInt(req.getParameter("" + o.getOnderdeelNummer())) > 0) {
                            onderdelenBestellen.add(o);
                            aantallen.add(Integer.parseInt(req.getParameter("" + o.getOnderdeelNummer())));
                        } else {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        rd = req.getRequestDispatcher("OnderdelenWijzigen.jsp");
                        req.setAttribute("error", "Niet overal zijn correcte getallen ingevoerd");
                    } else {
                        req.getSession().setAttribute("onderdelen", null);
                        voorraad.bestelOnderdelen(onderdelenBestellen, aantallen);
                        req.setAttribute("success", "De bestellijst is succesvol gegenereert");
                        rd = req.getRequestDispatcher("OnderdelenOverzicht.jsp");
                    }
                }
            } else if (req.getParameter("toegevoegd") != null) {
                if (req.getParameter("soort").equals("brandstof")) {
                    String validation = "";
                    boolean correct = true;
                    String type = req.getParameter("type");
                    String tid = req.getParameter("tid");
                    String minimumLiters = req.getParameter("minimumLiters");
                    String huidigeLiters = req.getParameter("huidigeLiters");
                    String prijsPerLiter = req.getParameter("prijsPerLiter");
                    if (type.equals("")) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        correct = false;
                        validation += "Type mag niet leeg zijn";
                    }
                    if (isInt(tid) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij Tank Station ID";
                        correct = false;
                    }
                    if (isInt(minimumLiters) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij minimaal aantal liters";
                        correct = false;
                    }
                    if (isInt(huidigeLiters) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij minimaal aantal liters";
                        correct = false;
                    }
                    try {
                        Double ppl = Double.parseDouble(prijsPerLiter);
                    } catch (NumberFormatException e) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij prijs per liter";
                        correct = false;
                    }
                    if (correct) {
                        Brandstof b = new Brandstof(type, Integer.parseInt(tid), Integer.parseInt(minimumLiters), Integer.parseInt(huidigeLiters), Double.parseDouble(prijsPerLiter));
                        voorraad.voegBrandstofToe(b);
                        req.setAttribute("success", "Deze brandstof is succesvol aan het systeem toegevoegd");
                        rd = req.getRequestDispatcher("BrandstofOverzicht.jsp");
                    } else {
                        req.setAttribute("validation", validation);
                        rd = req.getRequestDispatcher("BrandstofToevoegen.jsp");
                    }
                } else {
                    String validation = "";
                    boolean correct = true;
                    String onderdeelNummer = req.getParameter("onderdeelNummer");
                    String omschrijving = req.getParameter("omschrijving");
                    String huidigeVoorraad = req.getParameter("huidigeVoorraad");
                    String minimaleVoorraad = req.getParameter("minimaleVoorraad");
                    if (isInt(onderdeelNummer) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij Onderdeel-nummer";
                        correct = false;
                    }
                    if (omschrijving.equals("")) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Omschrijving mag niet leeg zijn";
                        correct = false;
                    }
                    if (isInt(huidigeVoorraad) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij huidige voorraad";
                        correct = false;
                    }
                    if (isInt(minimaleVoorraad) == -1) {
                        if (!validation.equals("")) {
                            validation += "</br>";
                        }
                        validation += "Voer een correct getal in bij minimale voorraad";
                        correct = false;
                    }
                    if (correct) {
                        Onderdeel o = new Onderdeel(omschrijving, Integer.parseInt(onderdeelNummer), Integer.parseInt(minimaleVoorraad), Integer.parseInt(huidigeVoorraad));
                        voorraad.voegOnderdeelToe(o);
                        req.setAttribute("success", "Het onderdeel is succesvol aan het systeem toegevoegd");
                        rd = req.getRequestDispatcher("OnderdelenOverzicht.jsp");
                    } else {
                        req.setAttribute("validation", validation);
                        rd = req.getRequestDispatcher("OnderdeelToevoegen.jsp");
                    }
                }
            }
            synchronized (sc) {
                sc.setAttribute("voorraad", voorraad);
            }

            rd.forward(req, resp);
        }
        /**
         * controleert of de gegeven String een int is.
         * @params de doorgegeven String die moet worden gecontroleerd.
         * @return de int die terug wordt gegeven als het een int is, en anders -1.
         */
        private int isInt(String s) {
            int i;
            if (!s.equals("")) {
                try {
                    i = Integer.parseInt(s);
                } catch (NumberFormatException exc) {
                    i = -1;
                }
            } else {
                i = -1;
            }
            return i;
        }
    }
