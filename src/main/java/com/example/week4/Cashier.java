package com.example.week4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {

    @RequestMapping(value = "/getChange/{money}", method = RequestMethod.GET)
    public Change getChang(@PathVariable("money") int money){
        Change c = new Change();

        while (money >= 1000){
            money -= 1000;
            c.setB1000(c.getB1000() + 1);
        }

        while (money >= 500){
            money -= 500;
            c.setB500(c.getB500() + 1);
        }

        while (money >= 100){
            money -= 100;
            c.setB100(c.getB100() + 1);
        }

        while (money >= 20){
            money -= 20;
            c.setB20(c.getB20() + 1);
        }

        while (money >= 10){
            money -= 10;
            c.setB10(c.getB10() + 1);
        }

        while (money >= 5){
            money -= 5;
            c.setB5(c.getB5() + 1);
        }

        while (money >= 1){
            money -= 1;
            c.setB1(c.getB1() + 1);
        }

        return c;
    }


}
