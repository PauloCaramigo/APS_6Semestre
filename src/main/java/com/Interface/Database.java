package com.Interface;

import javafx.beans.property.SimpleStringProperty;

public class Database {
    private SimpleStringProperty state;
    private SimpleStringProperty agro1;
    private SimpleStringProperty agro2;
    private SimpleStringProperty agro3;
    private SimpleStringProperty agro4;
    private SimpleStringProperty others;
    private SimpleStringProperty total;

    public Database(String state, String agro1, String agro2, String agro3, String agro4, String others, String total) {
        this.state = new SimpleStringProperty(state);
        this.agro1 = new SimpleStringProperty(agro1);
        this.agro2 = new SimpleStringProperty(agro2);
        this.agro3 = new SimpleStringProperty(agro3);
        this.agro4 = new SimpleStringProperty(agro4);
        this.others = new SimpleStringProperty(others);
        this.total = new SimpleStringProperty(total);
    }
    
    public String getState() {return state.get();}
    public void setState(String state) {this.state = new SimpleStringProperty(state);}

    public String getAgro1() {return agro1.get();}
    public void setAgro1(String agro1) {this.agro1 = new SimpleStringProperty(agro1);}   
    
    public String getAgro2() {return agro2.get();}
    public void setAgro2(String agro2) {this.agro2 = new SimpleStringProperty(agro2);}

    public String getAgro3() {return agro3.get();}
    public void setAgro3(String agro3) {this.agro3 = new SimpleStringProperty(agro3);}

    public String getAgro4() {return agro4.get();}
    public void setAgro4(String agro4) {this.agro4 = new SimpleStringProperty(agro4);}

    public String getOthers() {return others.get();}
    public void setOthers(String others) {this.others = new SimpleStringProperty(others);}

    public String getTotal() {return total.get();}
    public void setTotal(String total) {this.total = new SimpleStringProperty(total);} 

}
