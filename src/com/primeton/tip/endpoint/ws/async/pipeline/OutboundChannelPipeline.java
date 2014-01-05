package com.primeton.tip.endpoint.ws.async.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.DefaultChannelPipeline;

/**
 * Customized Netty Pipeline
 * @author kris
 */
public class OutboundChannelPipeline extends DefaultChannelPipeline {
	
	private List<OutboundChannelPipelineListener> pipeLineListeners;
	
	public OutboundChannelPipeline(){
		super();
	}
	
	public void attach(Channel channel, ChannelSink sink){
		channel.getCloseFuture().addListener(new ChannelFutureListener(){
			public void operationComplete(ChannelFuture future)
					throws Exception {
				pipelineCompleted();
			}						
		});
		super.attach(channel, sink);
	}
	
	public void addPipelineListener(OutboundChannelPipelineListener listener){
		if(this.pipeLineListeners == null){
			this.pipeLineListeners = new ArrayList<OutboundChannelPipelineListener>();
		}
		this.pipeLineListeners.add(listener);
	}
	
	protected void pipelineCompleted() {
		if(this.pipeLineListeners != null){
			for(OutboundChannelPipelineListener listener: pipeLineListeners){
				listener.pipelineCompleted(this);
			}
		}
	}
}
