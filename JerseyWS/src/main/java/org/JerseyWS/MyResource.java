package org.JerseyWS;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getIt() {
		return new Message();
	}
        
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void receiveMessage(Message message){
            System.out.println(message);
        }


}
