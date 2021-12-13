import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Employer;
import model.Job;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Dao getEmployerORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, Employer.class);
        return DaoManager.createDao(connectionSource, Employer.class);
    }

    private static Dao getJobORMLiteDao() throws SQLException {
        final String URI = "jdbc:sqlite:./JBApp.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(URI);
        TableUtils.createTableIfNotExists(connectionSource, Job.class);
        return DaoManager.createDao(connectionSource, Job.class);
    }


    public static void main(String[] args) {

        final int PORT_NUM = 7000;
        Spark.port(PORT_NUM);



        Spark.get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (req.cookie("username") != null)
                model.put("username", req.cookie("username"));
            // Solution Code starts
            if (req.cookie("color") != null)
                model.put("color", req.cookie("color"));
            // Solution Code ends
            return new ModelAndView(model, "public/index.vm");
        }, new VelocityTemplateEngine());

        Spark.post("/", (req, res) -> {
            String username = req.queryParams("username");
            String color = req.queryParams("color");
            res.cookie("username", username);
            // Solution Code starts
            res.cookie("color", color);
            // Solution Code ends
            res.redirect("/");
            return null;
        });

        Spark.get("/employers", (req, res) -> {
            List<Employer> ls = getEmployerORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("employers", ls);
            return new ModelAndView(model, "public/employers.vm");
        }, new VelocityTemplateEngine());

        Spark.post("/employers", (req, res) -> {
            String name = req.queryParams("name");
            String sector = req.queryParams("sector");
            String summary = req.queryParams("summary");
            Employer em = new Employer(name, sector, summary);
            getEmployerORMLiteDao().create(em);
            res.status(201);
            res.type("application/json");
            return new Gson().toJson(em.toString());
        });

        Spark.get("/addemployers", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "public/addemployers.vm");
        }, new VelocityTemplateEngine());

        // SOLUTION CODE STARTS
        Spark.get("/jobs", (req, res) -> {
            List<Job> ls = getJobORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("jobs", ls);
            return new ModelAndView(model, "public/jobs.vm");
        }, new VelocityTemplateEngine());

        Spark.get("/addjobs", (req, res) -> {
            List<Employer> ls = getEmployerORMLiteDao().queryForAll();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("employers", ls);
            return new ModelAndView(model, "public/addjobs.vm");
        }, new VelocityTemplateEngine());

        Spark.post("/jobs", (req, res) -> {
            String title = req.queryParams("title");
            Date datePosted = new SimpleDateFormat("yyyy-MM-dd").parse(req.queryParams("datePosted"));
            Date deadline = new SimpleDateFormat("yyyy-MM-dd").parse(req.queryParams("deadline"));
            String location = req.queryParams("location");
            String domain = req.queryParams("domain");
            boolean fulltime = req.queryParams("fulltime").equalsIgnoreCase("yes") ? true : false;
            boolean salaryBased = req.queryParams("salaryBased").equalsIgnoreCase("Salary") ? true : false;
            System.out.println(req.queryParams("payAmount"));
            int payAmount = Integer.parseInt(req.queryParams("payAmount"));
            String requirements = req.queryParams("requirements");
            String employerName = req.queryParams("employer");
            Employer employer = (Employer) getEmployerORMLiteDao().queryForEq("name", employerName).get(0);

            Job job = new Job(title, datePosted, deadline, domain, location, fulltime,
                            salaryBased, requirements, payAmount, employer);

            getJobORMLiteDao().create(job);
            res.status(201);
            res.type("application/json");
            return new Gson().toJson(job.toString());
        });

        Spark.get("/search", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "public/search.vm");
        }, new VelocityTemplateEngine());

        Spark.post("/search", (req, res) -> {

            String keywords = req.queryParams("keywords");
            System.out.println(keywords);
            String keywordsLike = "%" + keywords +"%";
            /* Search jobs table */
            QueryBuilder<Job, Integer> queryBuilderJobs = getJobORMLiteDao().queryBuilder();
            Where<Job, Integer> whereJobs = queryBuilderJobs.where();

            whereJobs.like("title", keywordsLike).or().like("domain", keywordsLike);

            PreparedQuery<Job> preparedQueryJobs = queryBuilderJobs.prepare();
            List<Job> jobs = getJobORMLiteDao().query(preparedQueryJobs);

            /* search employers table */
            QueryBuilder<Job, Integer> queryBuilderEmployers = getEmployerORMLiteDao().queryBuilder();
            Where<Job, Integer> whereEmployers = queryBuilderEmployers.where();
            whereEmployers.like("name", keywords);
            PreparedQuery<Job> preparedQueryEmployers = queryBuilderEmployers.prepare();
            List<Employer> employers = getEmployerORMLiteDao().query(preparedQueryEmployers);
            if (!employers.isEmpty()) {
                Integer id = employers.get(0).getId();
                List jobs2 = getJobORMLiteDao().queryForEq("employer_id", id);
                jobs.addAll(jobs2);
            }

            // set response code and return the JSON
            res.status(200);
            res.type("application/json");
            return new Gson().toJson(jobs.toString());

        });
        // SOLUTION CODE ENDS

    }
}
