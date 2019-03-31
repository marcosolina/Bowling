package com.marco.bownling.http.requests;

import java.io.Serializable;

/**
 * Questa classe rappresenta le request eseguite con
 * le varie chiamate AJAX (tranne la richiesta per aggiungere
 * una partita).
 * 
 * @author marco
 *
 */
public class EseguiLancioRequest implements Serializable {
	private static final long serialVersionUID = -7815044520183753468L;
	private String user;
	private int birilliAbbattuti;

	public int getBirilliAbbattuti() {
		return birilliAbbattuti;
	}

	public void setBirilliAbbattuti(int birilliAbbattuti) {
		this.birilliAbbattuti = birilliAbbattuti;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
