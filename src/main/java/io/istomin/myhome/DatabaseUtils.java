package io.istomin.myhome;

import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class DatabaseUtils {

    private static final Logger logger = LoggerFactory.getLogger("myhome/DB");

    public static void getConnection(ConfigurationNode config) {
        Connection myCon = null;

        // This shows we have gotten the configuration variables we need to make a SQL connection from
        // the main class. Note that we don't actually do anything to connect to the database here,
        // and we don't want to return a null Connection, so this method has been made to return a void
        // instead. If you want to actually attach a mysql DB, change the method to
        // public static Connection getConnection(ConfigurationNode config) above, and return "myCon"
        // instead of just return.

        logger.info("[MyHome] I got the following variables from my parent class: " + config.getChildrenMap());

        return;
    }
}
