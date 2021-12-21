package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "gameStats")
public class GameStat {
    @DatabaseField(generatedId = true)
    private int gameStatID;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Game game;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Team team;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Player player;
    @DatabaseField
    private Integer fgm;
    @DatabaseField
    private Integer fga;
    @DatabaseField
    private Double fg_pct;
    @DatabaseField
    private Integer fg3m;
    @DatabaseField
    private Integer fg3a;
    @DatabaseField
    private Double fg3_pct;
    @DatabaseField
    private Integer ftm;
    @DatabaseField
    private Integer fta;
    @DatabaseField
    private Double ft_pct;
    @DatabaseField
    private Integer oreb;
    @DatabaseField
    private Integer dreb;
    @DatabaseField
    private Integer reb;
    @DatabaseField
    private Integer ast;
    @DatabaseField
    private Integer stl;
    @DatabaseField
    private Integer blk;
    @DatabaseField
    private Integer pf;
    @DatabaseField
    private Integer pts;
    @DatabaseField
    private Integer plusminus;

    public GameStat() {

    }

    public GameStat(Game gameID, Team teamID, Player playerId, int fgm, int fga, double fg_pct, int fg3m, int fg3a,
            double fg3_pct, int ftm, int fta, double ft_pct, int oreb, int dreb, int reb, int ast, int stl, int blk,
            int pf, int pts, int plusminus) {
        this.game = gameID;
        this.team = teamID;
        this.player = playerId;
        this.fgm = fgm;
        this.fga = fga;
        this.fg_pct = fg_pct;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.fg3_pct = fg3_pct;
        this.ftm = ftm;
        this.fta = fta;
        this.ft_pct = ft_pct;
        this.oreb = oreb;
        this.dreb = dreb;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.pf = pf;
        this.pts = pts;
        this.plusminus = plusminus;
    }

    public Game getGame() {
        return game;
    }

    public Team getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getFgm() {
        return fgm;
    }

    public Integer getFga() {
        return fga;
    }

    public Double getFg_pct() {
        return fg_pct;
    }

    public Integer getFg3m() {
        return fg3m;
    }

    public Integer getFg3a() {
        return fg3a;
    }

    public Double getFg3_pct() {
        return fg3_pct;
    }

    public Integer getFtm() {
        return ftm;
    }

    public Integer getFta() {
        return fta;
    }

    public Double getFt_pct() {
        return ft_pct;
    }

    public Integer getOreb() {
        return oreb;
    }

    public Integer getDreb() {
        return dreb;
    }

    public Integer getReb() {
        return reb;
    }

    public Integer getAst() {
        return ast;
    }

    public Integer getStl() {
        return stl;
    }

    public Integer getBlk() {
        return blk;
    }

    public Integer getPf() {
        return pf;
    }

    public Integer getPts() {
        return pts;
    }

    public Integer getPlusminus() {
        return plusminus;
    }

    @Override
    public String toString() {
        return "Player: " + player.getPlayerName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameStat g = (GameStat) o;
        return game.equals(g.game) && player.equals(g.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game.getGameID(), team.getTeamID(), player.getPlayerID());
    }
}
