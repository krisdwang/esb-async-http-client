package com.primeton.tip.endpoint.ws.async.httpclient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 异步http client的配置描述类, 异步http client暂时不支持如下http特性：
 * <p>
 * {@code} 1. 压缩
 * <p>
 * 2. SSL
 * <p>
 * 3. 重定向(redirect)
 * <p>
 * 4. 代理服务
 * <p>
 * 5. cookie
 * <p>
 * {@code}
 * 
 * 属性配置描述如下：
 * <p>
 * reaper: 将空闲的连接置为过期
 * <p>
 * clientThreadPool: 处理异步的response的线程池,见 {@link ExecutorService}
 * <p>
 * maxTotalConnections: 最大连接数
 * <p>
 * maxConnectionsPerHost: 每个host的最大连接数
 * <p>
 * idleConnectionTimeout:
 * <p>
 * requestTimeout:
 * <p>
 * keepAlive:
 * <p>
 * 
 * @author kris <br>
 * @version 0.1
 */
public class TipAsyncHttpClientConfiguration {

	private final static String ASYNC_CLIENT = TipAsyncHttpClientConfiguration.class
			.getName()
			+ ".";

	private int maxTotalConnections;
	private int maxConnectionsPerHost;
	private int connectionTimeout;
	private int idleConnectionTimeout; // millisecond
	private int requestTimeout; // millisecond
	private boolean keepAlive; // millisecond

	private String userAgent;

	private final ScheduledExecutorService reaper;
	private final ExecutorService clientThreadPool;

	public TipAsyncHttpClientConfiguration(int maxTotalConnections,
			int maxConnectionsPerHost, int connectionTimeout,
			int idleConnectionTimeout, int requestTimeout, boolean keepAlive,
			String userAgent, ScheduledExecutorService reaper,
			ExecutorService clientThreadPool) {
		super();
		this.maxTotalConnections = maxTotalConnections;
		this.maxConnectionsPerHost = maxConnectionsPerHost;
		this.connectionTimeout = connectionTimeout;
		this.idleConnectionTimeout = idleConnectionTimeout;
		this.requestTimeout = requestTimeout;
		this.keepAlive = keepAlive;
		this.userAgent = userAgent;

		if (reaper == null) {
			this.reaper = Executors
					.newSingleThreadScheduledExecutor(new ThreadFactory() {
						//@Override
						public Thread newThread(Runnable r) {
							return new Thread(r, "TIP-ASYNC-HTTPCLIENT");
						}
					});
		} else {
			this.reaper = reaper;
		}

		if (clientThreadPool == null) {
			this.clientThreadPool = Executors.newCachedThreadPool();
		} else {
			this.clientThreadPool = clientThreadPool;
		}
	}

	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public int getMaxConnectionsPerHost() {
		return maxConnectionsPerHost;
	}

	public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getIdleConnectionTimeout() {
		return idleConnectionTimeout;
	}

	public void setIdleConnectionTimeout(int idleConnectionTimeout) {
		this.idleConnectionTimeout = idleConnectionTimeout;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public ScheduledExecutorService getReaper() {
		return reaper;
	}

	public ExecutorService getClientThreadPool() {
		return clientThreadPool;
	}

	public static class AsyncClientConfigBuilder {
		private int defaultMaxTotalConnections = Integer.getInteger(
				ASYNC_CLIENT + "defaultMaxTotalConnections", 2000);
		private int defaultMaxConnectionsPerHost = Integer.getInteger(
				ASYNC_CLIENT + "defaultMaxConnectionsPerHost", 1000);
		private int defaultConnectionTimeout = Integer.getInteger(ASYNC_CLIENT
				+ "", 60 * 1000);
		private int defaultIdleConnectionTimeout = Integer.getInteger(
				ASYNC_CLIENT + "defaultIdleConnectionTimeout", 60 * 1000);
		private int defaultRequestTimeout = Integer.getInteger(ASYNC_CLIENT
				+ "defaultRequestTimeout", 60 * 1000);
		private String userAgent = System.getProperty(ASYNC_CLIENT
				+ "userAgent", "TIP HTTP CLIENT/0.1");
		private boolean keepAlive = true;

		private ScheduledExecutorService reaper = Executors
				.newScheduledThreadPool(Runtime.getRuntime()
						.availableProcessors());
		private ExecutorService clientThreadPool = Executors
				.newCachedThreadPool();

		public AsyncClientConfigBuilder() {
		}

		public AsyncClientConfigBuilder setMaxTotalConnections(
				int defaultMaxTotalConnections) {
			this.defaultMaxTotalConnections = defaultMaxTotalConnections;
			return this;
		}

		public AsyncClientConfigBuilder setMaxConnectionsPerHost(
				int defaultMaxConnectionsPerHost) {
			this.defaultMaxConnectionsPerHost = defaultMaxConnectionsPerHost;
			return this;
		}

		public AsyncClientConfigBuilder setConnectionTimeout(
				int defaultConnectionTimeout) {
			this.defaultConnectionTimeout = defaultConnectionTimeout;
			return this;
		}

		public AsyncClientConfigBuilder setIdleConnectionTimeout(
				int defaultIdleConnectionTimeout) {
			this.defaultIdleConnectionTimeout = defaultIdleConnectionTimeout;
			return this;
		}

		public AsyncClientConfigBuilder setRequestTimeout(
				int defaultRequestTimeout) {
			this.defaultRequestTimeout = defaultRequestTimeout;
			return this;
		}

		public AsyncClientConfigBuilder setUserAgent(String userAgent) {
			this.userAgent = userAgent;
			return this;
		}

		public AsyncClientConfigBuilder setKeepAlive(boolean keepAlive) {
			this.keepAlive = keepAlive;
			return this;
		}

		public AsyncClientConfigBuilder setReaper(
				ScheduledExecutorService reaper) {
			this.reaper = reaper;
			return this;
		}

		public AsyncClientConfigBuilder setClientThreadPool(
				ExecutorService clientThreadPool) {
			this.clientThreadPool = clientThreadPool;
			return this;
		}

		public TipAsyncHttpClientConfiguration build() {
			return new TipAsyncHttpClientConfiguration(
					defaultMaxTotalConnections, defaultMaxConnectionsPerHost,
					defaultConnectionTimeout, defaultIdleConnectionTimeout,
					defaultRequestTimeout, keepAlive, userAgent, reaper,
					clientThreadPool);
		}
	}
}
