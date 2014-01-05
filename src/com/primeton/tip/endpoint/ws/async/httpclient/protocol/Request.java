package com.primeton.tip.endpoint.ws.async.httpclient.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface Request {
	
	public static interface EntityWriter {
        public void writeEntity(OutputStream out) throws IOException;
    }
	
	public RequestType getRequestType();
	
	public String getUrl();
	
	public MimeHeaders getMimeHeaders();
	
	public byte[] getByteData();
	
	public String getStringData();
	
	public InputStream getStreamData();
	
	public EntityWriter getEntityWriter();
	
	public long getLength();
		
	public Map<String, String> getQueryParams();
}
