package logic;
import java.util.*;
import java.util.stream.*;
import models.FactoryEquipment;;
public class FactoryManager {
    private Map<String, List<FactoryEquipment>> workshops = new HashMap<>();
    private List<SafetyProtocol<? extends FactoryEquipment>> protocols = new ArrayList<>();
    
    public void addEquipment(String workshopName, FactoryEquipment equipment){
        workshops.computeIfAbsent(workshopName, k -> new ArrayList<>()).add(equipment);
        System.out.println("Добавлен " + equipment.getName() + " в " + workshopName);
    }

    public void addProtocol(SafetyProtocol<?> protocol){
        protocols.add(protocol);
        System.out.println("Добавлен");
    }
    public FactoryEquipment getREquipmentById(String id){
        for (List<FactoryEquipment> list : workshops.values()){
            for (FactoryEquipment factoryEquipment : list){
                if (factoryEquipment.getId().equals(id)){return factoryEquipment;}
            }
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public void applyAllProtocols(){
        for (SafetyProtocol protocol : protocols){
            for (List<FactoryEquipment> list : workshops.values()){
                for (FactoryEquipment factoryEquipment : list){
                    try {protocol.apply(factoryEquipment);}
                    catch(ClassCastException e ){}
                }
            }
        }
        System.out.println("Готово");
    }
    public Stream<FactoryEquipment> getAnalyticsStream(){
        return workshops.values().stream().flatMap(List::stream);
    }
    public Map<String, List<FactoryEquipment>> getWorkShops(){return workshops;}
    public void printAll(){
        if(workshops.isEmpty()){
            System.out.println("Цех пустой");
            return;
        }
        for (var entry : workshops.entrySet()){
            System.out.println(entry.getKey());
            entry.getValue().forEach(f -> System.out.println(" " + f.getDetails()));
        }
    }
}
