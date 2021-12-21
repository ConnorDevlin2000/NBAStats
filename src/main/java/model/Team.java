package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "teams")
public class Team {
    @DatabaseField(id = true)
    private Integer teamID;
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

    public Team() {

    }

    public Team(int teamID, String abbreviation, String nickname, int yearFounded,
            String city, String arena) {
        this.teamID = teamID;
        this.abbreviation = abbreviation;
        this.nickname = nickname;
        this.yearFounded = yearFounded;
        this.city = city;
        this.arena = arena;
    }

    public Integer getTeamID() {
        return teamID;
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

    @Override
    public String toString() {
        return nickname;
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
