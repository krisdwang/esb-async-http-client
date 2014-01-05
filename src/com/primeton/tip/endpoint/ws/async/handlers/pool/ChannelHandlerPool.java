package com.primeton.tip.endpoint.ws.async.handlers.pool;

import org.jboss.netty.channel.ChannelHandler;

public interface ChannelHandlerPool {
	ChannelHandler borrowChannelHandler() throws Exception;
	void returnChannelHandler(ChannelHandler handler) throws Exception;
}
