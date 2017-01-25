package gettaroom.rest.api.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by golanet on 25/01/2017.
 */
public class RoomsResourceImpl implements RoomsResource {

    @Override
    public Response getRooms(@Context HttpHeaders headers) {
        String userToken = headers.getRequestHeader("user-token").get(0);
        return Response.ok().entity(userToken).build();
    }
}
