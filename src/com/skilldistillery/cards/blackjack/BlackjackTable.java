package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackTable {
	private List<Player> playerList;

	public BlackjackTable() {
		this.playerList = new ArrayList<>();
	}
	
	public void addPlayer(Player addingPlayer) {
		playerList.add(addingPlayer);
	}
	
	public List<Player> getPlayerList(){
		return playerList;
	}
	
	public void clearPlayerList() {
		this.playerList = new ArrayList<>();
	}
}
