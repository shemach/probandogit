package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.koushik.javabrains.messenger.dao.MessageDAO;
import org.koushik.javabrains.messenger.dao.MessageEntity;
import org.koushik.javabrains.messenger.dao.SessionUtil;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.resources.beans.MessageFilterBean;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes (MediaType.APPLICATION_JSON)
@Produces (value ={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})

public class MessagesResources {

	MessageDAO messageDAO = new MessageDAO();
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("JSON");
		if (filterBean.getYear() > 0) {
			return messageDAO.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageDAO.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageDAO.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("XML");
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}*/

	@POST
	public Response addMessage(Message msj) {
		MessageEntity message= new MessageEntity();
		message.
		msj.setMessage(msj.getMessage());;
		msj.setAuthor(msj.getAuthor());
		msj.setCreated(msj.getCreated());
		return Response.ok().build();
	}
	
	/*
	 @PUT//VEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeR
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageDAO.updateMessage(id, message);
	}*/
	
	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") long id) {
		MessageDAO dao = new MessageDAO();
		int count = dao.deleteMessage(id);
		if(count==0){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {	
	return messageDAO.getMessage(id);	
	}
	
	
	@GET
    public List<Message> getMessage() {
        MessageDAO dao = new MessageDAO();
        List messages = dao.getAllMessages();
        return messages;
    }

	/*private String getUriForComments(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessagesResources.class)
	       		.path(MessagesResources.class, "getCommentResource")
	       		.path(CommentResource.class)
	       		.resolveTemplate("messageId", message.getId())
	            .build();
	    return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
       		 .path(ProfileResource.class)
       		 .path(message.getAuthor())
             .build();
        return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		 .path(MessagesResources.class)
		 .path(Long.toString(message.getId()))
		 .build()
		 .toString();
		return uri;
	}*/
	
	
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
}
