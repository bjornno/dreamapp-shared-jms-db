package ${groupId};
import org.apache.commons.dbcp.BasicDataSource;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.plus.naming.EnvEntry;
import org.mortbay.jetty.security.SslSocketConnector;

import javax.naming.NamingException;


public class ApplicationLauncher {

    // starts server with plain connection or ssl connection on port 8080, default is
    // SSL connection.
    // Run createTestKeys.sh if you want ssl.
    public static void main(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        basicDataSource.setUrl("jdbc:hsqldb:mem:rest");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");

        try {
            new EnvEntry("jdbc/Ds", basicDataSource);
        } catch (NamingException e) {
            System.exit(-1);
        }
        Server server = new Server();
        Connector defaultConnector = new SocketConnector();
        defaultConnector.setPort(8080);
        server.setConnectors(new Connector[] { defaultConnector });
        server.addHandler(
                new org.mortbay.jetty.webapp.WebAppContext("src/main/webapp", "/"));
        try {
            server.start();
        } catch (Exception e) {
            System.exit(-1);
        }

    }

}