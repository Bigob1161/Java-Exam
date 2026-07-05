package models;

public class ConveyorBelt extends FactoryEquipment{
    private double speed;
    public ConveyorBelt(String id, String name, boolean status, double temperature, double speed){
        super(id, name, status, temperature);
        this.speed = speed;
    }
    public void setSpeed(double speed){this.speed = speed;}
    public double getSpeed(){return speed;}
    @Override
    public String getDetails(){
        return "\nID:" + getId() + "\nName: " + getName() + "\nStatus" + (getStatus() ? "On" : "Off") + "\nTemperature: " + getTemperature() + "\nСкорость: " + getSpeed();   
    }
}
