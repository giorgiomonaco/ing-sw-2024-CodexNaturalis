package it.polimi.ingsw.network;

public class LoginResult {
    private boolean logged;
    private boolean reconnected;

    public LoginResult(boolean logged, boolean reconnected){
        this.logged = logged;
        this.reconnected = reconnected;
    }

    public boolean isLogged(){
        return logged;
    }

    public boolean isReconnected(){
        return reconnected;
    }
}
