package Project;
import Project.Configurations.*;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Server {

    Configuration configuration = new Configuration();
    public Server(){
        configuration.loadConfiguration();
    }

}
