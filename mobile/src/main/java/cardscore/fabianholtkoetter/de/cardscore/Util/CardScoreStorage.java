package cardscore.fabianholtkoetter.de.cardscore.Util;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cardscore.fabianholtkoetter.de.cardscore.Model.Game;
import cardscore.fabianholtkoetter.de.cardscore.Model.Player;

/**
 * Created by Fabian on 28.01.2015.
 */
public class CardScoreStorage {

    /**
     * Saves a Game-Object in it's own file.
     *
     * @param context Context of the Activity
     * @param game    Game to be saved
     * @throws IOException
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveGame(Context context, Game game) throws IOException {
        String FILENAME = "game_" + game.getDATABASE_ID() + ".cardscoregame";
        File file = new File(FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
        out.writeObject(game);
        out.close();
    }

    /**
     * Returns a saved Game
     *
     * @param context Context of the Activity
     * @param gameKey Database-Key of the Game to be returned
     * @return The saved Game
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Game getGame(Context context, int gameKey) throws IOException, ClassNotFoundException {
        String FILENAME = "game_" + gameKey + ".cardscoregame";
        File file = new File(FILENAME);
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(FILENAME));
            Game returnedGame = (Game) in.readObject();
            in.close();
            return returnedGame;
        } else {
            return null;
        }
    }

    /**
     * Saves a Player-Object in it's own file.
     *
     * @param context Context of the Activity
     * @param player  Player to be saved
     * @throws IOException
     */
    public static void savePlayer(Context context, Player player) throws IOException {
        String FILENAME = "player_" + player.getDATABASE_ID() + ".cardscoreplayer";
        File file = new File(FILENAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
        out.writeObject(player);
        out.close();
    }

    /**
     * Returns a saved Player
     *
     * @param context   Context of the Activity
     * @param playerKey Database-key of the layer to be returned
     * @return The saved Player
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Player getPlayer(Context context, int playerKey) throws IOException, ClassNotFoundException {
        String FILENAME = "player_" + playerKey + ".cardscoreplayer";
        File file = new File(FILENAME);
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(FILENAME));
            Player returnedPlayer = (Player) in.readObject();
            in.close();
            return returnedPlayer;
        } else {
            return null;
        }
    }
}
