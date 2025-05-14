package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Panier;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VALID_LOGIN = "elbarae";
    private static final String VALID_PASSWORD = "abc2002";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Utilisateur utilisateur = new Utilisateur(VALID_LOGIN, VALID_PASSWORD);

        if (VALID_LOGIN.equals(login) && utilisateur.verifierMotDePasse(password)) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(300);
            session.setAttribute("utilisateur", utilisateur);
            session.setAttribute("panier", new Panier());
            response.sendRedirect("CatalogueServlet");
        } else {
            response.sendRedirect("login.html?error=true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
