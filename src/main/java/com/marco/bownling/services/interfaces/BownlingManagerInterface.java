package com.marco.bownling.services.interfaces;

import com.marco.bownling.domain.UserGame;
import com.marco.utils.MarcoException;

/**
 * Questa interfacca definisce le azioni che
 * il manager delle partite deve gestire
 * 
 * @author marco
 *
 */
public interface BownlingManagerInterface {
	/**
	 * Aggiunge l'utente e ritorna
	 * la situazione "blank"
	 * 
	 * @param user
	 * @return
	 * @throws MarcoException
	 */
	public UserGame addNewGame(String user) throws MarcoException;

	/**
	 * Applica le logice necessarie ad eseguire 
	 * il lacio della palla, e aggiorna i risultati
	 * 
	 * @param user
	 * @param birilliAbbattuti
	 * @return
	 * @throws MarcoException
	 */
	public UserGame eseguiLancio(String user, int birilliAbbattuti) throws MarcoException;
	
	/**
	 * Elimina la partita
	 * 
	 * @param user
	 * @throws MarcoException
	 */
	public void eliminaGame(String user) throws MarcoException;
}
