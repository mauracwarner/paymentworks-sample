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
        Map<String, Object> out = new HashMap<>();
        out.put("message", "Welcome to my PaymentWorks coding challenge sample app!  Please see the README for a " +
                "list of available endpoints.");
        return HttpResponse.ok(out);
    }

    @Get("/lines")
    public HttpResponse<List<MBTARouteDTO>> listLines() {
        try {
            List<MBTARouteDTO> lines = mbtaService.listLines();
            return HttpResponse.ok(lines);
        } catch (Exception e) {
            String message = "An unexpected error has occurred: " + e.getMessage();
            LOG.error(message, e);
            return HttpResponse.serverError();
        }
    }

    @Get("/stops")
    public HttpResponse<List<MBTAStopDTO>> listStops(@QueryValue @NotNull String line) {
        try {
            List<MBTAStopDTO> stops = mbtaService.listStopsForLine(line);
            if (stops == null || stops.isEmpty()) {
                return HttpResponse.notFound();
            } else {
                return HttpResponse.ok(stops);
            }
        } catch (Exception e) {
            LOG.error("An unexpected error has occurred: " + e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }
}
