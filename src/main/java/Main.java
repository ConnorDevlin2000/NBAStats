import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Game;
import model.GameStat;
import model.Player;
import model.Team;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static Dao getGameORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, Game.class);
        return DaoManager.createDao(connectionSource, Game.class);
    }

    private static Dao getPlayerORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, Player.class);
        return DaoManager.createDao(connectionSource, Player.class);
    }

    private static Dao getGameStatORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, GameStat.class);
        return DaoManager.createDao(connectionSource, GameStat.class);
    }

    private static Dao getTeamORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, Team.class);
        return DaoManager.createDao(connectionSource, Team.class);
    }

    public static void main(String[] args) {

        final int PORT_NUM = 7000;
        Spark.port(PORT_NUM);

        try{
            Scanner s = new Scanner(new File("src/main/java/archive/teams.csv"));
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] toCreate = line.split(",");
                final String URI = "jdbc:sqlite:./JBApp.db";
                ConnectionSource connectionSource = new JdbcConnectionSource(URI);
                Dao<Team,Integer> teamDao = DaoManager.createDao(connectionSource, Team.class);
                Team t = new Team(Integer.parseInt(toCreate[1]), toCreate[4], toCreate[5], Integer.parseInt(toCreate[6]), toCreate[7], toCreate[8]);
                teamDao.create(t);
            }
        } catch (Exception e) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.err.println("You're fucked!");
        }



        Spark.get("/", (req, res) -> {
            List<Game> ls = getGameORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("games", ls);
            return new ModelAndView(model, "public/games.vm");
        }, new VelocityTemplateEngine());

        Spark.get("/players", (req, res) -> {
            List<Game> ls = getPlayerORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("players", ls);
            return new ModelAndView(model, "public/players.vm");
        }, new VelocityTemplateEngine());

        Spark.get("/teams", (req, res) -> {
            List<Game> ls = getTeamORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("teams", ls);
            return new ModelAndView(model, "public/teams.vm");
        }, new VelocityTemplateEngine());

        Spark.get("/gamestats", (req, res) -> {
            List<GameStat> ls = getGameStatORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("gamestats", ls);
            return new ModelAndView(model, "public/gamestats.vm");
        }, new VelocityTemplateEngine());

    }
}
