package com.marco.bownling.services.implementation;

import java.util.ArrayList;
import java.util.List;

import com.marco.bownling.domain.Frame;
import com.marco.bownling.services.interfaces.BowlingGameInterface;
import com.marco.utils.MarcoException;

/**
 * La mia implementazione del gioco
 * 
 * @author marco
 *
 */
public class MarcoBowlingGame implements BowlingGameInterface {

	
	/*
	 * Qui mi salvo i birilli abbattuti al lancio xx
	 */
	private int[] punteggioLanci = new int[21];
	private List<Frame> board = new ArrayList<Frame>();
	private int lanciEseguitiAllUltimoFrame = 0;
	/*
	 * contatore dei lanci eseguite. Mi serve per salvare
	 * il numero di birilli nella posizione corretta dell'array
	 * precedente
	 */
	private int lanciEseguiti = 0;
	
	public MarcoBowlingGame() {
		for(int i = 0; i < 10; i++) {
			board.add(new Frame());
		}
	}
	

	@Override
	public boolean lancio(int birilliAbbattuti) throws MarcoException {
		if(checkPartitaFinita()) {
			throw new MarcoException("Parita finita");
		}
		
		/*
		 * Salvo i birilli abbattuti e incremento 
		 * il contatore dei lanci eseguiti
		 */
		punteggioLanci[lanciEseguiti++] = birilliAbbattuti;
		aggiornaSituazioneParita();
		return true;
	}

	@Override
	public int punteggioTotale() {
		int punteggio = 0;
		int lancio = 0;
		
		/*
		 * In una parita ci sono 10 frame,
		 * Uso un secondo contatore per capire
		 * se sto analizzando il lancio numero "1" del frame
		 * o il lancio numer "2" del frame
		 */
		for(int frame = 0; frame < 10; frame++) {
			if(isStrike(lancio)) {
				punteggio += punteggioStrike(lancio);
				lancio++;
			}else if(isSpare(lancio)) {
				punteggio += punteggioSpare(lancio);
				lancio += 2;//vado al prossimo frame
			}else {
				punteggio += punteggioLanci[lancio] + punteggioLanci[lancio + 1];
				lancio += 2;//vado al prossimo frame
			}
		}
		return punteggio;
	}

	
	/**
	 * Il punteggio dello spare equivale a 10
	 * più il numero dei birilli abbattuti con 
	 * il lancio successivo (nel frame successivo)
	 * 
	 * @param lancio
	 * @return
	 */
	private int punteggioSpare(int lancio) {
		/*
		 * più 2 perchè devo prendere il numero di
		 * birilli abbattuti nel primo tiro
		 * del frame successivo
		 */
		return 10 + punteggioLanci[lancio + 2];
	}
	
	/**
	 * Il punteggio dello strike equivale a 10
	 * più il numero dei birilli abbatuti nei
	 * due lanci seccessivi al corrente
	 * 
	 * @param lancio
	 * @return
	 */
	private int punteggioStrike(int lancio) {
		return 10 + punteggioLanci[lancio + 1] + punteggioLanci[lancio + 2];
	}
	
	private boolean isSpare(int lancio) {
		return punteggioLanci[lancio] + punteggioLanci[lancio + 1]== 10;
	}
	
	private boolean isStrike(int lancio) {
		return punteggioLanci[lancio] == 10;
	}

	public List<Frame> aggiornaSituazioneParita() {
		int punteggio = 0;
		int lancio = 0;
		
		int frameDaValorizzare = 0;
		
		/*
		 * Ciclo tutti i lanci fino a quando non arrivo al:
		 * - ultimo frame
		 *   o
		 * - ho raggiunto il numero di lanci eseguiti dall'utente
		 */
		while (frameDaValorizzare < board.size() && lancio < lanciEseguiti) {
			Frame f = board.get(frameDaValorizzare);
			
			
			if(frameDaValorizzare == 9) {
				/*
				 * Il frame 9 è quello speciale
				 */
				int punteggioTmp = 0;
				
				if(isStrike(lancio)) {
					punteggioTmp += punteggioStrike(lancio);
					f.setStrike(true);
				}else if(isSpare(lancio)) {
					punteggioTmp += punteggioSpare(lancio);
					f.setSpare(true);
				}else {
					punteggioTmp += punteggioLanci[lancio] + punteggioLanci[lancio + 1];
				}
				
				
				f.setLancio1(punteggioLanci[lancio]);
				f.setLancio2(punteggioLanci[lancio + 1]);
				
				/*
				 * Se ho fatto due strike o spare ho diritto al
				 * lacio bonus
				 */
				int tmp = punteggioLanci[lancio] + punteggioLanci[lancio + 1];
				if(tmp == 10 || tmp == 20) {
					f.setLancioBonus(punteggioLanci[lancio + 2]);
				}
				
				punteggio += punteggioTmp;
			}else {
				if(isStrike(lancio)) {
					punteggio += punteggioStrike(lancio);
					f.setLancio1(10);
					f.setStrike(true);
					lancio++;
				}else if(isSpare(lancio)) {
					punteggio += punteggioSpare(lancio);
					f.setLancio1(punteggioLanci[lancio]);
					f.setLancio2(punteggioLanci[lancio + 1]);
					f.setSpare(true);
					lancio += 2;//vado al prossimo frame
				}else {
					punteggio += punteggioLanci[lancio] + punteggioLanci[lancio + 1];
					f.setLancio1(punteggioLanci[lancio]);
					f.setLancio2(punteggioLanci[lancio + 1]);
					lancio += 2;//vado al prossimo frame
				}
			}
			
			f.setPunteggioAQuestoFrame(punteggio);
			frameDaValorizzare++;
		}
		
		return board;
	}

	/**
	 * Controlla se la parita è finita.
	 * 
	 * @return true se finita, false altrimenti
	 */
	private boolean checkPartitaFinita() {
		if(lanciEseguiti > 0) {
			boolean sonoAllUltimoFrame = board.get(9).getPunteggioAQuestoFrame() == punteggioTotale();
			
			if(sonoAllUltimoFrame || lanciEseguitiAllUltimoFrame != 0) {
				Frame ultimoFrame = board.get(9);
				lanciEseguitiAllUltimoFrame++;
				if(lanciEseguitiAllUltimoFrame > 2) {
					return true;
				}else if(lanciEseguitiAllUltimoFrame == 2) {
					int punteggioTmp = ultimoFrame.getLancio1() + ultimoFrame.getLancio2();
					return punteggioTmp != 10 && punteggioTmp != 20;
				}
				
			}
		}
		return false;
	}

	@Override
	public List<Frame> getSituazionePartita() {
		return this.board;
	}
	
}
