package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.GUI.Gui;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.WaitTurnMsg;

public class WaitTurnHandler implements MessageHandler{
    @Override
    public void manage(Message msg, Client client) {
        WaitTurnMsg wait = (WaitTurnMsg) msg;
        client.setEndTurn(false);
        client.setCurrentState(stateEnum.WAITING_TURN);

        if (wait.getFlag() == 1) {
            // message of personal end-turn
            client.setEndTurn(true);
            client.setPoints(wait.getPoints());
            client.setBoards(wait.getBoards());
            client.setPlayerHand(wait.getCard());
            client.setResources(wait.getResources());
            client.setCurrIndex(wait.getCurrIndex());
        } else if (wait.getFlag() == 2) {
            // message of other player end-turn
            client.setGameBoards(wait.getBoard(), client.getCurrIndex());
            client.setPoints(wait.getPoints());
        } else if (wait.getFlag() == 3) {
            // set the correct index
            client.setCurrIndex(wait.getCurrIndex());

            // print the message of wait turn in tui only in this case
            if (client.getUI() instanceof Tui) {
                client.getUI().run();
            }

        } else if (wait.getFlag() == 4) {
            // message to manage the reconnection
            client.setTurn(wait.getTurn());
            client.setInit(wait.getInit());
            client.setCommonObjectives(wait.getCommon());
            client.setObjective(wait.getPersonal());
            client.setPlayerList(wait.getUserList());
            client.setPlayersToken(wait.getTokens());
            client.setChat(wait.getChat());
        } else {
            // message for the end of the other players first turn
            client.setGameBoards(wait.getBoard(), client.getCurrIndex());
            client.setPoints(wait.getPoints());
            client.setPlayersToken(wait.getTokens());
        }


        if (client.getUI() instanceof Gui) {
            client.getUI().run();
        }

    }
}
