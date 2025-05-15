package tp2.tp2_akachar_el_barae_m2i.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static int activeUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeUsers++;
        System.out.println("Session cree - ID: " + se.getSession().getId());
        System.out.println("Nombre d'utilisateurs actifs: " + activeUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        if (utilisateur != null) {
            System.out.println("Session detruite pour l'utilisateur: " + utilisateur.getLogin());
        } else {
            System.out.println("Session detruite (utilisateur inconnu) - ID: " + session.getId());
        }
        activeUsers--;
        System.out.println("Nombre d'utilisateurs actifs: " + activeUsers);
    }

    public static int getActiveUsers() {
        return activeUsers;
    }
}
