package ca.uvic.seng330.assn1;

import java.util.Comparator;

public final class PlayerComparator implements Comparator<Player> {
	@Override
	public int compare(Player firstPlayer, Player secondPlayer){
		return(firstPlayer.getPoints() - secondPlayer.getPoints());
	}

}
