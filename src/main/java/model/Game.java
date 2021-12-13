package model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Objects;

public class Game {
    @DatabaseField
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
    private Integer fgPctHome;
    @DatabaseField
    private Integer ftPctHome;
    @DatabaseField
    private Integer fg3PctHome;
    @DatabaseField
    private Integer astHome;
    @DatabaseField
    private Integer rebHome;
    @DatabaseField
    private Integer ptsAway;
    @DatabaseField
    private Integer fgPctAway;
    @DatabaseField
    private Integer ftPctAway;
    @DatabaseField
    private Integer fg3PctAway;
    @DatabaseField
    private Integer astAway;
    @DatabaseField
    private Integer rebAway;

    public Game() {
    }

    public Game(int gameID, int homeTeamID, int visitorTeamID, int season, int ptsHome, int fgPctHome, int ftPctHome,
            int fg3PctHome, int astHome, int rebHome, int ptsAway, int fgPctAway, int ftPctAway, int fg3PctAway,
            int astAway, int rebAway) {
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

    public Integer getGameID() {
        return gameID;
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
