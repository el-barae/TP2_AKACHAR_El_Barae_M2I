package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Panier;
import tp2.tp2_akachar_el_barae_m2i.model.Produit;

@WebServlet("/AjouterProduitServlet")
public class AjouterProduitServlet extends HttpServlet {
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
        Panier panier = (Panier) session.getAttribute("panier");
        int productId = Integer.parseInt(request.getParameter("id"));
        Produit produitToAdd = null;
        for (Produit produit : produits) {
            if (produit.getId() == productId) {
                produitToAdd = produit;
                break;
            }
        }
        if (produitToAdd != null) {
            panier.ajouterProduit(produitToAdd);
            session.setAttribute("panier", panier);
        }
        response.sendRedirect("CatalogueServlet");
    }
}
