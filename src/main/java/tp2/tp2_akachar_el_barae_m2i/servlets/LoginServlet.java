package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
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
    private static final String VLOGIN = "admin";
    private static final String VPASSWORD = "1234";

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Utilisateur utilisateur = new Utilisateur(VLOGIN, VPASSWORD);

        if (VLOGIN.equals(login) && utilisateur.verifierMotDePasse(password)) {
            HttpSession session = req.getSession(true);
            session.setMaxInactiveInterval(300);
            session.setAttribute("utilisateur", utilisateur);
            session.setAttribute("panier", new Panier());
            res.sendRedirect("CatalogueServlet");
        } else {
            res.sendRedirect("login.html?error=true");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.sendRedirect("login.html");
    }
}
