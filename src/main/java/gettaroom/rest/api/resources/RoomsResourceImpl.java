package gettaroom.rest.api.resources;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by golanet on 25/01/2017.
 */
public class RoomsResourceImpl implements RoomsResource {
    /** Application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    @Override
    public Response getRooms(@Context HttpHeaders headers) {
        String accessToken = headers.getRequestHeader("access-token").get(0);

        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

        Calendar calendar = new Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        try {
            List<CalendarListEntry> items = calendar.calendarList().list().execute().getItems();
            List<String> calendars = items.stream().map(CalendarListEntry::getDescription).collect(Collectors.toList());

            return Response.ok().entity(calendars).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

    }
}
