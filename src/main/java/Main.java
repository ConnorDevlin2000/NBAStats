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
//        TableUtils.createTableIfNotExists(connectionSource, Player.class);
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
//        TableUtils.createTableIfNotExists(connectionSource, Team.class);
        return DaoManager.createDao(connectionSource, Team.class);
    }

    public static void main(String[] args) {

        final int PORT_NUM = 7000;
        Spark.port(PORT_NUM);

        try{
//            Scanner s = new Scanner(new File("src/main/java/archive/teams.csv"));
//            while(s.hasNextLine()){
//                String line = s.nextLine();
//                String[] toCreate = line.split(",");
//                final String URI = "jdbc:sqlite:./JBApp.db";
//                ConnectionSource connectionSource = new JdbcConnectionSource(URI);
//                Dao<Team,Integer> teamDao = DaoManager.createDao(connectionSource, Team.class);
//                Team t = new Team(Integer.parseInt(toCreate[1]), toCreate[4], toCreate[5], Integer.parseInt(toCreate[6]), toCreate[7], toCreate[8]);
//                teamDao.create(t);
//            }

//            Scanner s = new Scanner(new File("src/main/java/archive/players.csv"));
//            for(int i = 0; i < 5000; i++){
//                s.nextLine();
//            }
//            int counter = 1;
//            while(s.hasNextLine()){
//                System.out.println("Step: " + counter++);
//                String line = s.nextLine();
//                String[] toCreate = line.split(",");
//                System.out.println(line);
//                final String URI = "jdbc:sqlite:./JBApp.db";
//                ConnectionSource connectionSource = new JdbcConnectionSource(URI);
////                TableUtils.dropTable(connectionSource, Player.class, true);
////                TableUtils.createTableIfNotExists(connectionSource, Player.class);
//                Dao<Player,Integer> playerDao = DaoManager.createDao(connectionSource, Player.class);
//                Team t = (Team) getTeamORMLiteDao().queryForEq("teamID", Integer.parseInt(toCreate[1])).get(0);
//                Player p = new Player(toCreate[0], t, Integer.parseInt(toCreate[2]), Integer.parseInt(toCreate[3]));
//                playerDao.createIfNotExists(p);
//            }

            Scanner s = new Scanner(new File("src/main/java/archive/games.csv"));
            int counter = 1;
            for(int i = 0; i < 24000; i++){
                s.nextLine();
                counter++;
            }
            while(s.hasNextLine()){
                System.out.println("Step: " + counter++);
                String line = s.nextLine();
                String[] toCreate = line.split(",");
                System.out.println(line);
                final String URI = "jdbc:sqlite:./JBApp.db";
                ConnectionSource connectionSource = new JdbcConnectionSource(URI);
//                TableUtils.dropTable(connectionSource, Player.class, true);
//                TableUtils.createTableIfNotExists(connectionSource, Player.class);
                Dao<Team,Integer> teamDao = DaoManager.createDao(connectionSource, Team.class);
                Team t1 = (Team) getTeamORMLiteDao().queryForEq("teamID", Integer.parseInt(toCreate[3])).get(0);
                Team t2 = (Team) getTeamORMLiteDao().queryForEq("teamID", Integer.parseInt(toCreate[4])).get(0);

                Dao<Game,Integer> gameDao = DaoManager.createDao(connectionSource, Game.class);
                Game g = new Game(Integer.parseInt(toCreate[1]), t1, t2, Integer.parseInt(toCreate[5]), (int) (Double.parseDouble(toCreate[7])), Double.parseDouble(toCreate[8]), Double.parseDouble(toCreate[9]), Double.parseDouble(toCreate[10]), (int) (Double.parseDouble(toCreate[11])), (int) (Double.parseDouble(toCreate[12])), (int) (Double.parseDouble(toCreate[14])), Double.parseDouble(toCreate[15]), Double.parseDouble(toCreate[16]), Double.parseDouble(toCreate[17]), (int) (Double.parseDouble(toCreate[18])), (int) (Double.parseDouble(toCreate[19])));
                gameDao.createIfNotExists(g);
            }
        } catch (Exception e) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.err.println("You're fucked!");
            System.err.println(e.toString());
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
