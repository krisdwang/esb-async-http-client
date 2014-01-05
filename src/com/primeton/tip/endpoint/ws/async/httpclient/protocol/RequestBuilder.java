package com.primeton.tip.endpoint.ws.async.httpclient.protocol;

import java.io.InputStream;
import com.primeton.tip.endpoint.ws.async.httpclient.protocol.Request.EntityWriter;


public class RequestBuilder extends AbstractRequestBuilder<RequestBuilder> {

	public RequestBuilder(RequestType type) {
		super(RequestBuilder.class, type);
	}

	public RequestBuilder(Request request){
		super(RequestBuilder.class, request);
	}
	
	public RequestBuilder addHeader(String name, String value) {
        return super.addHeader(name, value);
    }
	
	public RequestBuilder addQueryParam(String key, String value) throws IllegalArgumentException {
        return super.addQueryParam(key, value);
    }
	
	public RequestBuilder setBody(byte[] data) throws IllegalArgumentException {
        return super.setBoby(data);
    }
	
	public RequestBuilder setBody(EntityWriter dataWriter, long length) throws IllegalArgumentException {
        return super.setBody(dataWriter, length);
    }
	
	public RequestBuilder setBody(EntityWriter dataWriter) {
        return super.setBody(dataWriter);
    }
	
	public RequestBuilder setBody(InputStream stream) throws IllegalArgumentException {
        return super.setBody(stream);
    }

    public RequestBuilder setBody(String data) throws IllegalArgumentException {
        return super.setBody(data);
    }

    public RequestBuilder setHeader(String name, String value) {
        return super.setHeader(name, value);
    }

    public RequestBuilder setMimeHeaders(MimeHeaders headers) {
        return super.setHeaders(headers);
    }
	
    public RequestBuilder setUrl(String url) {
        return super.setUrl(url);
    }
}
