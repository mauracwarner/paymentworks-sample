package com.mwarner;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @Inject
    private MBTAService mbtaService;

    @Get
    public HttpResponse<Map<String, Object>> index() {
        String message = "Welcome to my PaymentWorks coding challenge sample app!  Please see the README for a " +
                "list of available endpoints.";
        return HttpResponse.ok(jsonResponse(message));
    }

    @Get("/lines")
    public HttpResponse listLines() {
        try {
            List<MBTARouteDTO> lines = mbtaService.listLines();
            return HttpResponse.ok(lines);
        } catch (Exception e) {
            String message = "An unexpected error has occurred: " + e.getMessage();
            LOG.error(message, e);
            return HttpResponse.serverError(jsonResponse(message));
        }
    }

    @Get("/stops")
    public HttpResponse listStops(@QueryValue @NotNull String line) {
        try {
            List<MBTAStopDTO> stops = mbtaService.listStopsForLine(line);
            if (stops == null || stops.isEmpty()) {
                return HttpResponse.notFound(jsonResponse("Not Found"));
            } else {
                return HttpResponse.ok(stops);
            }
        } catch (Exception e) {
            String message = "An unexpected error has occurred: " + e.getMessage();
            LOG.error(message, e);
            return HttpResponse.serverError(jsonResponse(message));
        }
    }

    private static Map<String, Object> jsonResponse(String message) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", message);
        return resp;
    }
}
