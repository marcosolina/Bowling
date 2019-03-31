package com.marco.bownling.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marco.bownling.http.requests.AggiungiPartitaRequest;
import com.marco.bownling.http.requests.EseguiLancioRequest;
import com.marco.bownling.http.responses.ResponseLancioEseguito;
import com.marco.bownling.services.interfaces.BownlingManagerInterface;
import com.marco.bownling.utils.BowlingWebAppConstants;
import com.marco.utils.MarcoException;

/**
 * Questo è il controller usato da Spring
 * per gestire le varie richieste HTTP
 * 
 * @author msolina
 *
 */
@Controller
public class BowlingController {
	@Autowired
	private BownlingManagerInterface manager;
	final static Logger logger = Logger.getLogger(BowlingController.class);

	/**
	 * It presents the landing page
	 * 
	 * @return The file to render into the browser
	 */
	@RequestMapping("/")
	public String indexPage() {
		/*
		 * Example of logging
		 */
		logger.debug("Inside indexPage method");
		/*
		 * This file in in the current project under the WEB-INF/view folder
		 */
		return "view/index.html";
	}

	@RequestMapping(value = BowlingWebAppConstants.URL_ESEGUI_LANCIO)
	@ResponseBody
	public ResponseLancioEseguito eseguiLancio(@RequestBody EseguiLancioRequest request) {
		ResponseLancioEseguito resp = new ResponseLancioEseguito();
		try {
			resp.setUserGame(manager.eseguiLancio(request.getUser(), request.getBirilliAbbattuti()));
			resp.setStatus(true);
		} catch (MarcoException e) {
			resp.addError(e);
		}
		return resp;
	}

	@RequestMapping(value = BowlingWebAppConstants.URL_RIMUOVI_PARTITA)
	@ResponseBody
	public ResponseLancioEseguito rimuoviPartita(@RequestBody AggiungiPartitaRequest request) {
		ResponseLancioEseguito resp = new ResponseLancioEseguito();
		try {
			manager.eliminaGame(request.getUser());
			resp.setStatus(true);
		} catch (MarcoException e) {
			resp.addError(e);
		}
		return resp;
	}

	@RequestMapping(value = BowlingWebAppConstants.URL_AGGIUNGI_PARTITA)
	@ResponseBody
	public ResponseLancioEseguito aggiungiPartita(@RequestBody AggiungiPartitaRequest request) {
		ResponseLancioEseguito resp = new ResponseLancioEseguito();
		try {
			resp.setUserGame(manager.addNewGame(request.getUser()));
			resp.setStatus(true);
		} catch (MarcoException e) {
			resp.addError(e);
		}
		return resp;
	}

}
