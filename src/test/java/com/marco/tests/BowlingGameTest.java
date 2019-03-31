package com.marco.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.marco.bownling.domain.Frame;
import com.marco.bownling.services.implementation.MarcoBowlingGame;
import com.marco.bownling.services.interfaces.BowlingGameInterface;
import com.marco.utils.MarcoException;

/**
 * Test del gioco per vedere se calcolo il punteggio nel modo giusto (o se ho
 * capito le regole :) )
 * 
 * 
 * @author marco
 *
 */
public class BowlingGameTest {

	private BowlingGameInterface game;

	@Before
	public void init() {
		game = new MarcoBowlingGame();
	}

	@Test
	public void testLancio() {
		try {
			Assert.assertTrue(game.lancio(5));
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}
	}

	@Test
	public void testPunteggioSemplice() {
		try {
			game.lancio(5);
			game.lancio(3);//5 + 3 = 8
			game.lancio(4);
			game.lancio(2);//8 + 4 + 2 = 14
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals(14, game.punteggioTotale());
	}

	@Test
	public void testPunteggioSpare() {
		try {
			game.lancio(8);
			game.lancio(1);//8 + 1 = 9
			game.lancio(1);
			game.lancio(9);//9 + 10 + 3 = 22
			game.lancio(3);//22 + 3 = 25
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}
		Assert.assertEquals(25, game.punteggioTotale());
	}

	@Test
	public void testPunteggioStrike() {
		try {
			game.lancio(5);//5
			game.lancio(4);//5 + 4 = 9
			game.lancio(10);//9 + 10 + 10 + 1 = 30
			game.lancio(10);//30 + 10 + 1 = 41
			game.lancio(1);//41 + 1 = 42
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}
		Assert.assertEquals(42, game.punteggioTotale());
	}

	@Test
	public void testPartitaPerfetta() {
		try {
			// 10 tiri semplici + 2 di bonus
			for(int i = 0; i < 12; i++) {
				game.lancio(10);
			}
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}
		Assert.assertEquals(300, game.punteggioTotale());
	}

	@Test
	public void testListaFrame() {
		try {
			game.lancio(5);// Frame 1
			game.lancio(4);// Frame 1 
			game.lancio(10);// Strike (Frame 2)
			game.lancio(10);// Strike (Frame 3)
			game.lancio(1);// Frame 4

			List<Frame> board = game.getSituazionePartita();
			Assert.assertEquals(1, board.get(3).getLancio1());
		} catch (MarcoException e) {
			Assert.fail(e.getMessage());
			return;
		}
	}

}
