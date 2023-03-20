package se.gu.spraakbanken.fcs.endpoint.korp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import eu.clarin.sru.server.SRUConfigException;
import eu.clarin.sru.server.SRUException;
import eu.clarin.sru.server.fcs.EndpointDescription;
import eu.clarin.sru.server.fcs.ResourceInfo;
import eu.clarin.sru.server.fcs.utils.SimpleEndpointDescriptionParser;

public class EndpointDescriptionTest {
    private final static String ENDPOINT_DESCRIPTION_PATH = "target/test-classes/se/gu/spraakbanken/fcs/endpoint/korp/korp-endpoint-description-test.xml";

    private static EndpointDescription sed;

    @BeforeClass
    public static void parseEndpointDescription() throws SRUConfigException {
        try {
            sed = SimpleEndpointDescriptionParser.parse(new File(ENDPOINT_DESCRIPTION_PATH).toURI().toURL());
            assertEquals("http://clarin.eu/fcs/capability/basic-search", sed.getCapabilities().get(0).toString());
            assertEquals("http://clarin.eu/fcs/capability/advanced-search", sed.getCapabilities().get(1).toString());
        } catch (MalformedURLException mue) {
            throw new SRUConfigException("Malformed URL");
        }
    }

    @Test
    public void getCapabilitiesFromDescription() throws SRUConfigException {
        assertEquals("http://clarin.eu/fcs/capability/basic-search", sed.getCapabilities().get(0).toString());
        assertEquals("http://clarin.eu/fcs/capability/advanced-search", sed.getCapabilities().get(1).toString());
    }

    @Test
    public void getDataViewsFromDescription() throws SRUConfigException {
        System.out.println("sed.getSupportedDataViews(): " + sed.getSupportedDataViews());
        assertEquals("hits", sed.getSupportedDataViews().get(0).getIdentifier());
        assertEquals("SEND_BY_DEFAULT", sed.getSupportedDataViews().get(0).getDeliveryPolicy().toString());
        assertEquals("application/x-clarin-fcs-adv+xml", sed.getSupportedDataViews().get(1).getMimeType());
        assertEquals("application/x-cmdi+xml", sed.getSupportedDataViews().get(2).getMimeType());
        assertEquals("NEED_TO_REQUEST", sed.getSupportedDataViews().get(2).getDeliveryPolicy().toString());
    }

    @Test
    public void getLayersFromDescription() throws SRUConfigException {
        System.out.println("sed.getSupportedLayers(): " + sed.getSupportedLayers());
        assertEquals("http://spraakbanken.gu.se/ns/fcs/layer/word",
                sed.getSupportedLayers().get(0).getResultId().toString());
        assertEquals("lemma", sed.getSupportedLayers().get(1).getType().toString());
    }

    @Test
    public void getResourcesFromDescription() throws SRUException {
        List<ResourceInfo> riList = sed.getResourceList("hdl:10794/sbmoderna");
        System.out.println("Resource(0).Title: " + riList.get(0).getTitle());
        assertEquals("hits", riList.get(0).getAvailableDataViews().get(0).getIdentifier());
        assertEquals("SEND_BY_DEFAULT", riList.get(0).getAvailableDataViews().get(0).getDeliveryPolicy().toString());
        assertEquals("application/x-clarin-fcs-hits+xml", riList.get(0).getAvailableDataViews().get(0).getMimeType());
        assertEquals("https://spraakbanken.gu.se/resurser/suc", riList.get(0).getLandingPageURI());
        assertTrue(riList.get(0).hasAvailableLayers());
        assertEquals("word", riList.get(0).getAvailableLayers().get(0).getId());
        assertEquals("text", riList.get(0).getAvailableLayers().get(0).getType());
        assertNull(riList.get(0).getAvailableLayers().get(0).getQualifier());
        assertEquals("swe", riList.get(0).getLanguages().get(0));
        assertFalse(riList.get(0).hasSubResources());
    }
}
