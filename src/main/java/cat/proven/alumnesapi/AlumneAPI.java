package cat.proven.alumnesapi;
import cat.proven.alumnesapi.Alumne;
import io.javalin.Javalin;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlumneAPI {
 
    private static final Logger LOG = LoggerFactory.getLogger(AlumneAPI.class); 
 
    public static void main(String[] args) {
        // Crear la instància del DAO
        AlumneDAO alumneDAO = new AlumneDAOImpl();

        // Iniciar Javalin
        Javalin app = Javalin.create().start(7007);
 
        // Definir les rutes
        app.get("/alumnes", ctx -> {
            LOG.info("Entry route get /alumnes");
            List<Alumne> alumnes = alumneDAO.getAll();
            ctx.json(alumnes);
        });
 
        app.get("/alumnes/{id}", ctx -> {
            LOG.info("Entry route -> get /alumnes/{id]}");
            int id = Integer.parseInt(ctx.pathParam("id"));
            LOG.info("id value ",ctx.pathParam("id"));
            Alumne alumne = alumneDAO.getById(id);
            if (alumne != null) {
                ctx.json(alumne);
            } else {
                ctx.status(404).result("Alumne no trobat");
            }
        });
 
        app.post("/alumnes", ctx -> {
            LOG.info("Entry route -> post /alumnes");
            Alumne nouAlumne = ctx.bodyAsClass(Alumne.class);
            alumneDAO.save(nouAlumne);
            ctx.status(201).result("Alumne creat");
        });
 
        app.delete("/alumnes/{id}", ctx -> {
            LOG.info("Entry route -> delete /alumnes/{id}");
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (alumneDAO.delete(id)) {
                ctx.status(200).result("Alumne eliminat");
            } else {
                ctx.status(404).result("Alumne no trobat");
            }
        });
    }
}