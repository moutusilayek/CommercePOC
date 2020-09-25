package com.example.applicationinsights;

import com.microsoft.applicationinsights.TelemetryClient;

import com.microsoft.applicationinsights.telemetry.Duration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controler {

    @Autowired
    TelemetryClient telemetryClient;

    Logger logger= LoggerFactory.getLogger(Controler.class);

    @GetMapping("/hello")
    public String hello() {

        //track a custom event
        logger.info("in controller method");
        telemetryClient.trackEvent("tracking event...");

        //trace a custom trace
        telemetryClient.trackTrace("tracing hello method....");

        //track a custom metric
        HashMap map=new HashMap<String,String>();
        map.put("name", "mou");
        telemetryClient.trackMetric("new metric", 1.0 , 2 , 3, 4,map );

        //track a custom dependency
      //  telemetryClient.trackDependency("SQL", "Insert", new Duration(0, 0, 1, 1, 1), true);

        return "hello";
    }
}
