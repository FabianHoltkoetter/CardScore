package cardscore.fabianholtkoetter.de.cardscore.Model;

import android.graphics.Color;

/**
 * Created by Fabian on 24.01.2015.
 */
public class Player {
    private int DATABASE_ID;

    private int player_color;
    private String name;
    private int skat_games_played;
    private int doppelkopf_games_played;

    public Player(int player_color, String name, int skat_games_played, int doppelkopf_games_played, int DATABASE_ID) {
        this.player_color = player_color;
        this.name = name;
        this.skat_games_played = skat_games_played;
        this.doppelkopf_games_played = doppelkopf_games_played;
        this.DATABASE_ID = DATABASE_ID;
    }

    public int getPlayer_color() {
        return player_color;
    }

    public void setPlayer_color(int player_color) {
        this.player_color = player_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkat_games_played() {
        return skat_games_played;
    }

    public void setSkat_games_played(int skat_games_played) {
        this.skat_games_played = skat_games_played;
    }

    public int getDoppelkopf_games_played() {
        return doppelkopf_games_played;
    }

    public void setDoppelkopf_games_played(int doppelkopf_games_played) {
        this.doppelkopf_games_played = doppelkopf_games_played;
    }

    public int getDATABASE_ID() {
        return DATABASE_ID;
    }
}
