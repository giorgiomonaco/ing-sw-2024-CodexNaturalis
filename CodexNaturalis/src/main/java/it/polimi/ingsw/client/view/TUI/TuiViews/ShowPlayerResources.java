package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.ResourceCard;

import java.io.Serializable;
import java.util.List;

public class ShowPlayerResources implements TuiView, Serializable {
    private int[] resources;
    public void play(int[] resources){
        this.resources = resources;
        for(int i=0;i<7;i++){
            System.out.println("Ecco le tue risorse:");
            if(i==0) System.out.println("possiedi "+resources[i]+" mushroom");
            if(i==1) System.out.println("possiedi "+resources[i]+" leaf");
            if(i==2) System.out.println("possiedi "+resources[i]+" fox");
            if(i==3) System.out.println("possiedi "+resources[i]+" butterfly");
            if(i==4) System.out.println("possiedi "+resources[i]+" feather");
            if(i==5) System.out.println("possiedi "+resources[i]+" bottle");
            if(i==6) System.out.println("possiedi "+resources[i]+" scroll");
        }
    }

    @Override
    public void play() {

    }
}

