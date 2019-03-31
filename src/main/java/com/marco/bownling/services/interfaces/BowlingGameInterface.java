package com.marco.bownling.services.interfaces;

import java.util.List;

import com.marco.bownling.domain.Frame;
import com.marco.utils.MarcoException;

/**
 * Interfaccia condivisa.
 * Al front end non interessa l'implementazione ma solo
 * il risultato.
 * L'implementazione la possiamo fare qui, o da un'altra parte (web service)
 * 
 * @author marco
 *
 */
public interface BowlingGameInterface {

	/**
	 * Esegui il lancio e segna i birilli
	 * che sono stati abbattuti
	 * 
	 * @param birilliAbbattuti
	 * @return
	 */
	public boolean lancio(int birilliAbbattuti) throws MarcoException ;
	
	
	/**
	 * Calcola il punteggio totale
	 * 
	 * @return
	 */
	public int punteggioTotale();
	
	/**
	 * Ritorna un elenco con i punteggi 
	 * per ogni frame
	 * 
	 * @return
	 */
	public List<Frame> getSituazionePartita();
}
