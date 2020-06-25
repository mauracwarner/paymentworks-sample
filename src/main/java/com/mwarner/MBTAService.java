package com.mwarner;

import com.github.jasminb.jsonapi.ResourceConverter;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * This service makes requests to the MBTA v3 API and parses the responses into DTOs.
 */
@Singleton
public class MBTAService {

    private static final String MBTA_URI = "https://api-v3.mbta.com";

    @Client(MBTA_URI)
    @Inject
    private RxHttpClient http;

    /**
     * List all light rail and subway lines on the MBTA.
     *
     * @return list of line IDs and names
     */
    List<MBTARouteDTO> listLines() {
        String getLinesUri = "/routes?type=0,1";
        String json = http.toBlocking().retrieve(getLinesUri);
        ResourceConverter converter = new ResourceConverter(MBTARouteDTO.class);
        return converter.readDocumentCollection(json.getBytes(), MBTARouteDTO.class).get();
    }

    /**
     * List all stops on a given MBTA line.
     *
     * @param lineId - ID of the line to list stops for, e.g. "Orange" (required)
     * @return list of stop names and IDs
     */
    List<MBTAStopDTO> listStopsForLine(String lineId) {
        String getStopsUri = "/stops?route=" + lineId.trim();
        String json = http.toBlocking().retrieve(getStopsUri);
        ResourceConverter converter = new ResourceConverter(MBTAStopDTO.class);
        return converter.readDocumentCollection(json.getBytes(), MBTAStopDTO.class).get();
    }

}
