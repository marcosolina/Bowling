package com.marco.bownling.domain;

import java.io.Serializable;

/**
 * Questa classe rappresenta il singolo Frame dei punteggi
 * 
 * @author marco
 *
 */
public class Frame implements Serializable {
	private static final long serialVersionUID = -5637132757921364423L;
	private int lancio1 = 0;
	private int lancio2 = 0;
	private int lancioBonus = 0;
	private int punteggioAQuestoFrame = -1;
	private boolean spare = false;
	private boolean strike = false;

	public Frame() {

	}

	public Frame(int lancio1, int lancio2, int punteggioAQuestoFrame, boolean strike, boolean spare) {
		this.lancio1 = lancio1;
		this.lancio2 = lancio2;
		this.punteggioAQuestoFrame = punteggioAQuestoFrame;
		this.spare = spare;
		this.strike = strike;
	}

	public int getLancio1() {
		return lancio1;
	}

	public void setLancio1(int lancio1) {
		this.lancio1 = lancio1;
	}

	public int getLancio2() {
		return lancio2;
	}

	public void setLancio2(int lancio2) {
		this.lancio2 = lancio2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPunteggioAQuestoFrame() {
		return punteggioAQuestoFrame;
	}

	public void setPunteggioAQuestoFrame(int punteggioAQuestoFrame) {
		this.punteggioAQuestoFrame = punteggioAQuestoFrame;
	}

	public boolean isSpare() {
		return spare;
	}

	public void setSpare(boolean spare) {
		this.spare = spare;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	public int getLancioBonus() {
		return lancioBonus;
	}

	public void setLancioBonus(int lancioBonus) {
		this.lancioBonus = lancioBonus;
	}

}
