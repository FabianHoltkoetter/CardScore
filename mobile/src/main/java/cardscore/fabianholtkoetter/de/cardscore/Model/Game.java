package cardscore.fabianholtkoetter.de.cardscore.Model;

import android.util.Pair;

import java.util.List;

import cardscore.fabianholtkoetter.de.cardscore.Exceptions.InvalidGameTypeException;

/**
 * Created by Fabian on 24.01.2015.
 */
public class Game {
    public static int SKAT_GAME = 0;
    public static int DOPPELKOPF_GAME = 1;

    private int DATABASE_ID;

    private String game_name;
    private List<Player> players;
    private List<Integer> points;
    private List<Pair<Integer, Integer>> moves;
    private int GameType;

    public Game(String name, List<Player> players, int gameType, int DATABASE_ID, List<Integer> points, List<Pair<Integer, Integer>> moves) throws InvalidGameTypeException {
        this(name, players, gameType, DATABASE_ID);
        this.points = points;
        this.moves = moves;
    }

    public Game(String name, List<Player> players, int gameType, int DATABASE_ID) throws InvalidGameTypeException {
        this.game_name = name;
        this.players = players;
        this.DATABASE_ID = DATABASE_ID;
        if (gameType <= DOPPELKOPF_GAME) this.GameType = gameType;
        else throw new InvalidGameTypeException();
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> player_keys) {
        this.players = player_keys;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }

    public List<Pair<Integer, Integer>> getMoves() {
        return moves;
    }

    public void setMoves(List<Pair<Integer, Integer>> moves) {
        this.moves = moves;
    }

    public int getGameType() {
        return GameType;
    }

    public void setGameType(int gameType) {
        GameType = gameType;
    }

    public int getDATABASE_ID() {
        return DATABASE_ID;
    }
}
