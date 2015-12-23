package com.inzernettechnologies.bomblobbers.Database;

public class main {

    private database databaseType;

    ;

    public void main() {
        databaseType = database.valueOf(com.inzernettechnologies.bomblobbers.main.instance.getConfig().getString("database.type"));
    }

    public String getConfig(String config) {
        if (databaseType == database.YML) {
            YML yml = new YML();
            return yml.getConfig(config);
        } else {
            return null;
        }
    }

    private enum database {YML, MySQL}

}
