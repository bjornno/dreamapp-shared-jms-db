package ${groupId}.integration;

import org.apache.camel.Exchange;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;


public class RouteTest extends CamelSpringTestSupport {

    public void setUp() throws Exception {
       
        deleteDirectory("data");
        deleteDirectory("logs");
        super.setUp();
    }

    /*
    public void tearDown() throws Exception {
        deleteDirectory("data");
        deleteDirectory("logs");
        super.tearDown();
    }
    */

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/applicationContext.xml", "testContext.xml"});

    }

    @Test
    public void shouldMoveOkFileToDone() throws Exception {
        template.sendBodyAndHeader("file://data/in/",getOkFile(), Exchange.FILE_NAME, "okfile.txt");
        Thread.sleep(2000);
        File target = new File("data/out/");
        assertTrue("file not moved to done", target.exists());
    }

    String getOkFile() {
        return "STARTRECORD\nSLUTTRECORD";
    }
}
