public class GameBoardManager {
    //This class create all the boards for the players, personals and common
    private Game game;

    //Constructor
    public GameBoardManager(Game game) {
        this.game = game;
    }

    //initialization of all the game boards needed
    public void initializeBoards() {
        //fore every player in the game
        for (Player p : game.getPlayerList()) {
            //create a new game board
            GameBoard gameBoard = new GameBoard();
            //Associate the game board to every player
            p.setGameBoard(gameBoard);
        }
    }
}
