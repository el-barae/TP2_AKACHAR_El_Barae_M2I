package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Produit;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebServlet("/CatalogueServlet")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Produit> produits = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        produits.add(new Produit(1, "Ordinateur portable", 4000));
        produits.add(new Produit(2, "Smartphone", 1000));
        produits.add(new Produit(3, "Tablette", 1500));
        produits.add(new Produit(4, "Casque", 120));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Catalogue de produits</title>");
//            out.println("<style>");
//            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }");
//            out.println(".header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }");
//            out.println(".user-info { display: flex; align-items: center; }");
//            out.println(".buttons { display: flex; gap: 10px; }");
//            out.println(".btn { padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; color: white; text-align: center; }");
//            out.println(".btn-primary { background-color: #4CAF50; }");
//            out.println(".btn-secondary { background-color: #2196F3; }");
//            out.println(".btn-danger { background-color: #f44336; }");
//            out.println(".products { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 20px; }");
//            out.println(".product { border: 1px solid #ddd; border-radius: 4px; padding: 15px; }");
//            out.println(".product h3 { margin-top: 0; }");
//            out.println(".product p { margin-bottom: 15px; }");
//            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='header'>");
            out.println("<div class='user-info'>");
            out.println("<h2>Bienvenue, " + utilisateur.getLogin() + "</h2>");
            out.println("</div>");
            out.println("<div class='buttons'>");
            out.println("<a href='PanierServlet' class='btn btn-secondary'>Voir le panier</a>");
            out.println("<a href='LogoutServlet' class='btn btn-danger'>Deconnexion</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<h2>Catalogue de produits</h2>");
            out.println("<div class='products'>");
            for (Produit produit : produits) {
                out.println("<div class='product'>");
                out.println("<h3>" + produit.getNom() + "</h3>");
                out.println("<p>Prix: " + produit.getPrix() + " €</p>");
                out.println("<a href='AjouterProduitServlet?id=" + produit.getId() + "' class='btn btn-primary'>Ajouter au panier</a>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
