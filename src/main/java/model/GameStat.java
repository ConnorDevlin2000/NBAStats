package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "gameStats")
public class GameStat {
    @DatabaseField
    private Integer gameID;
    @DatabaseField
    private Integer teamID;
    @DatabaseField
    private Integer playerID;
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

    public GameStat(int gameID, int teamID, int fgm, int fga, double fg_pct, int fg3m, int fg3a, double fg3_pct, int ftm, int fta, double ft_pct, int oreb, int dreb, int reb, int ast, int stl, int blk, int pf, int pts, int plusminus) {
        this.gameID = gameID;
        this.teamID = teamID;
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

    @Override
    public String toString() {
        return "TBD";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameStat g = (GameStat) o;
        return gameID.equals(g.gameID) && playerID.equals(g.playerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, teamID, playerID);
    }
}
