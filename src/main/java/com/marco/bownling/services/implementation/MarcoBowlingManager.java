package com.marco.bownling.services.implementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.marco.bownling.domain.UserGame;
import com.marco.bownling.services.interfaces.BowlingGameInterface;
import com.marco.bownling.services.interfaces.BownlingManagerInterface;
import com.marco.utils.MarcoException;

/**
 * La mia implementazione del manager delle parite
 * 
 * @author marco
 *
 */
public class MarcoBowlingManager implements BownlingManagerInterface {
	private Map<String, BowlingGameInterface> games = new HashMap<>();
	@Autowired
	private ApplicationContext ctx;

	@Override
	public UserGame addNewGame(String user) throws MarcoException {
		if (games.containsKey(user)) {
			throw new MarcoException("Nome gi&agrave; ustato");
		}
		
		BowlingGameInterface game = ctx.getBean(BowlingGameInterface.class);
		games.put(user, game);
		
		UserGame ug = new UserGame(user);
		ug.setFrames(game.getSituazionePartita());
		return ug;
	}

	@Override
	public UserGame eseguiLancio(String user, int birilliAbbattuti) throws MarcoException {
		if (!games.containsKey(user)) {
			throw new MarcoException("Partita non trovata");
		}
		BowlingGameInterface game = games.get(user);
		UserGame ug = new UserGame(user);
		game.lancio(birilliAbbattuti);
		ug.setFrames(game.getSituazionePartita());
		return ug;
	}

	@Override
	public void eliminaGame(String user) throws MarcoException {
		if (!games.containsKey(user)) {
			throw new MarcoException("Partita non trovata");
		}
		games.remove(user);
	}

}
