package Ws;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Config extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(PasajerosAPI.class);
        classes.add(VuelosAPI.class);
        classes.add(AvionesAPI.class);
        classes.add(ReservasAPI.class);
        return classes;
    }
}