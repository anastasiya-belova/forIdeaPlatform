/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import static java.time.temporal.ChronoUnit.*;
import java.util.ArrayList;
import java.util.List;
import main.Ticket;

/**
 *
 * @author Anastasiya Belova
 */
public class Selector {
    
    public Selector(){}
    
    /**
     * Selects the flight time between two points from the ticket list.
     * @param tickets - list of tickets
     * @param selectParameter1 - first point
     * @param selectParameter2 - second point
     * @return an array of flight times
     * @throws ArithmeticException 
     */
    public static double[] getArrForStatistic(List<Ticket> tickets, String selectParameter1, String selectParameter2) throws ArithmeticException{
        
        double[] arrOfFlightTime = tickets.stream()
                .filter((next) -> (selectParameter1.equals(next.getFrom()) && selectParameter2.equals(next.getTo())
                || selectParameter2.equals(next.getFrom()) && selectParameter1.equals(next.getTo())))
                .map((next) -> next.getTimeOfDeparture().until(next.getTimeOfArrival(), MINUTES))
                .map((flightTime) -> {
                    if (flightTime <= 0){
                        throw new ArithmeticException();
                    }
                    return flightTime;
                })
                .mapToDouble(flightTime -> flightTime)
                .toArray();
                
        return arrOfFlightTime;
    }
}
