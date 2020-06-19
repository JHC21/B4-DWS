import view.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("logger");

    public static void main(String[] args) throws Exception {
        try {
            new UI();
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Failed to start watch");
        }
    }
}