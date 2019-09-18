package com.barisutku.cybersoftproject.IOOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IOFileOperations {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${inputFile}")
    private String inputFile;

    @Value("${outputFile}")
    private String outputFile;

    public String readInput(){

        StringBuilder buf = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){

            String currentLine;
            while((currentLine = br.readLine()) != null){
                buf.append(currentLine);
            }

        } catch(IOException e){
            LOG.error("Input File Exception");
        }
        return buf.toString();
    }

    public void writeOutput(String message) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))){
            bw.write(message);
            bw.newLine();
        } catch(IOException e){
            LOG.error("Output File Exception");
        }
    }
}
