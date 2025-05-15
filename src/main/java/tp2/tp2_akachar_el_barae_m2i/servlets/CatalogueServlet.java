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
import tp2.tp2_akachar_el_barae_m2i.model.Produit;

@WebServlet("/CatalogueServlet")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Produit> produits = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        produits.add(new Produit(1, "PC portable", 4000));
        produits.add(new Produit(2, "Smartphone", 1000));
        produits.add(new Produit(3, "Tablette", 1500));
        produits.add(new Produit(4, "Casque", 120));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Catalogue de produits</title>");
            out.println("<style>");
            out.println(".buttons { display: flex; gap: 10px; margin: 10px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Catalogue de produits</h2>");
            out.println("<ul>");
            for (Produit produit : produits) {
                out.println("<li>" + produit.getNom() + " - " + produit.getPrix() + " | " +"<a href='AjouterProduitServlet?id=" + produit.getId() + "' class='btn'>Ajouter au panier</a>"+"</li>");
            }
            out.println("<div class='buttons'>");
            out.println("<a href='PanierServlet' class='btn'>Voir le panier</a>");
            out.println("<a href='LogoutServlet' class='btn'>Deconnexion</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
