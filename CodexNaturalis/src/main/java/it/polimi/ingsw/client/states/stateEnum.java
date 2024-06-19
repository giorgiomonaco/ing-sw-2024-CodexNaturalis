package it.polimi.ingsw.client.states;

/**
 * The class contains all the states of the client during the game.
 */
public enum stateEnum {
    /**
     * When the client is trying to connect to the server.
     */
    LOGIN,
    /**
     * The login was ok, the client enters the server.
     */
    LOGIN_SUCCESSFUL,
    /**
     * After the login, the client is waiting for the admin to create a lobby.
     */
    WAITING_LOBBY,
    /**
     * If the client is the admin, the phase after the login where he has to choose
     * the number of the players in the game.
     */
    SELECT_NUM_PLAYERS,
    /**
     * Lobby waiting for the game to start (when the number of players is reached).
     */
    LOBBY,
    /**
     * The number of players in the lobby is reached and the server starts
     * the setup phase for the game. It's not the client's turn yet.
     */
    GAME_SETUP,
    /**
     * The game is stopped because only one player is left in the game,
     * a timer is started which decrees the end of the game if no other player enters before the end.
     */
    GAME_STOPPED,
    /**
     * When the game start, for the setup of the board, it's asked to select the token.
     */
    SELECT_TOKEN,
    /**
     * When the game start, for the setup of the board, it's asked to select the objective card.
     */
    SELECT_OBJECTIVE,
    /**
     * The game is started, but it's not the client turn.
     */
    WAITING_TURN,
    /**
     * It's the first part of the client's turn,
     * he's asked to select and play a card from his hand.
     */
    PLAY_CARD,
    /**
     * Second part of the client's turn,
     * after he plays a card, he's asked to draw one from the available decks.
     */
    DRAW_CARD,
    /**
     * The client is trying to join a game that is already started.
     */
    ALREADY_STARTED,
    /**
     * The client request to join the game is rejected after the admin pick the
     * number of players that can join the game.
     */
    REJECTED,
    /**
     * The game is ended because of a disconnection of a player
     * when the game is not even started.
     */
    DISCONNECTION,
    SHOW_WINNER,
    SEL_FIRST_CARD_SIDE
}
