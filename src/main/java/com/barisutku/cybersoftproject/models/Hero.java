package com.barisutku.cybersoftproject.models;

import com.barisutku.cybersoftproject.IOOperations.CreateOutput;
import com.barisutku.cybersoftproject.Services.Attack;

import java.util.List;

public class Hero extends alive implements Attack {
    private boolean alives=true;

    private CreateOutput outputMessage;

    private StringBuilder output = new StringBuilder();

    public void start(List<Position> positions) {

        output.append("Hero started journey with "+ getHealthPower() +" HP! \n");

        for (Position position : positions) {
            if(isAlive()) attack(position);
        }

        if(getHealthPower() > 0 )
            output.append("Hero survived!");

        outputMessage.writeMessage(output.toString());

    }

    public void attack(Position p) {
        Enemy e = p.getEnemy();

        while(getHealthPower() > 0 && e.getHealthPower() >0) {
            setHealthPower(getHealthPower()-e.getAttackPoints());
            e.setHealthPower(e.getHealthPower()-getAttackPoints());
        }

        if(getHealthPower() > 0) {
            output.append("Hero defeated "+  e.getType() + " with " + getHealthPower()+ " HP remaining \n");
        }
        else {
            output.append(e.getType() + " defeated Hero"+ " with " + e.getHealthPower()+ " HP remaining \n");
            output.append("Hero is Dead!! Last seen at position "+ p.getPosition() +"!! \n");
            setAlive(false);
        }

    }

    public boolean isAlive() {
        return alives;
    }

    public void setAlive(boolean alives) {
        this.alives = alives;
    }

    public CreateOutput getOutput() {
        return outputMessage;
    }

    public void setOutput(CreateOutput outputMessage) {
        this.outputMessage = outputMessage;
    }


}