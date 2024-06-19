package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayCardCommand implements CommandManager {
    private final Client client;
    private Card[][] cardBoard;

    public PlayCardCommand(Client client){
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {
        int index = Integer.parseInt(commands[1]);
        int x = Integer.parseInt(commands[2]);
        int y = Integer.parseInt(commands[3]);
        boolean side = commands[4].equals("front");
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
        else if (client.getBoards().getCheckBoard()[x][y] == -1 || client.getBoards().getCheckBoard()[x][y] == 1){
            throw new WrongInsertionException("The selected position is not available. Please choose above the available ones");
        }
        else if(!Objects.equals(commands[4], "front") && !Objects.equals(commands[4], "back")){
            throw new WrongInsertionException("The selected side is not available. Please choose above the available ones");
        }

        else if(card instanceof GoldCard && side){
            for (int i = 0; i < 7; i++) {
                if(!(((GoldCard) card).getNeededSymbols()[i] <= client.getResources()[i])){
                    throw new WrongInsertionException("You do not have enough resources to play this card. Please choose another one");
                }

            }
        }

        // List to store the covered angles by neighboring cards
        List<VisibleAngle> coveredAngle = new ArrayList<>();
        cardBoard = client.getBoards().getGameBoard();
        // Check the front visible angle of the card at (x+1, y+1) if it exists
        if (cardBoard[x + 1][y + 1] != null) {
            boolean front = cardBoard[x + 1][y + 1].getSide();
            if (front) {
                if (cardBoard[x + 1][y + 1].getFrontVisibleAngle(0) != null) {
                    coveredAngle.add(cardBoard[x + 1][y + 1].getFrontVisibleAngle(0));
                }
            }
        }

        // Check the front visible angle of the card at (x+1, y-1) if it exists
        if (cardBoard[x + 1][y - 1] != null) {
            boolean front = cardBoard[x + 1][y - 1].getSide();
            if (front) {
                if (cardBoard[x + 1][y - 1].getFrontVisibleAngle(2) != null) {
                    coveredAngle.add(cardBoard[x + 1][y - 1].getFrontVisibleAngle(2));
                }
            }
        }

        // Check the front visible angle of the card at (x-1, y+1) if it exists
        if (cardBoard[x - 1][y + 1] != null) {
            boolean front = cardBoard[x - 1][y + 1].getSide();
            if (front) {
                if (cardBoard[x - 1][y + 1].getFrontVisibleAngle(1) != null) {
                    coveredAngle.add(cardBoard[x - 1][y + 1].getFrontVisibleAngle(1));
                }
            }
        }

        // Check the front visible angle of the card at (x-1, y-1) if it exists
        if (cardBoard[x - 1][y - 1] != null) {
            boolean front = cardBoard[x - 1][y - 1].getSide();
            if (front) {
                if (cardBoard[x - 1][y - 1].getFrontVisibleAngle(3) != null) {
                    coveredAngle.add(cardBoard[x - 1][y - 1].getFrontVisibleAngle(3));
                }
            }
        }

        // If there are covered angles, lower the player's resources based on the symbols
        if (!coveredAngle.isEmpty()) {
            System.out.print("\nSelectioning that square you are going to cover and lose those resources:\n[");
            for (VisibleAngle angle : coveredAngle) {
                System.out.print(""+angle.getSymbol().getSymbolName()+"");
                }
            System.out.println("]");
            System.out.print("press [y] to confirm the choice or [n] to deny");

            }


        coveredAngle.clear(); // Clear the list for future use
        SelectionCard toSend = new SelectionCard(client.getUsername(), client.getPlayerHand().get(index-1), x, y, side);
        client.sendMessage(toSend);
    }



    //MANDA CARTA


}

