package com.primeton.tip.endpoint.ws.async.handlers.pool.impl;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;
import org.jboss.netty.channel.ChannelHandler;

import com.primeton.tip.endpoint.ws.async.handlers.pool.ChannelHandlerPool;
import com.primeton.tip.endpoint.ws.async.handlers.pool.ChannelHandlerPooling;

public class OutboundChannelHandlerPool implements ChannelHandlerPool {

	private ObjectPool pool;

	public OutboundChannelHandlerPool(String name,
			Class<? extends ChannelHandler> handlerClass,
			ChannelHandlerPooling pooling) {
		super();
		this.pool = new StackObjectPool(new OutboundChannelHandlerFactory(
				handlerClass), pooling.max(), pooling.initial());
	}

	public ChannelHandler borrowChannelHandler() throws Exception {
		return (ChannelHandler)pool.borrowObject();
	}

	public void returnChannelHandler(ChannelHandler handler) throws Exception {
		this.pool.returnObject(handler);
	}

}
