package com.jaxrs.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path(value = "/injectdata")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectData {

	@GET
	@Path("annotation")
	//Matrix param URL is like /webapi/injectdata/annotation;param=value
	public String getParamsUsingAnnotation(@MatrixParam("param") String matrixparam,
							 @HeaderParam("header1") String headerparam,
							 @CookieParam("name") String cookieName){
		StringBuffer string = new StringBuffer();
		string.append("Matrix Param is: ");
		string.append(matrixparam);
		string.append(" and ");
		string.append("Header Param is: ");
		string.append(headerparam);
		string.append(" and ");
		string.append("Cookie Name is: ");
		string.append(cookieName);
		return string.toString();
	}
	
	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo,
									   @Context HttpHeaders headerInfos){
		System.out.println("URI Info: "+uriInfo.getAbsolutePath().getPath());
		System.out.println("URI Info: "+uriInfo.getAbsolutePath().getPort());
		List<PathSegment> pathSegments = uriInfo.getPathSegments();
		for(PathSegment segment : pathSegments){
			System.out.println("Segment: "+segment.getPath());
		}
		//System.out.println("URI Info: "+uriInfo.getPathSegments());
		System.out.println("URI Info: "+uriInfo.getPathParameters());
		System.out.println("Header Info: "+headerInfos.getMediaType());
		System.out.println("Header Info: "+headerInfos.getRequestHeaders());
		return "test";
	}
	
}
