package main;
import java.util.*;
import java.util.stream.*;
import logic.*;
import models.ConveyorBelt;
import models.FactoryEquipment;
import models.HydraulicPress;
import models.RoboticArm;
public class Main {
    static Scanner scan = new Scanner(System.in);
    static FactoryManager manager = new FactoryManager();
    public static void main(String[] args) {
        int choice;
        do { 
            System.out.println("1. Добавить новое оборудование в цех");
            System.out.println("2. Запустить/остановить оборудование");
            System.out.println("3. Добавить базовый протокол «Сброс фантомного нагрева»");
            System.out.println("4. Добавить протокол контроля в систему");
            System.out.println("5. Запустить проверку всех добавленных протоколов");
            System.out.println("6. Выполнить аналитический запрос");
            System.out.println("0. Выход из программы");
            System.out.println("Ваш выбор: ");
            choice = readInt();
            switch(choice){
                case 1 -> addEquipment();
                case 2 -> toogleEquipment();
                case 3 -> PhantomProtocol();
                case 4 -> addVariant6();
                case 5 -> manager.applyAllProtocols();
                case 6 -> analytics();
                case 0 -> System.out.println("\nДо свидания");
            }
        } while (choice != 0);
    }

    static void addEquipment(){
        System.out.println("Выберите тип: 1 = Роботизированная рука, 2 = Конвейерная лента, 3 = Гидравлический пресс");
        int type = readInt();
        System.out.println("ID: "); String id = scan.nextLine().trim();
        System.out.println("Name: "); String name = scan.nextLine().trim();
        System.out.println("Название цеха: "); String workshop = scan.nextLine().trim();
        System.out.println("Status (1 = on, 0 = off)"); boolean status = readInt() == 1;
        System.out.println("Temperature: "); double Temperature = readDouble();
        switch(type){
            case 1 ->{
                System.out.println("Current Payload: ");
                double currentPayLoad = readDouble();
                manager.addEquipment(workshop, new RoboticArm(id, name, status, Temperature, currentPayLoad));
            }
            case 2 ->{
                System.out.println("Speed: ");
                double Speed = readDouble();
                manager.addEquipment(workshop, new ConveyorBelt(id, name, status, Temperature, Speed));
            }
            case 3 -> {
                System.out.println("Pressure: ");
                double Pressure = readDouble();
                manager.addEquipment(workshop, new HydraulicPress(id, name, status, Temperature, Pressure));
            }
            default -> System.out.println("Неверный выбор");
        }
    }

    static void toogleEquipment(){
        manager.printAll();
        System.out.print("ID: ");
        FactoryEquipment f = manager.getREquipmentById(scan.nextLine().trim());
        System.out.println("Оборудование сейчас: " + (f.isWorking() ? "работает" : "не работает"));
        System.out.println("1 = вкл, 0 = выкл: ");
        if (readInt() == 1){System.out.println("Включено"); f.startWork();}
        else{System.out.println("Выключено"); f.stopWork();}
    }

    static void PhantomProtocol(){
        var condition = (java.util.function.Predicate<FactoryEquipment>)
        c -> !c.isWorking() && c.getTemperature()>20;
        var action = (java.util.function.Consumer<FactoryEquipment>) c -> {
            System.out.println("[Сброс температуры]" + c.getName() + " в " + c.getTemperature() + " -> 20");
            c.setTemperature(20);
        };
        manager.addProtocol(new SafetyProtocol<>("Сброс температуры", condition, action));
    }

    static void addVariant6(){
        var condition = (java.util.function.Predicate<ConveyorBelt>)
        c -> !c.isWorking() && c.getName().contains("Main");
        var action = (java.util.function.Consumer<ConveyorBelt>) c -> {
            System.out.println("[Включение конвейеров]" + c.getName());
            c.startWork();
        };
        manager.addProtocol(new SafetyProtocol<>("Профилактика конвейеров", condition, action));
    }
    static void analytics(){
        List<String> shopStopWorking = manager.getWorkShops().entrySet().stream()
            .filter(entry -> entry.getValue().stream().anyMatch(equipment -> !equipment.isWorking()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        if (shopStopWorking.isEmpty()) {
            System.out.println("Все цеха работают");
        } else {
            System.out.println("Цеха с остановленным оборудованием: " + String.join(", ", shopStopWorking));
        }
    }

    static int readInt(){
        while(!scan.hasNextInt()){scan.next();}
        int v = scan.nextInt();
        scan.nextLine();
        return v;
    }
    static double readDouble(){
        while(!scan.hasNextDouble()){scan.next();}
        double v = scan.nextDouble();
        scan.nextLine();
        return v;
    }

}
