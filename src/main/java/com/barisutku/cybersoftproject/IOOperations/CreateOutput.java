package com.barisutku.cybersoftproject.IOOperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOutput {

    @Autowired
    private IOFileOperations fileOperation;

    public void writeMessage(String message) {

        fileOperation.writeOutput(message);
    }
}
