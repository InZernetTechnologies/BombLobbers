package com.inzernettechnologies.bomblobbers.Database;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private static Connection connection;

    public static void openConnection() {
        Bukkit.getScheduler().runTaskAsynchronously(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    connection = DriverManager.getConnection("jdbc:"
                                    + com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.type") + "://"
                                    + com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.host") + ":"
                                    + com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.port") + "/"
                                    + com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.database"),
                            com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.username"),
                            com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.password"));

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static ResultSet getBlocks() {
        ResultSet blockSet = null;
        try {
            PreparedStatement lBlock = connection.prepareStatement("SELECT * FROM `bl_regen`");
            blockSet = lBlock.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return blockSet;
    }

    public synchronized static void logError(Exception e) {
        Bukkit.getScheduler().runTaskAsynchronously(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    connection.prepareStatement("INSERT INTO `bl_log`(`type`, `message`) VALUES ('stacktrace', '" + "hi" + "')").executeQuery();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public synchronized static void closeConnection() {
        Bukkit.getScheduler().runTaskAsynchronously(com.inzernettechnologies.bomblobbers.main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public synchronized Connection getConnection() {
        return connection;
    }

}
