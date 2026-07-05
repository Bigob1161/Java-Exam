package models;

public class HydraulicPress extends FactoryEquipment {
    private double pressure;
    public HydraulicPress(String id, String name, boolean status, double temperature, double pressures){
        super(id, name, status, temperature);
        this.pressure = pressures;
    }
    public void setPressure(double pressure){this.pressure = pressure;}
    public double getPressure(){return pressure;}
    @Override
    public String getDetails(){
        return "\nID:" + getId() + "\nName: " + getName() + "\nStatus" + (getStatus() ? "On" : "Off") + "\nTemperature: " + getTemperature() + "\nДавление в МПа: " + getPressure();   
    }
}
