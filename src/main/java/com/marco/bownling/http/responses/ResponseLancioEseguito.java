package com.marco.bownling.http.responses;

import com.marco.bownling.domain.UserGame;
import com.marco.bownling.http.BowlingResponse;

/**
 * Questa classe definisce in dettaglio la risposta 
 * di una richiesta AJAX.
 * Al momento tutte le richieste AJAX
 * usano la stessa "response"
 * 
 * @author marco
 *
 */
public class ResponseLancioEseguito extends BowlingResponse {
	private static final long serialVersionUID = -1148108614258217565L;

	private UserGame userGame;

	public UserGame getUserGame() {
		return userGame;
	}

	public void setUserGame(UserGame userGame) {
		this.userGame = userGame;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
