package com.marco.bownling.domain;

import java.util.List;

/**
 * Questa classe rappresenta lo stato del gioco.
 * E' usata per comunicare con il front end.
 * 
 * @author marco
 *
 */
public class UserGame {
	private List<Frame> frames;
	private String user;

	public UserGame(String user) {
		this.user = user;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
