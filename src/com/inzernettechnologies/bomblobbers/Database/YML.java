package com.inzernettechnologies.bomblobbers.Database;

public class YML {

    private com.inzernettechnologies.bomblobbers.main main;

    public void YML(com.inzernettechnologies.bomblobbers.main main){
        this.main = main;
    }

    public String getConfig(String config){
        return main.getConfig().getString(config);
    }

    public void openConnection(){

    }

}
