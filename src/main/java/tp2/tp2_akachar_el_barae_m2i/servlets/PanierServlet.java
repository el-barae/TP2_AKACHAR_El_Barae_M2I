package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Panier;
import tp2.tp2_akachar_el_barae_m2i.model.Produit;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        HttpSession session = req.getSession(false);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        Panier panier = (Panier) session.getAttribute("panier");
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Votre panier</title>");
            out.println("<style>");
            out.println(".total { font-size: 16px; font-weight: bold; padding: 15px 0; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Votre panier</h2>");

            if (panier.getListe_Produits().isEmpty()) {
                out.println("<div class='empty-cart'>");
                out.println("<p>Votre panier est vide.</p>");
                out.println("</div>");
            } else {
                out.println("<ul>");
                for (Produit produit : panier.getListe_Produits()) {
                    out.println("<li>" + produit.getNom() + " - " + produit.getPrix() + "</li>");
                }

                out.println("<div class='total'>");
                out.println("Total: " + panier.getTotal() + " DH");
                out.println("</div>");
            }
            out.println("<a href='CatalogueServlet' class='btn'>Retour au catalogue</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
