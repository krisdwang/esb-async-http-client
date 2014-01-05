package com.primeton.tip.endpoint.ws.async.handlers.pool.impl;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.jboss.netty.channel.ChannelHandler;

public class OutboundChannelHandlerFactory extends BasePoolableObjectFactory {

	private Class<? extends ChannelHandler> clazz;
	
	public OutboundChannelHandlerFactory(Class<? extends ChannelHandler> clazz){
		this.clazz = clazz;
	}
	
	public Object makeObject() throws Exception {
		return this.clazz.newInstance();
	}

}
