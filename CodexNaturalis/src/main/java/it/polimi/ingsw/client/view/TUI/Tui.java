package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.ReadCommand;
import it.polimi.ingsw.client.stateManager.stateEnum;
import it.polimi.ingsw.client.view.TUI.TuiViews.*;
import it.polimi.ingsw.client.view.UserInterface;

import java.util.HashMap;
import java.util.Map;

public class Tui implements UserInterface {

    private stateEnum currentState;
    private Map<stateEnum, TuiView> phaseView;
    private ReadCommand reader;

    public Tui(Client client){

        currentState = client.getCurrentState();
        client.setUI(this);
        phaseView = new HashMap<>();
        reader = new ReadCommand(this, client);
        Thread readerThread = new Thread(reader);
        readerThread.start();

        phaseView.put(stateEnum.LOGIN, new LoginView());
        phaseView.put(stateEnum.ALREADY_STARTED, new AlreadyStartedView());
        phaseView.put(stateEnum.DISCONNECTION, new DisconnectionView());
        phaseView.put(stateEnum.DRAW_CARD, new DrawCardView());
        phaseView.put(stateEnum.LOBBY, new LobbyView());
        phaseView.put(stateEnum.PLAY_CARD, new PlayCardView());
        phaseView.put(stateEnum.SELECT_NUM_PLAYERS, new SelNumPlayerView());
        phaseView.put(stateEnum.SELECT_TOKEN, new SelTokenView());
        phaseView.put(stateEnum.WAITING_TURN, new WaitTurnView());

    }


    @Override
    public void run() {
        switch(currentState){
            case LOGIN:
                phaseView.get(stateEnum.LOGIN).start();
                break;
            case ALREADY_STARTED:
                phaseView.get(stateEnum.ALREADY_STARTED).start();
                break;
            case DISCONNECTION:
                phaseView.get(stateEnum.DISCONNECTION).start();
                break;
            case DRAW_CARD:
                phaseView.get(stateEnum.DRAW_CARD).start();
                break;
            case LOBBY:
                phaseView.get(stateEnum.LOBBY).start();
                break;
            case PLAY_CARD:
                phaseView.get(stateEnum.PLAY_CARD).start();
                break;
            case SELECT_NUM_PLAYERS:
                phaseView.get(stateEnum.SELECT_NUM_PLAYERS).start();
                break;
            case SELECT_TOKEN:
                phaseView.get(stateEnum.SELECT_TOKEN).start();
                break;
            case WAITING_TURN:
                phaseView.get(stateEnum.WAITING_TURN).start();
                break;
        }
    }

    @Override
    public void endGame() {

    }

    @Override
    public void error() {

    }

    @Override
    public void showMessage() {

    }

    public stateEnum getCurrentState() {
        return currentState;
    }

}
