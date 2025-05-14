package tp2.tp2_akachar_el_barae_m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp2.tp2_akachar_el_barae_m2i.model.Utilisateur;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String LOG_FILE = "logout_logs.txt";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String confirm = request.getParameter("confirm");

        if (confirm != null) {
            if (confirm.equals("yes")) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                logDeconnexion(utilisateur.getLogin());
                session.invalidate();
                response.sendRedirect("login.html");
                return;
            } else if (confirm.equals("no")) {
                response.sendRedirect("PanierServlet");
                return;
            }
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Confirmation de déconnexion</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f5f5f5; }");
            out.println(".container { text-align: center; background-color: white; padding: 30px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 400px; }");
            out.println("h2 { margin-top: 0; color: #333; }");
            out.println("p { margin-bottom: 20px; color: #666; }");
            out.println(".buttons { display: flex; justify-content: center; gap: 20px; }");
            out.println(".btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; color: white; display: inline-block; }");
            out.println(".btn-confirm { background-color: #f44336; }");
            out.println(".btn-cancel { background-color: #4CAF50; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Confirmation de déconnexion</h2>");
            out.println("<p>Êtes-vous sûr de vouloir vous déconnecter?</p>");
            out.println("<div class='buttons'>");
            out.println("<a href='LogoutServlet?confirm=yes' class='btn btn-confirm'>Se déconnecter</a>");
            out.println("<a href='LogoutServlet?confirm=no' class='btn btn-cancel'>Annuler</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void logDeconnexion(String login) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(new Date());
            String logMessage = "Déconnexion de l'utilisateur " + login + " à " + timestamp + "\n";
            String logPath = getServletContext().getRealPath("/") + LOG_FILE;
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
