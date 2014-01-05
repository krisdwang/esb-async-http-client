package com.primeton.tip.endpoint.ws.async.handlers;

import org.jboss.netty.channel.ChannelHandler;

public interface ChannelHandlerFactory {
	public ChannelHandler makeChannelHandler();
	public void returnChannelHandler(ChannelHandler handler);
}
