package com.fivetwenty.piggyback.controller;

import com.fivetwenty.piggyback.model.PickupDrop;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class PiggyController {

    @GetMapping("/")
    public String index(){
        return "Application OK";
    }

    @PostMapping("/queryRoute")
    public String getRoutes(@RequestBody PickupDrop pickupDrop){
        System.out.println(pickupDrop.getPickup() + " " + pickupDrop.getDrop());
        return pickupDrop.toString();
    }

}
