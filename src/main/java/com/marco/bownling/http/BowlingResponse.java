package com.marco.bownling.http;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.marco.utils.MarcoException;

/**
 * Questa classe rappresenta il minimo delle informazioni
 * che il front end si aspetta quando esegue delle richieste
 * AJAX
 * 
 * @author marco
 *
 */
public abstract class BowlingResponse implements Serializable {
	private static final long serialVersionUID = -6080322153003410693L;
	private boolean status;
	private List<MarcoException> errors;

	public boolean addError(MarcoException e) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		return this.errors.add(e);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<MarcoException> getErrors() {
		return errors;
	}

	public void setErrors(List<MarcoException> errors) {
		this.errors = errors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
