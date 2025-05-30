package tp2.tp2_akachar_el_barae_m2i.listeners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContexteListener implements ServletContextListener {
    private long startTime;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        startTime = System.currentTimeMillis();
        ServletContext c = sce.getServletContext();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTimeFormatted = sdf.format(new Date(startTime));
        System.out.println("Application demarree a " + startTimeFormatted);
        System.out.println("Chemin de l'application: " + c.getRealPath("/"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        long sec = duration / 1000;
        long min = sec / 60;
        long h = min / 60;
        long d = h / 24;
        sec %= 60;
        min %= 60;
        h %= 24;
        String durationFormatted = String.format("%d jours, %d heures, %d minutes, %d secondes",
                d, h, min, sec);
        ServletContext c = sce.getServletContext();
        try {
            String filePath = c.getRealPath("/") + "running_time.txt";
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(filePath);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTimeFormatted = sdf.format(new Date(startTime));
            String endTimeFormatted = sdf.format(new Date(endTime));
            writer.write("Heure de demarrage: " + startTimeFormatted + "\n");
            writer.write("Heure d'arret: " + endTimeFormatted + "\n");
            writer.write("Duree de fonctionnement: " + durationFormatted + "\n");
            writer.close();

            System.out.println("Duree de fonctionnement enregistree dans: " + filePath);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'ecriture du fichier de duree: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Application arretee apres " + durationFormatted);
    }
}
