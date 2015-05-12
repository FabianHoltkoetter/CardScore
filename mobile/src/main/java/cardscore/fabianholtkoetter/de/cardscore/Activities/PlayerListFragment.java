package cardscore.fabianholtkoetter.de.cardscore.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import cardscore.fabianholtkoetter.de.cardscore.Model.Player;
import cardscore.fabianholtkoetter.de.cardscore.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * .
 * Organization: HM FK07
 * Project: CardScore, cardscore.fabianholtkoetter.de.cardscore.Activities
 * Author(s): Fabian Holtkoetter
 * Date: 12.05.2015
 * OS: Windows 8.1
 * Java-Version: 1.8
 * Processor: Intel Core i5-4300 CPU @ 1.9GHz
 * RAM: 4GB
 */
public class PlayerListFragment extends android.support.v4.app.Fragment {
    RecyclerView mPlayerCardList;
    FloatingActionButton mActionAddPlayer;
    TextView mTextViewNoPlayers;

    private SharedPreferences userPrefs;
    private List<Player> players;

    public PlayerListFragment(){}

    public static PlayerListFragment newInstance()
    {
        return new PlayerListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_playerlist, container, false);

        mPlayerCardList = (RecyclerView) v.findViewById(R.id.fragment_dashplayers_recyclerview_players);
        mActionAddPlayer = (FloatingActionButton) v.findViewById(R.id.fragment_dashplayers_fab_addPlayer);
        mTextViewNoPlayers = (TextView) v.findViewById(R.id.fragment_dashplayers_textview_noPlayers);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mPlayerCardList.setLayoutManager(llm);

        //players = createTestData();
        toggleVisibility();

        return v;
    }

    public void toggleVisibility() {
        if (players != null && players.size() > 0) {
            //mCardList.setAdapter(new GameCardViewAdapter(getActivity(), games));
            //mCardList.setItemAnimator(new DefaultItemAnimator());
        } else {
            mTextViewNoPlayers.setVisibility(View.VISIBLE);
            mTextViewNoPlayers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Clicked TextView", Toast.LENGTH_SHORT).show();
                }
            });
            mPlayerCardList.setVisibility(View.GONE);
        }
    }
}
