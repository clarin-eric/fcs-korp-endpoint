package se.gu.spraakbanken.fcs.endpoint.korp;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import eu.clarin.sru.server.CQLQueryParser;
import eu.clarin.sru.server.SRUDiagnosticList;
import eu.clarin.sru.server.SRUException;
import eu.clarin.sru.server.SRUVersion;
import eu.clarin.sru.server.fcs.FCSQueryParser;
import se.gu.spraakbanken.fcs.endpoint.korp.cqp.FCSToCQPConverter;

public class FCSToCQPConverterTest {
    @Test
    public void convertCQL() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        // params.put("query", "text = 'användning' AND text = 'begränsad'");
        final String query = "text = 'användningen är begränsad'";
        final String res = "[word = 'användningen'][word = 'är'][word = 'begränsad']";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromCQL((new CQLQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSSimple() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning' & pos = 'NOUN']";
        final String res = "[word = 'användning' & pos = 'NN']";
        params.put("query", query);
        // params.put("query", "[text = 'användning']");
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSLemma() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[lemma = 'Kabul' & pos = 'PROPN']";
        final String res = "[lemma contains 'Kabul' & pos = 'PM']";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSNot() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning' & pos != 'NOUN']";
        final String res = "[word = 'användning' & pos != 'NN']";
        params.put("query", query);
        // params.put("query", "[text = 'användning']");
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSWildcard() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning' & pos = 'NOUN'] [] [pos = 'ADJ'] ";
        final String res = "[word = 'användning' & pos = 'NN'] [] [pos = '(JJ|PC|RO)'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSRegexCaseInsensitive() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning'/c & pos = 'NOUN'] ";
        final String res = "[word = 'användning' %c & pos = 'NN'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res.trim(), resActual.trim());
    }

    @Test
    public void convertFCSRegexIgnoreDiacritics() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning'/d & pos = 'NOUN'] ";
        final String res = "[word = 'användning' %d & pos = 'NN'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res.trim(), resActual.trim());
    }

    @Test
    @Ignore
    public void convertFCSRegexLiteral() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = '?'/l & pos = 'PUNCT'] ";
        final String res = "[word = '?' %l & pos = '(MAD|MID|PAD)'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        // This fails right now since you get d too!
        assertEquals(res.trim(), resActual.trim());
    }

    @Test
    public void convertFCSOccurs() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning' & pos = 'NOUN'] []{1,3} [pos = 'ADJ'] ";
        final String res = "[word = 'användning' & pos = 'NN'] []{1,3} [pos = '(JJ|PC|RO)'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    @Test
    public void convertFCSOccursExact() throws SRUException {
        Map<String, String> params = new HashMap<String, String>();
        final String query = "[word = 'användning' & pos = 'NOUN'] []{3} [pos = 'ADJ'] ";
        final String res = "[word = 'användning' & pos = 'NN'] []{3} [pos = '(JJ|PC|RO)'] ";
        params.put("query", query);
        SRUDiagnosticList diagnostics = new Diagnostic();
        final String resActual = FCSToCQPConverter
                .makeCQPFromFCS((new FCSQueryParser()).parseQuery(SRUVersion.VERSION_2_0, params, diagnostics));

        System.out.println(resActual);
        assertEquals(res, resActual);
    }

    public class Diagnostic implements SRUDiagnosticList {
        @Override
        public void addDiagnostic(String uri, String details, String message) {
        }
    }
}
