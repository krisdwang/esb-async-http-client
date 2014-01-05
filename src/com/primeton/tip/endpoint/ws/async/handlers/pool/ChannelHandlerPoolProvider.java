package com.primeton.tip.endpoint.ws.async.handlers.pool;

import org.jboss.netty.channel.ChannelHandler;

public interface ChannelHandlerPoolProvider {
	ChannelHandlerPool createPool(String name,
			Class<? extends ChannelHandler> handlerClass,
			ChannelHandlerPooling pooling);
}
