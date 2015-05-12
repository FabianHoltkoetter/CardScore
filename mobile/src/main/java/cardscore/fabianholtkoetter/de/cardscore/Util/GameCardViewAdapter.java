package cardscore.fabianholtkoetter.de.cardscore.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cardscore.fabianholtkoetter.de.cardscore.Model.Game;
import cardscore.fabianholtkoetter.de.cardscore.Model.Player;
import cardscore.fabianholtkoetter.de.cardscore.R;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by Fabian on 29.01.2015.
 */
public class GameCardViewAdapter extends RecyclerView.Adapter<GameCardViewAdapter.GameCardViewHolder> {

    private List<Game> gameList;
    private Context context;
    private SharedPreferences userPrefs;

    public GameCardViewAdapter(Context context, List<Game> gameList) {
        this.gameList = gameList;
        this.context = context;
        userPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //When get's shown in UI
    @Override
    public void onBindViewHolder(GameCardViewHolder holder, int position) {
        Game game = gameList.get(position);
        String gameName = game.getGame_name();
        String gameType = "";
        int color = 0;
        List<Player> players = game.getPlayers();

        if (PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_theme_key), "light").equals(context.getString(R.string.setting_theme_default_value))) {
            holder.vCardView.setBackgroundColor(context.getResources().getColor(R.color.background_material_light));
        } else {
            holder.vCardView.setBackgroundColor(context.getResources().getColor(R.color.background_material_dark));
        }

        if (game.getGameType() == Game.SKAT_GAME) {
            color = userPrefs.getInt(context.getString(R.string.setting_skat_color_key), 0);
            gameType = context.getResources().getString(R.string.dashboard_fab_option_skat);
        } else if (game.getGameType() == Game.DOPPELKOPF_GAME) {
            color = userPrefs.getInt(context.getString(R.string.setting_doppelkopf_color_key), 0);
            gameType = context.getResources().getString(R.string.dashboard_fab_option_doppelkopf);
        }

        holder.vTitle.setText(gameName);
        holder.vType.setText(gameType);
        holder.vType.setTextColor(color);
        holder.vColorHighlight.setBackgroundColor(color);
        holder.vChart.setScrollEnabled(false);
        holder.vChart.setZoomEnabled(false);
        holder.vChart.setValueSelectionEnabled(true);
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axis_values = new ArrayList<AxisValue>();
        for (int i = 0; i < game.getPlayers().size(); i++) {
            values = new ArrayList<SubcolumnValue>();
            SubcolumnValue val = new SubcolumnValue(game.getPoints().get(i), game.getPlayers().get(i).getPlayer_color());
            values.add(val);
            axis_values.add(new AxisValue(i, game.getPlayers().get(i).getName().toCharArray()));
            Column column = new Column(values);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);
        Axis axisX = new Axis().setValues(axis_values);
        Axis axisY = new Axis().setHasLines(false);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        holder.vChart.setColumnChartData(data);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.cardview_translate_animation);
        holder.vCardView.startAnimation(anim);
    }

    @Override
    public GameCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_game, viewGroup, false);
        return new GameCardViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class GameCardViewHolder extends RecyclerView.ViewHolder {
        protected CardView vCardView;
        protected View vColorHighlight;
        protected TextView vTitle;
        protected TextView vType;
        protected ColumnChartView vChart;

        public GameCardViewHolder(View itemView) {
            super(itemView);
            vCardView = (CardView) itemView.findViewById(R.id.card_view_game);
            vColorHighlight = (View) itemView.findViewById(R.id.cardview_game_colorHighlight);
            vTitle = (TextView) itemView.findViewById(R.id.cardview_game_title);
            vType = (TextView) itemView.findViewById(R.id.cardview_game_gametype);
            vChart = (ColumnChartView) itemView.findViewById(R.id.cardview_game_chart);
        }
    }
}
