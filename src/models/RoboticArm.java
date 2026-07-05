package models;

public class RoboticArm extends FactoryEquipment {
    private double currentPayload;
    public RoboticArm(String id, String name, boolean status, double temperature, double currentPayload){
        super(id, name, status, temperature);
        this.currentPayload = currentPayload;
    }
    public void setCurrentPayLoad(double currentPayload){this.currentPayload = currentPayload;}
    public double getCurrentPayLoad(){return currentPayload;}
    @Override
    public String getDetails(){
        return "\nID:" + getId() + "\nName: " + getName() + "\nStatus" + (getStatus() ? "On" : "Off") + "\nTemperature: " + getTemperature() + "\nТекущая удерживаемая нагрузка в кг: " + getCurrentPayLoad();   
    }
}
