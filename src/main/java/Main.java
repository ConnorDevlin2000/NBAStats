import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        // TableUtils.createTableIfNotExists(connectionSource, Game.class);
        return DaoManager.createDao(connectionSource, Game.class);
    }

    private static Dao getPlayerORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // TableUtils.createTableIfNotExists(connectionSource, Player.class);
        return DaoManager.createDao(connectionSource, Player.class);
    }

    private static Dao getGameStatORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // TableUtils.createTableIfNotExists(connectionSource, GameStat.class);
        return DaoManager.createDao(connectionSource, GameStat.class);
    }

    private static Dao getTeamORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // TableUtils.createTableIfNotExists(connectionSource, Team.class);
        return DaoManager.createDao(connectionSource, Team.class);
    }

    public static void main(String[] args) {

        final int PORT_NUM = 7000;
        Spark.port(PORT_NUM);
        Spark.staticFiles.location("/public");

        // try{
        // // Scanner s = new Scanner(new File("src/main/java/archive/teams.csv"));
        // // while(s.hasNextLine()){
        // // String line = s.nextLine();
        // // String[] toCreate = line.split(",");
        // // final String URI = "jdbc:sqlite:./JBApp.db";
        // // ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // // Dao<Team,Integer> teamDao = DaoManager.createDao(connectionSource,
        // Team.class);
        // // Team t = new Team(Integer.parseInt(toCreate[1]), toCreate[4], toCreate[5],
        // Integer.parseInt(toCreate[6]), toCreate[7], toCreate[8]);
        // // teamDao.create(t);
        // // }

        // // Scanner s = new Scanner(new File("src/main/java/archive/players.csv"));
        // // for(int i = 0; i < 5000; i++){
        // // s.nextLine();
        // // }
        // // int counter = 1;
        // // while(s.hasNextLine()){
        // // System.out.println("Step: " + counter++);
        // // String line = s.nextLine();
        // // String[] toCreate = line.split(",");
        // // System.out.println(line);
        // // final String URI = "jdbc:sqlite:./JBApp.db";
        // // ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // //// TableUtils.dropTable(connectionSource, Player.class, true);
        // //// TableUtils.createTableIfNotExists(connectionSource, Player.class);
        // // Dao<Player,Integer> playerDao = DaoManager.createDao(connectionSource,
        // Player.class);
        // // Team t = (Team) getTeamORMLiteDao().queryForEq("teamID",
        // Integer.parseInt(toCreate[1])).get(0);
        // // Player p = new Player(toCreate[0], t, Integer.parseInt(toCreate[2]),
        // Integer.parseInt(toCreate[3]));
        // // playerDao.createIfNotExists(p);
        // // }
        // final String URI = "jdbc:sqlite:./JBApp.db";
        // ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        // Scanner s = new Scanner(new File("src/main/java/archive/games_details.csv"));
        // s.nextLine();
        // int counter = 1;
        // for(int i = 0; i < 148000; i++){
        // s.nextLine();
        // counter++;
        // }
        // while(s.hasNextLine()){
        // System.out.println("Step: " + counter++);
        // String line = s.nextLine();
        // String[] toCreate = line.split(",");
        // System.out.println("Length: " + toCreate.length);
        // // System.out.println(line);
        // // TableUtils.dropTable(connectionSource, Player.class, true);
        // // TableUtils.createTableIfNotExists(connectionSource, Player.class);

        // ArrayList<Team> t = (ArrayList<Team>)
        // getTeamORMLiteDao().queryForEq("teamID", Integer.parseInt(toCreate[1]));
        // ArrayList<Player> p = (ArrayList<Player>)
        // getPlayerORMLiteDao().queryForEq("playerID", Integer.parseInt(toCreate[4]));
        // ArrayList<Game> g = (ArrayList<Game>)
        // getGameORMLiteDao().queryForEq("gameID", Integer.parseInt(toCreate[0]));

        // Dao<GameStat,Integer> gameStatDao = DaoManager.createDao(connectionSource,
        // GameStat.class);
        // if(toCreate[8].length() > 0 || t.isEmpty() || p.isEmpty() || g.isEmpty()){
        // continue;
        // }

        // if(toCreate.length == 29) {
        // GameStat gs = new GameStat(g.get(0), t.get(0), p.get(0), (int)
        // Double.parseDouble(toCreate[10]), (int) Double.parseDouble(toCreate[11]),
        // Double.parseDouble(toCreate[12]), (int) Double.parseDouble(toCreate[13]),
        // (int) Double.parseDouble(toCreate[14]), Double.parseDouble(toCreate[15]),
        // (int) Double.parseDouble(toCreate[16]), (int)
        // Double.parseDouble(toCreate[17]), Double.parseDouble(toCreate[18]), (int)
        // Double.parseDouble(toCreate[19]), (int) Double.parseDouble(toCreate[20]),
        // (int) Double.parseDouble(toCreate[21]), (int)
        // Double.parseDouble(toCreate[22]), (int) Double.parseDouble(toCreate[23]),
        // (int) Double.parseDouble(toCreate[24]), (int)
        // Double.parseDouble(toCreate[26]), (int) Double.parseDouble(toCreate[27]),
        // (int) Double.parseDouble(toCreate[28]));
        // gameStatDao.createIfNotExists(gs);
        // } else {
        // GameStat gs = new GameStat(g.get(0), t.get(0), p.get(0), (int)
        // Double.parseDouble(toCreate[10]), (int) Double.parseDouble(toCreate[11]),
        // Double.parseDouble(toCreate[12]), (int) Double.parseDouble(toCreate[13]),
        // (int) Double.parseDouble(toCreate[14]), Double.parseDouble(toCreate[15]),
        // (int) Double.parseDouble(toCreate[16]), (int)
        // Double.parseDouble(toCreate[17]), Double.parseDouble(toCreate[18]), (int)
        // Double.parseDouble(toCreate[19]), (int) Double.parseDouble(toCreate[20]),
        // (int) Double.parseDouble(toCreate[21]), (int)
        // Double.parseDouble(toCreate[22]), (int) Double.parseDouble(toCreate[23]),
        // (int) Double.parseDouble(toCreate[24]), (int)
        // Double.parseDouble(toCreate[26]), (int) Double.parseDouble(toCreate[27]), 0);
        // gameStatDao.createIfNotExists(gs);
        // }

        // }
        // } catch (Exception e) {
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // System.err.println("You're fucked!");
        // System.err.println(e.toString());
        // }

        Spark.get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "public/landing.vm");
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

        Spark.get("/queryselector", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "public/queryselector.vm");
        }, new VelocityTemplateEngine());

        Spark.post("/queryselector", (req, res) -> {
            String select = req.queryParams("field1");
            String attributes = req.queryParams("attributes");
            String operator = req.queryParams("operator");
            String value = String.valueOf(req.queryParams("value"));

            Dao<GameStat, Integer> gameStatDao = getGameStatORMLiteDao();
            QueryBuilder<GameStat, Integer> query = gameStatDao.queryBuilder();
            if (select.equals("player")) {
                if (operator.equals(">")) {
                    query.distinct().selectColumns("player_id").where().gt(attributes, Integer.parseInt(value));
                } else if (operator.equals(">=")) {
                    query.distinct().selectColumns("player_id").where().ge(attributes, Integer.parseInt(value));
                } else if (operator.equals("<")) {
                    query.distinct().selectColumns("player_id").where().lt(attributes, Integer.parseInt(value));
                } else if (operator.equals("<=")) {
                    query.distinct().selectColumns("player_id").where().le(attributes, Integer.parseInt(value));
                } else {
                    query.distinct().selectColumns("player_id").where().eq(attributes, Integer.parseInt(value));
                }
            } else if (select.equals("team")) {
                Dao<Game, Integer> gameDao = getGameORMLiteDao();
                QueryBuilder<Game, Integer> query2 = gameDao.queryBuilder();
                query2.groupBy("homeTeam_id").having("AVG(" + attributes.replaceAll("_", "") + "home" + ") " + operator + value);
                PreparedQuery<Game> preparedQuery2 = query2.prepare();
                List<Game> result2 = gameDao.query(preparedQuery2);
                return result2;
            } else {

            }

            PreparedQuery<GameStat> preparedQuery = query.prepare();
            List<GameStat> result = gameStatDao.query(preparedQuery);
            return result;
        });

    }
}
