package models;
import interfaces.*;

public abstract class FactoryEquipment implements Operatable {
    protected String id;
    protected String name;
    protected boolean status;
    protected double temperature;
    public FactoryEquipment(String id, String name, boolean status, double temperature){
        this.id = id;
        this.name = name;
        this.status = status;
        this.temperature = temperature;
    }
    @Override
    public void startWork(){status = true;}
    @Override
    public void stopWork(){status = false;}
    @Override
    public boolean isWorking(){return status;}
    public void setId(String id){this.id = id;}
    public String getId(){return id;}
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setStatus(boolean status){this.status = status;}
    public boolean getStatus(){return status;}
    public void setTemperature(double temperature){this.temperature = temperature;}
    public double getTemperature(){return temperature;}
    public abstract String getDetails();
}
