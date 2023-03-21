package se.gu.spraakbanken.fcs.endpoint.korp;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;

import eu.clarin.sru.server.SRUConfigException;
import eu.clarin.sru.server.SRUDiagnosticList;
import eu.clarin.sru.server.SRUException;
import eu.clarin.sru.server.SRUQueryParserRegistry;
import eu.clarin.sru.server.SRUServerConfig;
import eu.clarin.sru.server.utils.SRUServerServlet;
import eu.clarin.sru.server.fcs.FCSQueryParser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.testing.ServletTester;

import se.gu.spraakbanken.fcs.endpoint.korp.data.json.pojo.info.*;
import se.gu.spraakbanken.fcs.endpoint.korp.data.json.pojo.query.Query;

public class KorpEndpointSearchEngineTest {
    private final static String SRU_SERVER_CONFIG_PATH = "src/main/webapp/WEB-INF/sru-server-config.xml";

    private static Map<String, String> params = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put(SRUServerConfig.SRU_TRANSPORT, "http");
            put(SRUServerConfig.SRU_HOST, "127.0.0.1");
            put(SRUServerConfig.SRU_PORT, "8082");
            put(SRUServerConfig.SRU_DATABASE, "sru-server");
            put(SRUServerServlet.SRU_SERVER_CONFIG_LOCATION_PARAM, SRU_SERVER_CONFIG_PATH);
        }
    });
    private static SRUServerConfig config;

    private static ServletTester tester;
    private static ServletHolder holder;

    @BeforeClass
    public static void setup() throws SRUConfigException, ServletException {
        tester = new ServletTester();
        // tester.setContextPath("/");
        tester.setContextPath("http://localhost:8082/sru-server");
        tester.setResourceBase("src/main/webapp");
        tester.setClassLoader(SRUServerServlet.class.getClassLoader());
        holder = tester.addServlet(SRUServerServlet.class, "/sru");

        // TODO: needs to be after `tester.start()`?
        URL url;
        try {
            // SRUServerServlet.class.getClassLoader().getResource("META-INF/sru-server-config.xml");
            url = new File(SRU_SERVER_CONFIG_PATH).toURI().toURL();
        } catch (MalformedURLException mue) {
            throw new SRUConfigException("Malformed URL");
        }
        if (url == null) {
            throw new ServletException("not found, url == null");
        }
        // other runtime configuration, usually obtained from Servlet context
        config = SRUServerConfig.parse(params, url);
        System.out.println("config.getBaseUrl(): " + config.getBaseUrl());
        System.out.println("config.getDatabase(): " + config.getDatabase());

        // try {
        // String baseUrl = tester.createSocketConnector(true);
        // } catch (ServletException e) {
        // throw new SRUConfigException("Failed to set context attribute.");
        // } catch (Exception e) {
        // throw new SRUConfigException("Failed to create socket connector.");
        // }
        try {
            // tester.setAttribute(SRUServerServlet.SRU_SERVER_CONFIG_LOCATION_PARAM,
            // SRU_SERVER_CONFIG_PATH);
            System.out.println(
                    "tester.getAttribute(): " + tester.getAttribute(SRUServerServlet.SRU_SERVER_CONFIG_LOCATION_PARAM));
            // System.out.println(holder.getServlet().getServletConfig().getInitParameter(SRUServerServlet.SRU_SERVER_CONFIG_LOCATION_PARAM));
            tester.start();
        } catch (Exception e) {
            throw new SRUConfigException("Failed to start servlet.");
        }
    }

    @Test
    public void doInit() throws SRUConfigException, ServletException {
        KorpEndpointSearchEngine kese = new KorpEndpointSearchEngine();
        kese.doInit(config, new SRUQueryParserRegistry.Builder().register(new FCSQueryParser()), params);

        assertNotNull(kese.getCorporaInfo());
        assertNotNull(kese.getCorporaInfo().getTime());
        // assertNotNull(kese.getCorporaInfo().getCorpus("PAROLE"));
    }

    @Test
    public void search1() throws SRUException, SRUConfigException, XMLStreamException {
        KorpEndpointSearchEngine kese = new KorpEndpointSearchEngine();
        SRUDiagnosticList diagnostics = new Diagnostic();
        kese.doInit(config, new SRUQueryParserRegistry.Builder().register(new FCSQueryParser()), params);
        // SRURequest request = new SRURequestImpl(config, queryParsers, new
        // HttpServletRequestWrapper());
        // SRUSearchResultSet ssrs = kese.search(config, request, diagnostics);
        CorporaInfo openCorporaInfo = kese.getCorporaInfo();
        final String query = "[word = 'och'][pos = 'NOUN']";
        final String cqpQuery = "[word = 'och'][pos = 'NN']";

        Query queryRes = kese.makeQuery(cqpQuery, openCorporaInfo, 0, 25);
        KorpSRUSearchResultSet kssrs = new KorpSRUSearchResultSet(config, diagnostics, queryRes, query,
                openCorporaInfo);
        StringWriter sw = new StringWriter();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
        try {
            System.out.println("getCurrentRecordCursor 0: " + kssrs.getCurrentRecordCursor());
            if (kssrs.nextRecord()) {
                kssrs.writeRecord(xmlStreamWriter);
                System.out.println("search1: " + sw.toString());
            }
            xmlStreamWriter.flush();
        } finally {
            try {
                xmlStreamWriter.close();
            } catch (Exception ex2) {
            }
        }

        System.out.println("getHits: " + queryRes.getHits());
        System.out.println("getTotalRecordCount: " + kssrs.getTotalRecordCount());
        System.out.println("getRecordCount: " + kssrs.getRecordCount());
        System.out.println("getCurrentRecordCursor 1: " + kssrs.getCurrentRecordCursor());
        assertNotNull(sw.toString());
        // assertEquals(res, resActual);
    }

    @AfterClass
    public static void cleanupServletContainer() throws Exception {
        tester.stop();
    }

    public class Diagnostic implements SRUDiagnosticList {
        @Override
        public void addDiagnostic(String uri, String details, String message) {
        }
    }

    public static void main(String[] args) {
    }
}
