package com.sensepost.log4jpwn;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static spark.Spark.*;

public class App {
    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {

        port(8080);

        get("/*", (req, res) -> {

            String ua = req.headers("User-Agent");
            String q = req.queryParams("q");
            String pth = req.pathInfo();

            System.out.println("logging pth: " + pth);

            // trigger
            logger.error(ua);
            logger.error(q);
            logger.error(pth);

            res.type("text/html");
            return "<!DOCTYPE html> <html> <head> <title>Log4jpwn</title> </head> <body> <h1>Log4jpwn</h1> <p>This site records the following</p> <ul> <li>User-Agent</li> <li>Path</li> <li>Value of parameter q</li> <li>Post body</li> </ul> </body> </html>";
        });
	
	post("/*", (req, res) -> {

            String ua = req.headers("User-Agent");
            String body = req.body();
            String pth = req.pathInfo();

            System.out.println("logging pth: " + pth);

            // trigger
            logger.error(ua);
            logger.error(body);
            logger.error(pth);

            res.type("text/html");
            return "<!DOCTYPE html> <html> <head> <title>Log4jpwn</title> </head> <body> <h1>Log4jpwn</h1> <p>This site records the following</p> <ul> <li>User-Agent</li> <li>Path</li> <li>Value of parameter q</li> <li>Post body</li> </ul> </body> </html>";
        });
    }
}
