package com.example.applicationinsights

import com.microsoft.applicationinsights.TelemetryClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class WelcomeController {
    @Autowired
    var telemetryClient: TelemetryClient=TelemetryClient()
    var logger = LoggerFactory.getLogger(WelcomeController::class.java)

    @RequestMapping("/welcome")
    fun checkLogging():String{

        // check if logs getting tracked in application insights
        logger.info("in controller method of kotlin")

        //track a custom event
        telemetryClient.trackEvent("tracking event in Kotlin class...")
        telemetryClient.trackTrace("tracing hello method in KOtlin....")

        //track a custom metric
        val map: HashMap<String, String> = java.util.HashMap<String, String>()
        map.put("name", "insights")
        telemetryClient.trackMetric("new metric in kotlin", 1.0, 2, 3.0, 4.0, map)


        return "welcome.. logging complete"
    }

}