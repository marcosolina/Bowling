package com.marco.bownling.http.requests;

import java.io.Serializable;

/**
 * Questa classe rappresenta la richiesta AJAX 
 * per aggiunge un giocatore / partita
 * 
 * @author marco
 *
 */
public class AggiungiPartitaRequest implements Serializable {
	private static final long serialVersionUID = 5842108295462726468L;
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
