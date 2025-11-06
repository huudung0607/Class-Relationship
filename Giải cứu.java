import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;
interface Event{
    void execute(Prince prince);
}
class mushroomEvent implements Event{
    @Override
    public void execute(Prince prince){
        prince.setBlood(prince.getBlood() - 15);
        prince.setPower(prince.getPower() - 2);
        prince.checkStatus();
    }
}
class witchEvent implements Event{
    private final int witchPower;

    public witchEvent(int witchPower) {
        this.witchPower = witchPower;
    }
    @Override
    public void execute(Prince prince){
        if(witchPower >= prince.getPower()){
            prince.setAlive(false);
            prince.setPower(0);
            prince.setBlood(0);
        }
        else{
            prince.setPower(prince.getPower() + 5);
        }
        prince.checkStatus();
    }
}
class SoldierEvent implements Event{
    private final int soldierPower;

    public SoldierEvent(int soldierPower) {
        this.soldierPower = soldierPower;
    }
    @Override
    public void execute(Prince prince){
        if(soldierPower >= prince.getPower()){
            prince.setAlive(false);
            prince.setPower(0);
            prince.setBlood(0);
        }
        else{
            prince.setPower(prince.getPower() + 7);
            prince.setBlood(prince.getBlood() + 5);
        }
        prince.checkStatus();
    }
}
class PeaEvent implements Event{
    @Override
    public void execute(Prince prince) {
        prince.setBlood(prince.getBlood() + 10);
        prince.setPower(prince.getPower() + 2);
    }
}
class AliveState implements PrinceState{
    private final Prince prince;

    public AliveState(Prince prince) {
        this.prince = prince;
    }

    @Override
    public void handle(Event event) {
        event.execute(prince);
    }
}
class Prince{
    private int power,blood;
    private PrinceState aliveState;
    private PrinceState deadState;
    private PrinceState state;

    public Prince(int power, int blood) {
        this.power = power;
        this.blood = blood;
        this.aliveState = new AliveState(this);
        this.state = aliveState;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
    public boolean isAlive(){
        return state == aliveState;
    }
    public void setAlive(boolean isAlive){
        this.state = isAlive ? aliveState : deadState;
    }
    public void checkStatus(){
        if(this.power <= 0 || this.blood <= 0){
            setAlive(false);
        }
    }
    public void encouter(Event event){
        state.handle(event);
    }
    public void printStatus(){
        System.out.printf("POWER : %d\nBLOOD : %d\n%s\n--------------------\n",this.power,this.blood,isAlive() ? "ALIVE" : "DEAD");
    }
}
interface PrinceState{
    void handle(Event event);
}
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String []initialPowerLine = sc.nextLine().split(":");
        int initialPower = Integer.parseInt(initialPowerLine[1].trim());

        String []initialBloodLine = sc.nextLine().split(":");
        int initialBlood = Integer.parseInt(initialBloodLine[1].trim());
        sc.nextLine();

        Prince prince = new Prince(initialPower,initialBlood);
        int numberEvent = Integer.parseInt(sc.nextLine());
        Vector<Event> v = new Vector<>();
        for(int i = 0;i < numberEvent;i++) {
            String event = sc.nextLine();
            String[] eventParts = event.split(" ");
            Event event1 = null;
            switch (eventParts[0]) {
                case "mushroom":
                    event1 = new mushroomEvent();
                    break;
                case "pea":
                    event1 = new PeaEvent();
                    break;
                case "witch":
                    int witchpower = Integer.parseInt(eventParts[1]);
                    event1 = new witchEvent(witchpower);
                    break;
                case "soldier":
                    int soldierPower = Integer.parseInt(eventParts[1]);
                    event1 = new SoldierEvent(soldierPower);
                    break;
                default:
                    continue;
            }
            prince.encouter(event1);
            prince.printStatus();
            if (!prince.isAlive()) {
                for (int j = i + 1; j < numberEvent; j++) {
                    prince.printStatus();
                }
                break;
            }
        }
    }
}
