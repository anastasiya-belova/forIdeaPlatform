/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.time.OffsetDateTime;
/**
 *
 * @author Anastasiya Belova
 */
public class Ticket {
    private String from;
    private String to;
    private String timeOfDeparture;
    private String timeOfArrival;
    
    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return this.from;
    }
    public void setTo(String to){
        this.to = to;
    }
    public String getTo(){
        return this.to;
    }
    public void setTimeOfDeparture(String timeOfDeparture){
        this.timeOfDeparture = timeOfDeparture;
    }
    public OffsetDateTime getTimeOfDeparture(){
        return OffsetDateTime.parse(this.timeOfDeparture);
    }
    public void setTimeOfArrival(String timeOfArrival){
        this.timeOfArrival = timeOfArrival;
    }
    public OffsetDateTime getTimeOfArrival(){
        return OffsetDateTime.parse(this.timeOfArrival);
    }
}
