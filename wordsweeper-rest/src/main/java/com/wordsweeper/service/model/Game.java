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
     */
    public void repositionBoard(Player player, int rowChange, int columnChange) {
        player.setOffset(new Location(
                Math.max(0, Math.min(player.getOffset().getRow() + rowChange,
                        board.getRows() - PLAYER_BOARD_ROWS)),
                Math.max(0, Math.min(player.getOffset().getColumn() + columnChange,
                        board.getColumns() - PLAYER_BOARD_COLUMNS))));
    }

    public boolean findWord(Player playerName, String word, List<Location> locations) {
        return false;
    }
}
