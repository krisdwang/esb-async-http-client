package com.primeton.tip.endpoint.ws.async.httpclient.protocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MimeHeaders implements Iterable<HeaderElement<String, String>> {
	
	public static final String CONTENT_TYPE = "Content-Type";

	private List<HeaderElement<String, String>> headers = new ArrayList<HeaderElement<String, String>>();

	public MimeHeaders(){
		
	}
	
	public MimeHeaders(MimeHeaders mimeHeaders){
		if(mimeHeaders != null){
			for(HeaderElement<String, String> header: mimeHeaders){
				add(header);
			}
		}
	}
	
	public Iterator<HeaderElement<String, String>> iterator() {
        return headers.iterator();
    }
	

	public MimeHeaders add(HeaderElement<String, String> header) {
		this.headers.add(new HeaderElement<String, String>(header.getKey(), header
				.getValue()));
		return this;
	}
	
	public MimeHeaders add(String key, String value){
		this.headers.add(new HeaderElement<String, String>(key, value));
		return this;
	}
	
	public MimeHeaders addAll(MimeHeaders headers){
		for(HeaderElement<String, String> header : headers.headers){
			add(header);
		}
		return this;
	}
	
	public MimeHeaders addContentType(String contentType){
		return add(CONTENT_TYPE, contentType);
	}
	
	public MimeHeaders remove(String key){
		for(Iterator<HeaderElement<String, String>> iterator = this.headers.iterator(); iterator.hasNext();){
			HeaderElement<String, String> header = iterator.next();
			if(key.equalsIgnoreCase(header.getKey())){
				iterator.remove();
			}
		}
		return this;
	}
	
	public void replace(String key, String value){
		remove(key);
		add(key, value);
	}
	
	public String getHeaderValue(String key){
		for(HeaderElement<String, String> header : this){
			if(key.equalsIgnoreCase(header.getKey())){
				return header.getValue();
			}
		}
		return null;
	}
	
	public List<String> getHeaderValues(String key){
		List<String> values = new ArrayList<String>();
		for(HeaderElement<String, String> header: this){
			values.add(header.getValue());
		}
		return values;
	}
	
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(this.getClass() != o.getClass())
			return false;
		final MimeHeaders other = (MimeHeaders)o;
		if(headers == null){
			if(other != null)
				return false;					
		} else if(!headers.equals(other.headers))
			return false;
		return true;
	}

}
