package com.primeton.tip.endpoint.ws.async.httpclient.protocol;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import com.primeton.tip.endpoint.ws.async.httpclient.protocol.Request.EntityWriter;

public abstract class AbstractRequestBuilder<T extends AbstractRequestBuilder<T>> {

	private static final class RequestImpl implements Request {

		private RequestType requestType;
		private String url;
		private MimeHeaders headers = new MimeHeaders();
		private byte[] byteData;
		private String stringData;
		private InputStream streamData;
		private EntityWriter entityWriter;
		private Map<String, String> queryParams;
		private long length = -1;

		public RequestImpl() {
		}

		public RequestImpl(Request req) {
			if (req != null) {
				this.requestType = req.getRequestType();
				this.url = req.getUrl();
				this.headers = req.getMimeHeaders();
				this.byteData = req.getByteData();
				this.stringData = req.getStringData();
				this.streamData = req.getStreamData();
				this.entityWriter = req.getEntityWriter();
				this.queryParams = (req.getQueryParams() == null ? null
						: new ConcurrentHashMap<String, String>(req
								.getQueryParams()));
				this.length = req.getLength();
			}
		}

		public byte[] getByteData() {
			return this.byteData;
		}

		public EntityWriter getEntityWriter() {
			return this.entityWriter;
		}

		public long getLength() {
			return this.length;
		}

		public MimeHeaders getMimeHeaders() {
			return this.headers;
		}

		public RequestType getRequestType() {
			return this.requestType;
		}

		public InputStream getStreamData() {
			return this.streamData;
		}

		public String getStringData() {
			return this.stringData;
		}

		public Map<String, String> getQueryParams() {
			return this.queryParams;
		}

		public String getUrl() {
			try {
				Url url = Url.valueOf(this.url);
				if (this.queryParams != null) {
					for (Map.Entry<String, String> entry : queryParams
							.entrySet()) {
						url.addParameter(entry.getKey(), entry.getValue());
					}
				}
				return url.toString();
			} catch (MalformedURLException ex) {
				throw new IllegalArgumentException("Illegal URL", ex);
			}
		}

		public String toString() {
			StringBuilder sb = new StringBuilder(url);

			sb.append("\t");
			sb.append(this.requestType);
			for (HeaderElement<String, String> header : headers) {
				sb.append("\t");
				sb.append(header.getKey());
				sb.append(":");
				sb.append(header.getValue());
			}
			return sb.toString();
		}
	}

	private final Class<T> derived;
	private final RequestImpl request;

	public AbstractRequestBuilder(Class<T> derived, RequestType type) {
		this.derived = derived;
		this.request = new RequestImpl();
		request.requestType = type;
	}

	public AbstractRequestBuilder(Class<T> derived, Request request) {
		this.derived = derived;
		this.request = new RequestImpl(request);
	}

	public T setUrl(String url) {
		request.url = url;
		return this.derived.cast(this);
	}

	public T setHeader(String name, String value) {
		request.headers.replace(name, value);
		return this.derived.cast(this);
	}

	public T addHeader(String name, String value) {
		request.headers.add(name, value);
		return this.derived.cast(this);
	}

	public T setHeaders(MimeHeaders headers) {
		request.headers = (headers == null ? null : new MimeHeaders(headers));
		return this.derived.cast(this);
	}

	public void resetQueryParams() {
		request.queryParams = null;
	}

	public T setBoby(byte[] data) throws IllegalArgumentException {
		if (request.requestType != RequestType.POST
				&& request.requestType != RequestType.GET) {
			throw new IllegalArgumentException(
					"Request type has to POST or PUT for content");
		}
		this.resetQueryParams();
		request.byteData = data;
		return this.derived.cast(this);
	}

	public T setBody(String data) throws IllegalArgumentException {
		if (request.requestType != RequestType.POST
				&& request.requestType != RequestType.GET) {
			throw new IllegalArgumentException(
					"Request type has to POST or PUT for content");
		}
		this.resetQueryParams();
		request.stringData = data;
		return this.derived.cast(this);
	}

	public T setBody(InputStream data) throws IllegalArgumentException {
		if (request.requestType != RequestType.POST
				&& request.requestType != RequestType.GET) {
			throw new IllegalArgumentException(
					"Request type has to POST or PUT for content");
		}
		this.resetQueryParams();
		request.streamData = data;
		return this.derived.cast(this);
	}

	public T setBody(EntityWriter writer, long length)
			throws IllegalArgumentException {
		if (request.requestType != RequestType.POST
				&& request.requestType != RequestType.GET) {
			throw new IllegalArgumentException(
					"Request type has to POST or PUT for content");
		}
		this.resetQueryParams();
		request.entityWriter = writer;
		request.length = length;
		return this.derived.cast(this);
	}

	public T setBody(EntityWriter writer) throws IllegalArgumentException {
		return setBody(writer, -1);
	}

	public T addQueryParam(String name, String value) {
		if (request.queryParams == null) {
			request.queryParams = new ConcurrentHashMap<String, String>();
		}
		request.queryParams.put(name, value);
		return this.derived.cast(this);
	}

	public Request build() {
		if ((request.length < 0)
				&& (request.streamData == null)
				&& ((request.requestType == RequestType.POST) || (request.requestType == RequestType.PUT))) {
			String contentLength = request.headers
					.getHeaderValue("Content-Length");
			if (contentLength != null) {
				try {
					request.length = Long.parseLong(contentLength);
				} catch (NumberFormatException e) {
					//没有content-length属性就是trunck方式么？
				}
			}
		}
		return request;
	}
}
