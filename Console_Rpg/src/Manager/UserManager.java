package Manager;

import Player.Player;

public class UserManager {

    private static UserManager _master;
    public static UserManager Master(){
        if (_master == null){
            _master = new UserManager();
        }
        return _master;
    }

    public UserManager(){

    }


    private Player activePlayer;

    public Player getActivePlayer() {
        return activePlayer;
    }
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
}
