package planningPoker;
/**
 * JRE Planning Poker Rev 1.0
 * @author Jason Codding
 * @author Eric Greer
 * @author Matt Runkle
 */

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
/**
 * The PMF class is for persisting data objects to the data store
 *
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
