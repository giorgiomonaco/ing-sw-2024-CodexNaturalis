package it.polimi.ingsw.client.view.TUI.TuiViews;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;

public class SelTokenView implements TuiView{
    Colors color = new Colors();
    @Override
    public void play(Client client) {

        System.out.println("WHICH COLOUR DO YOU WANT TO PLAY WITH?\nCHOOSE BETWEEN: ");
        System.out.print(color.redColor + " red " + color.resetColor);
        System.out.print(color.blueColor + " blue " + color.resetColor);
        System.out.print(color.yellowColor + " yellow " + color.resetColor);
        System.out.print(color.greenColor + " green " + color.resetColor);
    }
}
