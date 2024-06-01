package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;

import java.rmi.RemoteException;
import java.util.Objects;

public class PlayCardCommand implements CommandManager {
    private Client client;

    public PlayCardCommand(Client client){
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {
        int index = Integer.parseInt(commands[1]);
        int x = Integer.parseInt(commands[2]);
        int y = Integer.parseInt(commands[3]);
        boolean side = Boolean.parseBoolean(commands[4]);
        Card card = client.getPlayerHand().get(index-1);

        if(!client.getCurrentState().equals(stateEnum.PLAY_CARD) ){
            throw new CommandNotAvailableException();
        }
        else if(index < 1 || index > 3){
            throw new WrongInsertionException("The selected index is not available. Please choose above the available ones");
        }
        else if (x < 0 || x > client.getBoards().getMAX_X()){
            throw new WrongInsertionException("The selected x coordinate is not available. Please choose above the available ones");
        }
        else if (y < 0 || y > client.getBoards().getMAX_Y()){
            throw new WrongInsertionException("The selected y coordinate is not available. Please choose above the available ones");
        }
        else if (client.getBoards().getCheckboard()[x][y] == -1 || client.getBoards().getCheckboard()[x][y] == 1){
            throw new WrongInsertionException("The selected position is not available. Please choose above the available ones");
        }
        else if(!Objects.equals(commands[4], "true") && !Objects.equals(commands[4], "false")){
            throw new WrongInsertionException("The selected side is not available. Please choose above the available ones");
        }

        else if(card instanceof GoldCard){
            for (int i = 0; i < 7; i++) {
                if(!(((GoldCard) card).getNeededSymbols()[i] <= client.getResources()[i])){
                    throw new WrongInsertionException("You do not have enough resources to play this card. Please choose another one");
                }

            }

        }
        SelectionCard toSend = new SelectionCard(client.getUsername(), client.getPlayerHand().get(index-1), x, y, side);
        client.sendMessage(toSend);



    }
}
