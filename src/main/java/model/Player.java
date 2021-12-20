package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "players")
public class Player {
    @DatabaseField
    private String playerName;
    @DatabaseField
    private Integer teamID;
    @DatabaseField(id = true)
    private Integer playerID;
    @DatabaseField
    private Integer season;

    public Player() {
    }

    public Player(String playerName, int teamID, int playerID, int season) {
        this.playerName = playerName;
        this.teamID = teamID;
        this.playerID = playerID;
        this.season = season;
    }

    public Integer getPlayerID() {
        return playerID;
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
        Player p = (Player) o;
        return playerID.equals(p.playerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID);
    }
}
