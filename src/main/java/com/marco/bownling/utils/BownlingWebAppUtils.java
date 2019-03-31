package com.marco.bownling.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.marco.utils.MarcoException;
import com.marco.utils.miscellaneous.MarcoUtils;

/**
 * This is a Utils class that provides
 * "App specific" utils methods
 * 
 * @author msolina
 *
 */
public class BownlingWebAppUtils {
    final static Logger logger = Logger.getLogger(BownlingWebAppUtils.class);
    
    /**
     * It returns a map of String.
     * This method is called by Thymeleaf in order to 
     * create the javascript __URLS Object
     * 
     * @return A map that contains the URL for the different controller
     */
    public static Map<String, Map<String, String>> getJsUrlConstants() {
        Map<String, Map<String, String>> map = new HashMap<>();
        String appName;
        try {
            appName = "/" + MarcoUtils.getProperty(BowlingWebAppConstants.PRP_APP_NAME);
        } catch (MarcoException e) {
            e.printStackTrace();
            return map;
        }

        Map<String, String> generic = new HashMap<>();
        generic.put("ESEGUI_LANCIO", appName + BowlingWebAppConstants.URL_ESEGUI_LANCIO);
        generic.put("AGGIUNGI_PARTITA", appName + BowlingWebAppConstants.URL_AGGIUNGI_PARTITA);
        generic.put("RIMUOVI_PARTITA", appName + BowlingWebAppConstants.URL_RIMUOVI_PARTITA);
        map.put("BOWLING", generic);

        return map;
    }
}
