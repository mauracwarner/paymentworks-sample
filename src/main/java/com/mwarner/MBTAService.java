package com.mwarner;

import com.github.jasminb.jsonapi.ResourceConverter;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MBTAService {

    private static final String MBTA_URI = "https://api-v3.mbta.com";

    @Client(MBTA_URI)
    @Inject
    private RxHttpClient http;

    List<MBTARouteDTO> listLines() {
        String getLinesUri = "/routes?type=0,1";
        String json = http.toBlocking().retrieve(getLinesUri);
        ResourceConverter converter = new ResourceConverter(MBTARouteDTO.class);
        return converter.readDocumentCollection(json.getBytes(), MBTARouteDTO.class).get();
    }

    List<MBTAStopDTO> listStopsForLine(String lineId) {
        String getStopsUri = "/stops?route=" + lineId.trim();
        String json = http.toBlocking().retrieve(getStopsUri);
        ResourceConverter converter = new ResourceConverter(MBTAStopDTO.class);
        return converter.readDocumentCollection(json.getBytes(), MBTAStopDTO.class).get();
    }

}
