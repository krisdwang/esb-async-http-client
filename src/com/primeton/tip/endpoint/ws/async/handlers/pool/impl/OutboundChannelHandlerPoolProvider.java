package com.primeton.tip.endpoint.ws.async.handlers.pool.impl;

import org.jboss.netty.channel.ChannelHandler;

import com.primeton.tip.endpoint.ws.async.handlers.pool.ChannelHandlerPool;
import com.primeton.tip.endpoint.ws.async.handlers.pool.ChannelHandlerPoolProvider;
import com.primeton.tip.endpoint.ws.async.handlers.pool.ChannelHandlerPooling;

public class OutboundChannelHandlerPoolProvider implements
		ChannelHandlerPoolProvider {

	public ChannelHandlerPool createPool(String name,
			Class<? extends ChannelHandler> handlerClass,
			ChannelHandlerPooling pooling) {
		return new OutboundChannelHandlerPool(name, handlerClass, pooling);
	}

}
