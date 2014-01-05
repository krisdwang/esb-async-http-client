package com.primeton.tip.endpoint.ws.async.handlers.factory;

import java.lang.reflect.Constructor;

import org.jboss.netty.channel.ChannelHandler;

import com.primeton.tip.endpoint.ws.async.handlers.ChannelHandlerFactory;

public class OutboundChannelHandlerFactory implements ChannelHandlerFactory {

	private ChannelHandlerFactory factory;

	public OutboundChannelHandlerFactory(String name,
			Class<? extends ChannelHandler> handlerClass,
			Class<? extends ChannelHandlerFactory> factoryClass) {
		super();
		Constructor<? extends ChannelHandlerFactory> constructor = null;
		try{
			constructor = factoryClass.getConstructor(String.class, Class.class);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		if(constructor != null) {
			try{
				factory = constructor.newInstance(name, handlerClass);
			} catch (Exception ex){
				throw new RuntimeException("Unable to create handler factory", ex);
			}
		} else {
			try{
				constructor = factoryClass.getConstructor(Class.class);
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
			if(constructor != null){
				try{
					factory = constructor.newInstance(name);
				} catch (Exception ex){
					new RuntimeException("Unable to create handler factory", ex);
				}
			} else {
				try{
					factory = factoryClass.newInstance();
				} catch (Exception ex){
					new RuntimeException("Unable to create handler factory", ex);
				}
			}
		}
	}

	public ChannelHandler makeChannelHandler() {
		return factory.makeChannelHandler();
	}

	public void returnChannelHandler(ChannelHandler handler) {
		factory.returnChannelHandler(handler);
	}

}
