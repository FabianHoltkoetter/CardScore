package cardscore.fabianholtkoetter.de.cardscore.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cardscore.fabianholtkoetter.de.cardscore.Exceptions.InvalidGameTypeException;
import cardscore.fabianholtkoetter.de.cardscore.Model.Game;
import cardscore.fabianholtkoetter.de.cardscore.Model.Player;
import cardscore.fabianholtkoetter.de.cardscore.R;
import cardscore.fabianholtkoetter.de.cardscore.Util.CardScoreStorage;
import cardscore.fabianholtkoetter.de.cardscore.Util.GameCardViewAdapter;


public class GameListFragment extends Fragment {
    RecyclerView mCardList;
    FloatingActionButton mActionSkat;
    FloatingActionButton mActionDoppelkopf;
    TextView mTextViewNoGames;

    private SharedPreferences userPrefs;
    private List<Game> games;

    public GameListFragment(){}

    public static final GameListFragment newInstance(int title, String message)
    {
        GameListFragment fragment = new GameListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gamelist, container, false);

        mCardList = (RecyclerView) v.findViewById(R.id.cardListGames);
        mActionSkat = (FloatingActionButton) v.findViewById(R.id.games_fab_action_skat);
        mActionDoppelkopf = (FloatingActionButton) v.findViewById(R.id.games_fab_action_doppelkopf);
        mTextViewNoGames = (TextView) v.findViewById(R.id.noGamesTextView);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.app_preferences, false);
        userPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mActionSkat.setColorNormal(userPrefs.getInt(getString(R.string.setting_skat_color_key), 0));
        mActionDoppelkopf.setColorNormal(userPrefs.getInt(getString(R.string.setting_doppelkopf_color_key), 0));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mCardList.setLayoutManager(llm);

        //Load saved games
        int counter = 0;
        /**
        games = new ArrayList<Game>();
        while (counter <= 20) {
            try {
                Game next = CardScoreStorage.getGame(getActivity(), counter);
                if (next != null) {
                    games.add(next);
                }
                counter++;
            } catch (Exception e) {
                counter++;
            }
        }
         **/
        games = createTestData();
        toggleVisibility();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionSkat.setColorNormal(userPrefs.getInt(getString(R.string.setting_skat_color_key), 0));
        mActionDoppelkopf.setColorNormal(userPrefs.getInt(getString(R.string.setting_doppelkopf_color_key), 0));
        mCardList.getAdapter().notifyDataSetChanged();
    }

    public List<Game> createTestData(){
        List<Player> players = new ArrayList<Player>();
        Player p1 = new Player(getActivity().getResources().getColor(R.color.blue), "Hans", 0, 0, 0);
        Player p2 = new Player(getActivity().getResources().getColor(R.color.green), "Peter", 0, 0, 1);
        Player p3 = new Player(getActivity().getResources().getColor(R.color.yellow), "Mark", 0, 0, 2);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        try {
            List<Integer> points = new ArrayList<Integer>();
            points.add(120);
            points.add(20);
            points.add(-10);
            Game g1 = new Game("Game Eins", players, Game.SKAT_GAME, 0);
            g1.setPoints(points);
            Game g2 = new Game("Game Zwei", players, Game.DOPPELKOPF_GAME,1);
            g2.setPoints(points);
            Game g3 = new Game("Game Drei", players, Game.SKAT_GAME, 2);
            g3.setPoints(points);
            List<Game> games = new ArrayList<Game>();
            games.add(g1);
            games.add(g2);
            games.add(g3);
            return games;
        } catch (InvalidGameTypeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void toggleVisibility() {
        if (games.size() > 0) {
            mCardList.setAdapter(new GameCardViewAdapter(getActivity(), games));
            mCardList.setItemAnimator(new DefaultItemAnimator());
        } else {
            mTextViewNoGames.setVisibility(View.VISIBLE);
            mTextViewNoGames.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clicked TextView", Toast.LENGTH_SHORT).show();
                }
            });
            mCardList.setVisibility(View.GONE);
        }
    }
}
