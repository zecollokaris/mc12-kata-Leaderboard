import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App{
    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        Map<String, Object> model = new HashMap<String, Object>();
        Dataquery dq=new Dataquery();
        get("/", (request, response) -> {
            model.put("template", "templates/index.vtl");
            model.put("boards",dq.getAll());
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/addBoard",(req,res)->{
            board bd=new board();
            bd.setFname(req.queryParams("fname"));
            bd.setLname(req.queryParams("lname"));
            bd.setKata(req.queryParams("kata"));
            bd.setLink(req.queryParams("link"));
            bd.setSpeed(req.queryParams("speed"));
            bd.save();
            res.redirect("/");
            return new ModelAndView(model,layout);
        },new VelocityTemplateEngine());
    }
}