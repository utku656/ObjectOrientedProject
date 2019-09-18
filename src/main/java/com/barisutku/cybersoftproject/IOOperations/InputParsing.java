package com.barisutku.cybersoftproject.IOOperations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.barisutku.cybersoftproject.Constants.StringConstants;
import com.barisutku.cybersoftproject.models.*;
import org.springframework.stereotype.Component;


@Component
public class InputParsing {

    public Hero getHero(String input) {

        Hero hero = new Hero();
        hero.setHealthPower(getHealthPower("Hero", input));
        hero.setAttackPoints(getAttackPoints("Hero", input));

        return hero;
    }

    public List<Position> getRoute(String input) {

        List<Position> routeList = new ArrayList<Position>();
        Pattern routePat = Pattern.compile(StringConstants.position);
        Matcher routeMatcher = routePat.matcher(input);

        while(routeMatcher.find()) {
            String group = routeMatcher.group();

            Pattern enemyTypePattern = Pattern.compile("[a-z,A-Z]+");
            Matcher enemyTypeMatcher = enemyTypePattern.matcher(group);
            if(enemyTypeMatcher.find()) {
                String enemyType = enemyTypeMatcher.group();

                Pattern positionPattern = Pattern.compile("[0-9]+");
                Matcher positionMatcher = positionPattern.matcher(group);

                if(positionMatcher.find()) {
                    int position = Integer.valueOf(positionMatcher.group());
                    Position route = new Position(getEnemy(enemyType, input), position);
                    routeList.add(route);
                }
            }
        }


        Resources resource = getResource(input);
        Iterator<Position> iterator = routeList.iterator();
        while(iterator.hasNext()) {
            Position route = iterator.next();
            if(route.getPosition() > resource.getDistance()) {
                iterator.remove();
            }
        }

        if(!routeList.isEmpty()) {
            routeList.sort(new PositionComparator());
        }

        return routeList;
    }

    private Resources getResource(String input) {
        Pattern resourcesPat = Pattern.compile(StringConstants.resources);
        Matcher resourceMatcher = resourcesPat.matcher(input);

        while(resourceMatcher.find()) {
            String resourceMatch = resourceMatcher.group();
            Pattern metersPattern = Pattern.compile("[0-9]+");
            Matcher metersMatchMatcher = metersPattern.matcher(resourceMatch);
            if(metersMatchMatcher.find()) {
                int metersMatch = Integer.valueOf(metersMatchMatcher.group());
                return new Resources(metersMatch);
            }
        }
        return new Resources(0);
    }

    private Enemy getEnemy(String type, String input) {

        Pattern pattern = Pattern.compile(StringConstants.enemy);
        Matcher matcher = pattern.matcher(input);

        Enemy enemy = null;
        while(matcher.find()) {

            String match = matcher.group();
            Pattern typePattern = Pattern.compile( type + " is Enemy");
            Matcher typeMatcher = typePattern.matcher(match);
            if(typeMatcher.find()) {
                enemy = new Enemy(type);
                enemy.setHealthPower(getHealthPower(type, input));
                enemy.setAttackPoints(getAttackPoints(type, input));
            }
        }

        return enemy;

    }

    private int getAttackPoints(String type, String input) {
        Pattern apPattern = Pattern.compile(type +" " + StringConstants.attackPoints);
        Matcher apMatcher = apPattern.matcher(input);
        if(apMatcher.find()) {
            String apMatch = input.substring(apMatcher.start(), apMatcher.end());
            Pattern apNumeric = Pattern.compile("[0-9]+");
            Matcher apNumMatcher = apNumeric.matcher(apMatch);
            if(apNumMatcher.find()) {
                int ap = Integer.valueOf(apMatch.substring(apNumMatcher.start(), apNumMatcher.end()));
                return ap;
            }
        }
        return 0;
    }

    private int getHealthPower(String type, String input) {
        Pattern hpPattern = Pattern.compile(type + " " + StringConstants.health);
        Matcher hpMatcher = hpPattern.matcher(input);
        if(hpMatcher.find()) {
            String hpMatch = input.substring(hpMatcher.start(), hpMatcher.end());
            Pattern hpNumeric = Pattern.compile("[0-9]+");
            Matcher hpnumMatcher = hpNumeric.matcher(hpMatch);
            if(hpnumMatcher.find()) {
                int hp = Integer.valueOf(hpMatch.substring(hpnumMatcher.start(), hpnumMatcher.end()));
                return hp;
            }
        }
        return 0;
    }

}
