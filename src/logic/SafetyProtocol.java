package logic;
import models.FactoryEquipment;
import java.util.function.*;;
public class SafetyProtocol<T extends FactoryEquipment> {
    private String protocolName;
    private Predicate<T> condition;
    private Consumer<T> action;
    public SafetyProtocol(String protocolName, Predicate<T> condition, Consumer<T> action){
        this.protocolName = protocolName;
        this.condition = condition;
        this.action = action;
    }
    public String getProtocolName(){return protocolName;}
    public void apply(T equipment){
        if(condition.test(equipment)){
            action.accept(equipment);
        }
    }
}
