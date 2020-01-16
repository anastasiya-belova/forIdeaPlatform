/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import IO.JsonParser;
import ch.qos.logback.classic.util.ContextInitializer;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.time.format.DateTimeParseException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import statistics.Selector;

/**
 *
 * @author Anastasiya Belova
 */
public class Main {

    static{
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "logback.xml");
    }
    static Logger logger = LoggerFactory.getLogger(Main.class);
    
    static DecimalFormat twoDecimalPlaces = new DecimalFormat("#0.00");
    
    public static void main(String[] args) {
        run(args);
    }
    
    public static void run(String[] args){
        JsonParser jsonParser = JsonParser.getInstance();
        if (args.length > 0) {
            jsonParser.setFile(args[0]);
        } else {
            jsonParser.setFile("tickets.json");
        }
        List<Ticket> tickets;
        try {
            tickets = jsonParser.readJson();
        } catch (IOException e) {
            logger.error("Program execution stopped because an error occured: " + e.getMessage());
            return;
        } catch (JsonSyntaxException | NullPointerException e) {
            logger.error("Program execution stopped because an error occured: " + e.toString());
            return;
        }
        try {
            DescriptiveStatistics stat = new DescriptiveStatistics(Selector.getArrForStatistic(tickets, "Tel-Aviv", "Vladivostok"));
            logger.info("Average flight time: " + twoDecimalPlaces.format(stat.getMean()) + " minutes");
            logger.info("90-percentile of flight time: " + twoDecimalPlaces.format(stat.getPercentile(90)) + " minutes");
        } catch (ArithmeticException e) {
            logger.error("The departure time later than the time of landing.");
        } catch (DateTimeParseException e) {
            logger.error("The time was entered in the wrong format in the original data.");
        }
    }
}
