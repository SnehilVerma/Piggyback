package com.fivetwenty.piggyback.controller;

import com.fivetwenty.piggyback.model.PickupDrop;
import com.fivetwenty.piggyback.service.RidePairing;
import org.apache.logging.log4j.message.Message;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@CrossOrigin("http://localhost:3000")
public class PiggyController {

    //TODO: need to check which concrete object to assign to ExecutorService interface.
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @GetMapping("/")
    public String index(){
        return "Application OK";
    }

    @PostMapping("/queryRoute")
    public String getRoutes(@RequestBody PickupDrop pickupDrop){
        System.out.println(pickupDrop.getPickup() + " " + pickupDrop.getDrop());
        return pickupDrop.toString();
    }

    //TODO: right now the code is working via a get endpoint from React EventSource.
    // Need to figure out how would a post mapping be possible from EventSource on React else we won't be
    // able to securely send ride params.
    @GetMapping("/requestRide")
    /**
     * <p>
     *     Sample endpoint to test Server side events.
     *     What it's supposed to do - When the client calls this endpoint, the server sets up a
     *     unidirectional channel with the client. The server can send updates to this connected client,
     *     and when it's done it will close the connection.
     *     Important thing to note : Unlike WebSockets which are bidirectional, here the connection is
     *     unidirection from server -> client ( which called this endpoint ).
     * </p>
     */
    public SseEmitter requestRide(){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(()->System.out.println("SseEmitted completed"));
        sseEmitter.onTimeout(()->System.out.println("SseEmitted completed"));
        sseEmitter.onError((ex)->System.out.println("error " + ex));
        executor.execute(()->{
            for(int i=0;i<15;i++){
                try{
                    sseEmitter.send(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
                    Thread.sleep(1000);    //sleep for 1 sec.
                }catch (Exception ex){
                    ex.printStackTrace();
                    sseEmitter.completeWithError(ex);
                }
            }
            sseEmitter.complete();
        });
        System.out.println("exiting controller");
        return sseEmitter;

    }


    @GetMapping("/matches")
    public SseEmitter matches(){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(()->System.out.println("SseEmitted completed"));
        sseEmitter.onTimeout(()->System.out.println("SseEmitted completed"));
        sseEmitter.onError((ex)->System.out.println("error " + ex));
        return sseEmitter;
    }

}
