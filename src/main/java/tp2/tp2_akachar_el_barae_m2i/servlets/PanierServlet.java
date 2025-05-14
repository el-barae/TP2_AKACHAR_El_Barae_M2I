package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        Panier panier = (Panier) session.getAttribute("panier");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Votre panier</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }");
            out.println(".header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }");
            out.println(".user-info { display: flex; align-items: center; }");
            out.println(".btn { padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; color: white; text-align: center; display: inline-block; margin: 0 5px; }");
            out.println(".btn-primary { background-color: #4CAF50; }");
            out.println(".btn-secondary { background-color: #2196F3; }");
            out.println(".btn-danger { background-color: #f44336; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            out.println("th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".empty-cart { text-align: center; padding: 50px; color: #777; }");
            out.println(".total { font-size: 18px; font-weight: bold; text-align: right; padding: 15px 0; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='header'>");
            out.println("<div class='user-info'>");
            out.println("<h2>Panier de " + utilisateur.getLogin() + "</h2>");
            out.println("</div>");
            out.println("<div>");
            out.println("<a href='LogoutServlet' class='btn btn-danger'>Déconnexion</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<h2>Contenu de votre panier</h2>");

            if (panier.getListe_Produits().isEmpty()) {
                out.println("<div class='empty-cart'>");
                out.println("<p>Votre panier est vide.</p>");
                out.println("</div>");
            } else {
                out.println("<table>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Produit</th>");
                out.println("<th>Prix</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (Produit produit : panier.getListe_Produits()) {
                    out.println("<tr>");
                    out.println("<td>" + produit.getNom() + "</td>");
                    out.println("<td>" + produit.getPrix() + " €</td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");
                out.println("<div class='total'>");
                out.println("Total: " + panier.getTotal() + " €");
                out.println("</div>");
            }
            out.println("<a href='CatalogueServlet' class='btn btn-secondary'>Retour au catalogue</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
