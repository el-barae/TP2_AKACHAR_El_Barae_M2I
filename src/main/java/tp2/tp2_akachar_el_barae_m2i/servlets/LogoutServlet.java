package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String log = "logout_logs.txt";
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);
        String confirm = req.getParameter("confirm");

        if (confirm != null) {
            if (confirm.equals("yes")) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                logDeconnexion(utilisateur.getLogin());
                session.invalidate();
                res.sendRedirect("login.html");
                return;
            } else if (confirm.equals("no")) {
                res.sendRedirect("PanierServlet");
                return;
            }
        }
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");

        try (PrintWriter out = res.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Confirmation de déconnexion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Etes-vous sur de vouloir vous deconnecter?</h2>");
            out.println("<p>Vous allez etre deconnecte de votre session.</p>");
            out.println("<form action='LogoutServlet' method='GET' style='display:inline;'>");
            out.println("<input type='hidden' name='confirm' value='yes' />");
            out.println("<button type='submit'>Oui, je me deconnecte</button>");
            out.println("</form>");
            out.println("<a href='LogoutServlet?confirm=no' >Annuler</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void logDeconnexion(String login) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(new Date());
            String logMessage = "Deconnexion de l'utilisateur " + login + " a " + timestamp + "\n";
            String logPath = getServletContext().getRealPath("/") + log;
            Files.write(
                    Paths.get(logPath),
                    logMessage.getBytes(),
                    Files.exists(Paths.get(logPath)) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
