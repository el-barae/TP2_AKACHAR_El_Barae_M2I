package tp2.tp2_akachar_el_barae_m2i.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<Produit> liste_Produits;

    public Panier() {
        this.liste_Produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit p) {
        liste_Produits.add(p);
    }

    public double getTotal() {
        double total = 0;
        for (Produit produit : liste_Produits) {
            total += produit.getPrix();
        }
        return total;
    }

    public List<Produit> getListe_Produits() {
        return liste_Produits;
    }

    public void setListe_Produits(List<Produit> liste_Produits) {
        this.liste_Produits = liste_Produits;
    }
}
