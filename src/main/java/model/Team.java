package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "teams")
public class Team {
    @DatabaseField(id = true)
    private Integer teamID;
    @DatabaseField
    private Integer minYear;
    @DatabaseField
    private Integer maxYear;
    @DatabaseField
    private String abbreviation;
    @DatabaseField
    private String nickname;
    @DatabaseField
    private Integer yearFounded;
    @DatabaseField
    private String city;
    @DatabaseField
    private String arena;
    @DatabaseField
    private Integer arenaCapacity;

    public Team(){

    }

    public Team(int teamID, int minYear, int maxYear, String abbreviation, String nickname, int yearFounded,
            String city, String arena, int arenaCapacity) {
        this.teamID = teamID;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.abbreviation = abbreviation;
        this.nickname = nickname;
        this.yearFounded = yearFounded;
        this.city = city;
        this.arena = arena;
        this.arenaCapacity = arenaCapacity;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public String getCity() {
        return city;
    }

    public String getArena() {
        return arena;
    }

    public Integer getArenaCapacity() {
        return arenaCapacity;
    }

    @Override
    public String toString() {
        return "Team ID: " + teamID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Team t = (Team) o;
        return teamID.equals(t.teamID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamID);
    }
}
