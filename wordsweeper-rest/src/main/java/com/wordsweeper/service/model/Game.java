package com.wordsweeper.service.model;

import com.wordsweeper.service.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the WordSweeper Game
 *
 * @author francisco
 */
@XmlRootElement
@Entity
@Table(name = "game")
public class Game {

    /**
     * The Status active.
     */
    public static final int STATUS_ACTIVE = 1;
    /**
     * The Status inactive.
     */
    static final int STATUS_INACTIVE = 0;
    /**
     * The Default board size.
     */
    static final int DEFAULT_BOARD_SIZE = 7;
    /**
     * The Player board rows.
     */
    static final int PLAYER_BOARD_ROWS = 4;
    /**
     * The Player board columns.
     */
    static final int PLAYER_BOARD_COLUMNS = 4;
    /**
     * The Max player to board ratio.
     */
    static final double MAX_PLAYER_TO_BOARD_RATIO = 3.0;
    /**
     * The Player ratio multiplier.
     */
    static final double PLAYER_RATIO_MULTIPLIER = 16.0;

    /**
     * Point allocation for alphabet's letters
     * 											 A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     */
    static final int[] LETTER_SCORES = new int[]{2,4,3,3,1,4,4,2,2,7,5,3,3,2,2,4,8,2,2,1,3,5,3,7,4,8};

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id; /* The internal id of the game */

    /**
     * The Board.
     */
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    Board board;

    /**
     * The Player list.
     */
    @OneToMany
    List<Player> playerList;

    /**
     * The Managing player name.
     */
    @Column(name = "managingPlayerName")
    String managingPlayerName;

    /**
     * The Locked.
     */
    @NotNull
    @Column(name = "locked")
    boolean locked;

    /**
     * The Password.
     */
    @Column(name = "password")
    String password;

    /**
     * The Unique id.
     */
    @NotNull
    @Column(name = "uniqueId")
    final String uniqueId;

    /**
     * The Status.
     */
/*
    0: Active
    1: Inactive
     */
    @NotNull
    @Column(name = "status")
    int status;

    /**
     * Instantiates a new Game.
     */
    protected Game() {
        this.uniqueId = RandomUtil.nextUniqueId();
    }

    /**
     * Instantiates a new Game.
     *
     * @param player the player
     */
    public Game(Player player) {
        this(player, null);
    }

    /**
     * Instantiates a new Game.
     *
     * @param player   the player
     * @param password the password
     */
    public Game(Player player, String password) {
        this.status = STATUS_ACTIVE;
        this.board = new Board(DEFAULT_BOARD_SIZE);
        this.playerList = new ArrayList<>();
        addPlayer(player);
        this.managingPlayerName = player.getName();
        this.password = password;
        this.uniqueId = RandomUtil.nextUniqueId();
    }

    /**
     * Add a new player given a player name to the current game
     *
     * @param playerName the new player name
     * @return false if there is a player with the same game in the game, true otherwise
     */
    public boolean addPlayer(String playerName) {
        return addPlayer(new Player(playerName));
    }

    /**
     * Add a new player given a player name to the current password protected game
     *
     * @param playerName the new player name
     * @param password   the game password
     * @return false if there is a player with the same game in the game or the password doesn't match, true otherwise
     */
    public boolean addPlayer(String playerName, String password) {
        return addPlayer(new Player(playerName), password);
    }

    /**
     * Adds a new player to the current game
     *
     * @param player the new player
     * @return false if there is a player with the same game in the game, true otherwise
     */
    public boolean addPlayer(Player player) {
        return addPlayer(player, null);
    }


    /**
     * Add a new player to the current password protected game
     *
     * @param player   the new player
     * @param password the game password
     * @return false if there is a player with the same game in the game or the password doesn't match, true otherwise
     */
    public boolean addPlayer(Player player, String password) {

        if (StringUtils.isNotBlank(this.password) && !StringUtils.equals(password, this.password)) {
            return false; /* password doesn't match */
        }

        if (status == STATUS_INACTIVE) {
            return false; /* unable to save players to a finished game */
        }

        if (isLocked()) {
            return false; /* game is locked */
        }

        if (containsPlayer(player)) {
            return false; /* game already contains a player with that name */
        }

        playerList.add(player);

        randomizePlayerLocation(player);

        if (((double) playerList.size() * PLAYER_RATIO_MULTIPLIER) /
                (double) board.size() >= MAX_PLAYER_TO_BOARD_RATIO) {
            // When the number of players to board ratio becomes too large,
            // we increase the board size
            board.grow(1);
        }

        return true;
    }

    /**
     * Removes a player from the game. If the player being removed
     * is the managing player, a new manager player is assigned if there
     * are players left
     *
     * @param playerName the name of the player to be removed
     * @return true if the player exists, false otherwise
     */
    public boolean removePlayer(String playerName) {
        for (Player p : playerList) {
            if (StringUtils.equals(p.getName(), playerName)) {

                playerList.remove(p); /* Remove player from the game */

                if (managingPlayerName.equals(p.getName()) && !playerList.isEmpty()) {
                    // If the removed player was the managing player
                    // assign a new random managing player to the game
                    managingPlayerName = playerList.get(RandomUtil.nextInt(playerList.size())).getName();
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Gets a player by player name.
     *
     * @param playerName the player name
     * @return the player if it exists, null otherwise
     */
    public Player getPlayer(String playerName) {
        for (Player p : playerList) {
            if (StringUtils.equals(p.getName(), playerName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Resets the status of the board and all
     * the player scores and their position on the board.
     */
    public void reset() {
        this.board.reset();

        for (Player player : playerList) {
            player.setScore(0);
            randomizePlayerLocation(player);
        }
    }

    /**
     * Resets the scores of all players in the game
     */
    public void resetPlayersScores() {
        for (Player player : playerList) {
            player.setScore(0);
        }
    }

    /**
     * Randomizes the location of a player
     *
     * @param player the player
     */
    private void randomizePlayerLocation(Player player) {
        /* we need to add +1 because nextInt returns a value
         * between 0 (inclusive) and the specified value (exclusive).
         * We add +1 to make it inclusive
         */
        player.setOffset(new Location(
                RandomUtil.nextInt(board.getRows() - PLAYER_BOARD_ROWS + 1),
                RandomUtil.nextInt(board.getColumns() - PLAYER_BOARD_COLUMNS + 1)));
    }

    /**
     * Determine if there's a Player with the same name already joined to the game
     *
     * @param player the player
     * @return true if there is a user with the same name in the game, false otherwise
     */
    boolean containsPlayer(Player player) {
        return containsPlayer(player.getName());
    }

    /**
     * Determine if there's a Player with the same name already joined to the game
     *
     * @param playerName the name of the player
     * @return true if there is a user with the same name in the game, false otherwise
     */
    boolean containsPlayer(String playerName) {
        return getPlayer(playerName) != null;
    }

    /**
     * Determine if there are any players in the game left
     *
     * @return false if the game has no players, true otherwise
     */
    public boolean isEmpty() {
        return playerList.isEmpty();
    }

    /**
     * Determine if the game is locked
     *
     * @return true if locked, false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Locks the game if the locking user is the managing user
     *
     * @return true if the game was locked, false otherwise
     */
    public boolean lock() {
        this.locked = true;
        return true;
    }

    /**
     * Ends the current game
     */
    public void end() {
        this.managingPlayerName = null;
        this.locked = true;
        this.status = STATUS_INACTIVE;
    }

    /**
     * Whether the game has ended
     *
     * @return true if the game ended, false otherwise
     */
    public boolean ended() {
        return status == STATUS_INACTIVE;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets managing player name.
     *
     * @return the managing player name
     */
    public String getManagingPlayerName() {
        return managingPlayerName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Gets player list.
     *
     * @return the player list
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Is password protected boolean.
     *
     * @return the boolean
     */
    public boolean isPasswordProtected() {
        return StringUtils.isNotBlank(password);
    }

    /**
     * Reposition Player's board by rowChange and columnChange, staying within bounds
     *
     * @param player       the player to reposition
     * @param rowChange    the rowChange
     * @param columnChange the columnChange
     * @return true if the board was repositioned, false otherwise
     */
    public boolean repositionBoard(Player player, int rowChange, int columnChange) {

        int currentRow = player.getOffset().getRow();
        int currentColumn = player.getOffset().getColumn();

        int newRow = Math.max(0, Math.min(currentRow + rowChange, board.getRows() - PLAYER_BOARD_ROWS));
        int newColumn = Math.max(0, Math.min(currentColumn + columnChange, board.getColumns() - PLAYER_BOARD_COLUMNS));

        if (currentRow != newRow || currentColumn != newColumn) {
            player.setOffset(new Location(newRow, newColumn));
            return true;
        }

        return false;
    }

    /**
     * Calculate the score of a word using the following formula:
     * 2^N * 10 * SUM( 2^M * Pi ) * cellMultiplier
     * Where N is the number of tiles in the word
     * M is the number of players that share the cell if M is greater than 1
     * Pi is the letter frequency
     * cellMultiplier is the multiplier of the bonus cell if the word
     * contains the bonus cell.
     *
     * @param word the word
     * @return the score of the word
     */
    public int calculateWordScore(Word word) {
    	int numOfPlayers = this.playerList.size();
    	int wordSize = word.locations.size();
    	int N = word.getWordLength();
    	int[] M = new int[wordSize];
    	int sum = 0;
    	boolean multiplier = false;
    	Location bonusCell = this.board.bonusCellLocation;

    	if(N > 1){
    		// calculate how many players share each cell
    		for(int k = 0; k < numOfPlayers; k++){
    			Player player = this.playerList.get(k);
    			for(int i = 0; i < wordSize; i++){
    				Location cellLoc = word.locations.get(i);
    				if(player.isLocationInPlayerBoard(cellLoc))
    					M[i]++;
    			}
    		}
    		// calculate the sum and check for multiplier cell
    		for(int i = 0; i < wordSize; i++){
    			if(word.locations.get(i).equals(bonusCell))
    				multiplier = true;
    			sum += Math.pow(2, M[i]-1) * calcLetterScore(word.word.charAt(i));
    		}
    	} else {
    		// calculate the sum and check for multiplier cell
    		for(int i = 0; i < wordSize; i++){
    			if(word.locations.get(i).equals(bonusCell))
    				multiplier = true;
    			sum += calcLetterScore(word.word.charAt(i));
    		}
    	}

    	sum *= 10;
    	sum *= Math.pow(2, N);
    	return (multiplier) ? sum * 10 : sum;
    }

    /**
     * Determine whether a word is valid
     *
     * @param word the word to validate
     * @return true if the word is valid, false if not
     */
    public boolean validateWord(Player player, Word word) {

        if (word.containsDuplicateCells()) {
            return false;
        }

        StringBuilder sb = new StringBuilder(32);

        for (int i = 0; i < word.locations.size(); i++) {
            Location location = word.locations.get(i);

            if (i < word.locations.size() - 1 &&
                    !board.areLocationsAdjacent(location, word.locations.get(i + 1))) {
                return false;
            }

            if (!player.isLocationInPlayerBoard(location)) {
                return false;
            } else {
                sb.append(board.getLetterAtLocation(location).printCharacter());
            }
        }

        return StringUtils.equalsIgnoreCase(word.word, sb.toString());
    }

    int calcLetterScore(char c){
		int Pi = (int) c;
		if(Pi > 90)
			Pi -= 97;
		else
			Pi -= 65;
		return LETTER_SCORES[Pi];
    }
}
