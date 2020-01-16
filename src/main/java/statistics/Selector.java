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
    
    public static double[] getArrForStatistic(List<Ticket> tickets, String selectParameter1, String selectParameter2) throws ArithmeticException{
        List<Long> listOfFlightTime = new ArrayList<>();//time in minutes
        for(Ticket next : tickets){
            if(selectParameter1.equals(next.getFrom()) && selectParameter2.equals(next.getTo())
                    || selectParameter2.equals(next.getFrom()) && selectParameter1.equals(next.getTo())){
                long flightTime = next.getTimeOfDeparture().until(next.getTimeOfArrival(), MINUTES);
                if (flightTime <= 0){
                    throw new ArithmeticException();
                }
                listOfFlightTime.add(flightTime);
            }
        }
        double[] arrOfFlightTime = new double[listOfFlightTime.size()];
        int count = 0;
        for (Long next : listOfFlightTime){
            arrOfFlightTime[count] = next.doubleValue();
            count++;
        }
        return arrOfFlightTime;
    }
}
