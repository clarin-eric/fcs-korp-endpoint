package se.gu.spraakbanken.fcs.endpoint.korp.data.json.pojo.info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"version",
	"cqp_version",
	"corpora",
	"protected_corpora",
	"time"
})

public class ServiceInfo {
    
    @JsonProperty("corpora")
    private List<String> corpora = new ArrayList<String>();
    @JsonProperty("version")
    private String korpAPIVersion;
    @JsonProperty("cqp_version")
    private String cqpVersion;
    @JsonProperty("protected_corpora")
    private List<String> protectedCorpora = new ArrayList<String>();
    @JsonProperty("time")
    private Double time;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private static final List<String> MODERN_CORPORA_v6 = Collections.unmodifiableList(Arrays.asList("FSBBLOGGVUXNA","MAGMAKOLUMNER","INFORMATIONSTIDNINGAR","LAGTEXTER","MYNDIGHET","PROPOSITIONER","BARNLITTERATUR","FSBESSAISTIK","FSBSAKPROSA","FSBSKONLIT1960-1999","FSBSKONLIT2000TAL","UNGDOMSLITTERATUR","FNB1999","FNB2000","HBL1991","HBL1998","HBL1999","HBL20122013","HBL2014","JAKOBSTADSTIDNING1999","JAKOBSTADSTIDNING2000","PARGASKUNGORELSER2011","PARGASKUNGORELSER2012","SYDOSTERBOTTEN2010","SYDOSTERBOTTEN2011","SYDOSTERBOTTEN2012","SYDOSTERBOTTEN2013","SYDOSTERBOTTEN2014","VASABLADET1991","VASABLADET2012","VASABLADET2013","VASABLADET2014","ABOUNDERRATTELSER2012","ABOUNDERRATTELSER2013","OSTERBOTTENSTIDNING2011","OSTERBOTTENSTIDNING2012","OSTERBOTTENSTIDNING2013","OSTERBOTTENSTIDNING2014","BORGABLADET","VASTRANYLAND","AT2012","OSTRANYLAND","ASTRA1960-1979","ASTRANOVA","BULLEN","FANBARAREN","FINSKTIDSKRIFT","FORUMFEOT","HANKEITEN","HANKEN","JFT","KALLAN","MEDDELANDEN","NYAARGUS","STUDENTBLADET","SVENSKBYGDEN","ROMI","ROMII","ROM99","STORSUC","BLOGGMIX1998","BLOGGMIX1999","BLOGGMIX2000","BLOGGMIX2001","BLOGGMIX2002","BLOGGMIX2003","BLOGGMIX2004","BLOGGMIX2005","BLOGGMIX2006","BLOGGMIX2007","BLOGGMIX2008","BLOGGMIX2009","BLOGGMIX2010","BLOGGMIX2011","BLOGGMIX2012","BLOGGMIX2013","BLOGGMIX2014","BLOGGMIX2015","BLOGGMIXODAT","TWITTER","GP1994","GP2001","GP2002","GP2003","GP2004","GP2005","GP2006","GP2007","GP2008","GP2009","GP2010","GP2011","GP2012","GP2013","GP2D","PRESS65","PRESS76","PRESS95","PRESS96","PRESS97","PRESS98","WEBBNYHETER2001","WEBBNYHETER2002","WEBBNYHETER2003","WEBBNYHETER2004","WEBBNYHETER2005","WEBBNYHETER2006","WEBBNYHETER2007","WEBBNYHETER2008","WEBBNYHETER2009","WEBBNYHETER2010","WEBBNYHETER2011","WEBBNYHETER2012","WEBBNYHETER2013","ATTASIDOR","DN1987","ORDAT","FOF","SFS","SNP7879","SUC3","WIKIPEDIA-SV","TALBANKEN"));

// v8
private static final List<String> MODERN_CORPORA = Collections.unmodifiableList(Arrays.asList("ABOUNDERRATTELSER2012","ABOUNDERRATTELSER2013","ANSOKNINGAR","ASPACSV","ASTRA1960-1979","ASTRANOVA","ASU","AT2012","ATTASIDOR","BARNLITTERATUR","BLOGGMIX1998","BLOGGMIX1999","BLOGGMIX2000","BLOGGMIX2001","BLOGGMIX2002","BLOGGMIX2003","BLOGGMIX2004","BLOGGMIX2005","BLOGGMIX2006","BLOGGMIX2007","BLOGGMIX2008","BLOGGMIX2009","BLOGGMIX2010","BLOGGMIX2011","BLOGGMIX2012","BLOGGMIX2013","BLOGGMIX2014","BLOGGMIX2015","BLOGGMIX2016","BLOGGMIX2017","BLOGGMIXODAT","BORGABLADET","BULLEN","COCTAILL-AE","COCTAILL-LT","COCTAILL","DA","DIABETOLOG","DN1987","DRAMA","FAMILJELIV-ADOPTION","FAMILJELIV-ALLMANNA-EKONOMI","FAMILJELIV-ALLMANNA-FAMILJELIV","FAMILJELIV-ALLMANNA-FRITID","FAMILJELIV-ALLMANNA-HUSDJUR","FAMILJELIV-ALLMANNA-HUSHEM","FAMILJELIV-ALLMANNA-KROPP","FAMILJELIV-ALLMANNA-NOJE","FAMILJELIV-ALLMANNA-SAMHALLE","FAMILJELIV-ALLMANNA-SANDLADAN","FAMILJELIV-ANGLARUM","FAMILJELIV-EXPERT","FAMILJELIV-FORALDER","FAMILJELIV-GRAVID","FAMILJELIV-KANSLIGA","FAMILJELIV-MEDLEM-ALLMANNA","FAMILJELIV-MEDLEM-FORALDRAR","FAMILJELIV-MEDLEM-PLANERARBARN","FAMILJELIV-MEDLEM-VANTARBARN","FAMILJELIV-PAPPAGRUPP","FAMILJELIV-PLANERARBARN","FAMILJELIV-SEXSAMLEVNAD","FAMILJELIV-SVARTATTFABARN","FANBARAREN","FINSKTIDSKRIFT","FLASHBACK-DATOR","FLASHBACK-DROGER","FLASHBACK-EKONOMI","FLASHBACK-FLASHBACK","FLASHBACK-FORDON","FLASHBACK-HEM","FLASHBACK-KULTUR","FLASHBACK-LIVSSTIL","FLASHBACK-MAT","FLASHBACK-OVRIGT","FLASHBACK-POLITIK","FLASHBACK-RESOR","FLASHBACK-SAMHALLE","FLASHBACK-SEX","FLASHBACK-SPORT","FLASHBACK-VETENSKAP","FNB1999","FNB2000","FOF","FORHOR","FORUMFEOT","FRAGELISTOR","FSBBLOGGVUXNA","FSBESSAISTIK","FSBSAKPROSA","FSBSKONLIT1960-1999","FSBSKONLIT2000TAL","GDC","GP1994","GP2001","GP2002","GP2003","GP2004","GP2005","GP2006","GP2007","GP2008","GP2009","GP2010","GP2011","GP2012","GP2013","GP2D","HANKEITEN","HANKEN","HBL1991","HBL1998","HBL1999","HBL20122013","HBL2014","INFORMATIONSTIDNINGAR","IVIP-DEMO","IVIP","JAKOBSTADSTIDNING1999","JAKOBSTADSTIDNING2000","JFT","JUBILEUMSARKIVET-PILOT","KALLAN","KLARSPRAK","LASBART","LAGTEXTER","LAWLINE","SOU","LT1996","LT1997","LT1998","LT1999","LT2000","LT2001","LT2002","LT2003","LT2004","LT2005","LT2006","MAGMAKOLUMNER","MEDDELANDEN","MEPAC-I","MEPAC","MYNDIGHET","NYAARGUS","ORDAT","OSTERBOTTENSTIDNING2011","OSTERBOTTENSTIDNING2012","OSTERBOTTENSTIDNING2013","OSTERBOTTENSTIDNING2014","OSTRANYLAND","PARGASKUNGORELSER2011","PARGASKUNGORELSER2012","PAROLE","PLATSANNONSER","PODIET","POETER","PRESS65","PRESS76","PRESS95","PRESS96","PRESS97","PRESS98","PROPOSITIONER","PSALMBOKEN","RD-BET","RD-DS","RD-EUN","RD-FLISTA","RD-FPM","RD-FRSRDG","RD-IP","RD-KAMMAKT","RD-KOM","RD-MOT","RD-OVR","RD-PROP","RD-PROT","RD-RSKR","RD-SAMTR","RD-SKFR","RD-SOU","RD-TLISTA","RD-UTR","RD-UTSK","RD-YTTR","ROM99","ROMG","ROMI","ROMII","SALTNLD-SV","SFS","SIC2","SMITTSKYDD","SNP7879","SOEXEMPEL","SUC3","SPIN-SOURCE","SPINV1","SPRAKFRAGOR","STORSUC","STRINDBERGBREV","STRINDBERGROMANER","STUDENTBLADET","SUC2","VIVILL","SV-COVID-19","SVENSKBYGDEN","SVT-2004","SVT-2005","SVT-2006","SVT-2007","SVT-2008","SVT-2009","SVT-2010","SVT-2011","SVT-2012","SVT-2013","SVT-2014","SVT-2015","SVT-2016","SVT-2017","SVT-2018","SVT-2019","SVT-2020","SVT-2021","SVT-2022","SVT-2023","SVT-NODATE","SW1203","SW1203V1","SW1203V2","SWEACHUM","SWEACSAM","SWEFN-EX","SWELL-ORIGINAL","SWELL-TARGET","SWELLV1-ORIGINAL","SWELLV1-TARGET","SYDOSTERBOTTEN2012","SYDOSTERBOTTEN2013","SYDOSTERBOTTEN2014","TKR-BET-MEM-UTL","TALBANKEN","TISUS","TWITTER-2015","TISUSV1","TWITTER-2016","TWITTER-2017","TWITTER-PLDEBATT-130612","TWITTER-PLDEBATT-131006","TWITTER-PLDEBATT-140504","TWITTER","UNGDOMSLITTERATUR","VASABLADET1991","VASABLADET2012","VASABLADET2013","VASABLADET2014","TKR-MOTIONER","VASTRANYLAND","TKR-PROTOKOLL","TKR-REGISTER","WEBBNYHETER2001","WEBBNYHETER2002","WEBBNYHETER2003","WEBBNYHETER2004","WEBBNYHETER2005","WEBBNYHETER2006","WEBBNYHETER2007","WEBBNYHETER2008","WEBBNYHETER2009","WEBBNYHETER2010","WEBBNYHETER2011","WEBBNYHETER2012","WEBBNYHETER2013","WIKIPEDIA-SV","TISUSV2","TKR-RSKR","SYDOSTERBOTTEN2011","SYDOSTERBOTTEN2010","FOLKE-PILOT","SAOB-BOCKER","TKR-BERATTELSER-REDOGORELSER-FRSRDG","TKR-PROPOSITIONER-SKRIVELSER","TKR-REGLEMENTEN-SFS","TKR-RIKSDAGENS-FORFATTNINGSSAMLING-RFS","TKR-UTREDNINGAR-KOMBET-SOU"));

private static final List<String> MODERN_PROTECTED_CORPORA = Collections.unmodifiableList(Arrays.asList("ANSOKNINGAR","ASU","DREAM-DE-RESTRICTED","DREAM-EN-RESTRICTED","DREAM-ES-RESTRICTED","DREAM-FR-RESTRICTED","DREAM-IT-RESTRICTED","DREAM-NL-RESTRICTED","DREAM-RU-RESTRICTED","DYLAN","ESPC-EN","ESPC-SV","FORHOR","GDC","IVIP","LAWFN","LAWLINE","MEPAC","MEPAC-I","PLATSANNONSER","SOEXEMPEL","SPIN-SOURCE","SPIN-TARGET","SPINV1","SPRAKFRAGOR","SW1203","SW1203V1","SW1203V2","SWELL-ORIGINAL","SWELL-TARGET","SWELLV1-ORIGINAL","SWELLV1-TARGET","TISUS","TISUSV1","TISUSV2"));

    /**
     *
     * @return
     * The corpora
     */
    @JsonProperty("corpora")
    public List<String> getCorpora() {
	return corpora;
    }

    /**
     *
     * @param corpora
     * The corpora
     */
    @JsonProperty("corpora")
    public void setCorpora(List<String> corpora) {
	this.corpora = corpora;
    }

    /**
     *
     * @return
     * The korpAPIVersion
     */
    @JsonProperty("version")
    public String getKorpAPIVersion() {
	return korpAPIVersion;
    }

    /**
     *
     * @param korpAPIVersion
     * The korpAPIversion
     */
    @JsonProperty("version")
    public void setKorpAPIVersion(String korpAPIVersion) {
	this.korpAPIVersion = korpAPIVersion;
    }

    /**
     *
     * @return
     * The cqpVersion
     */
    @JsonProperty("cqp_version")
    public String getCqpVersion() {
	return cqpVersion;
    }

    /**
     *
     * @param cqpVersion
     * The cqp-version
     */
    @JsonProperty("cqp_version")
    public void setCqpVersion(String cqpVersion) {
	this.cqpVersion = cqpVersion;
    }

    /**
     *
     * @return
     * The protectedCorpora
     */
    @JsonProperty("protected_corpora")
    public List<String> getProtectedCorpora() {
	return protectedCorpora;
    }

    /**
     *
     * @param protectedCorpora
     * The protected_corpora
     */
    @JsonProperty("protected_corpora")
    public void setProtectedCorpora(List<String> protectedCorpora) {
	this.protectedCorpora = protectedCorpora;
    }

    /**
     *
     * @return
     * The time
     */
    @JsonProperty("time")
    public Double getTime() {
	return time;
    }

    /**
     *
     * @param time
     * The time
     */
    @JsonProperty("time")
    public void setTime(Double time) {
	this.time = time;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
    }

    public static List<String> getOpenCorporaLive() {
        ObjectMapper mapper = new ObjectMapper();

	ServiceInfo si = null;
	final String wsStringv6 = "https://ws.spraakbanken.gu.se/ws/korp/v6/?";
	final String queryStringv6 = "command=info";
	final String wsString = "https://ws.spraakbanken.gu.se/ws/korp/v8/info";
	final String queryString = "";

        try {
	    URL korp = new URL(wsString + queryString);

            si = mapper.reader(ServiceInfo.class).readValue(korp.openStream());
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> openCorpora = new ArrayList<String>();
	boolean isPC = false;
	for (String corpus : si.getCorpora()) {
	    for (String pCorpus : si.getProtectedCorpora()) {
	        if (corpus.equals(pCorpus)) {
		    isPC = true;
		}
	    }
	    if (!isPC) {
		openCorpora.add(corpus);
	    }
	    isPC = false;
	}
	return openCorpora;
    }

    public static List<String> getOpenCorporaNonLive() {
        List<String> openCorpora = new ArrayList<String>();
        boolean isPC = false;
        for (String corpus : MODERN_CORPORA) {
            for (String pCorpus : MODERN_PROTECTED_CORPORA) {
                if (corpus.equals(pCorpus)) {
                    isPC = true;
                }
            }
            if (!isPC) {
                openCorpora.add(corpus);
            }
            isPC = false;
        }
        return openCorpora;
    }

    public static List<String> getModernCorpora() {
        List<String> modernCorpora = new ArrayList<String>();
        List<String> openCorpora = ServiceInfo.getOpenCorporaLive();
        for (String corpus : openCorpora) {
            if (MODERN_CORPORA.contains(corpus)) {
                modernCorpora.add(corpus);
            }
        }
        return modernCorpora;
    }

}
