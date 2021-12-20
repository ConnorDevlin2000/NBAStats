package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "games")
public class Game {
    @DatabaseField(id = true)
    private Integer gameID;
    @DatabaseField
    private Integer homeTeamID;
    @DatabaseField
    private Integer visitorTeamID;
    @DatabaseField
    private Integer season;
    @DatabaseField
    private Integer ptsHome;
    @DatabaseField
    private Double fgPctHome;
    @DatabaseField
    private Double ftPctHome;
    @DatabaseField
    private Double fg3PctHome;
    @DatabaseField
    private Integer astHome;
    @DatabaseField
    private Integer rebHome;
    @DatabaseField
    private Integer ptsAway;
    @DatabaseField
    private Double fgPctAway;
    @DatabaseField
    private Double ftPctAway;
    @DatabaseField
    private Double fg3PctAway;
    @DatabaseField
    private Integer astAway;
    @DatabaseField
    private Integer rebAway;

    public Game() {
    }

    public Game(int gameID, int homeTeamID, int visitorTeamID, int season, int ptsHome, double fgPctHome, double ftPctHome, double fg3PctHome, int astHome, int rebHome, int ptsAway, double fgPctAway, double ftPctAway, double fg3PctAway, int astAway, int rebAway) {
        this.gameID = gameID;
        this.homeTeamID = homeTeamID;
        this.visitorTeamID = visitorTeamID;
        this.season = season;
        this.ptsHome = ptsHome;
        this.fgPctHome = fgPctHome;
        this.ftPctHome = ftPctHome;
        this.fg3PctHome = fg3PctHome;
        this.astHome = astHome;
        this.rebHome = rebHome;
        this.ptsAway = ptsAway;
        this.fgPctAway = fgPctAway;
        this.ftPctAway = ftPctAway;
        this.fg3PctAway = fg3PctAway;
        this.astAway = astAway;
        this.rebAway = rebAway;
    }

    public Integer getSeason() {
        return season;
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
        Game g = (Game) o;
        return gameID.equals(g.gameID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID);
    }
}

