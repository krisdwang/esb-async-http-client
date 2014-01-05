package com.primeton.tip.endpoint.ws.async.pipeline;

import org.jboss.netty.channel.ChannelPipeline;

public interface OutboundChannelPipelineListener {
	void pipelineCompleted(ChannelPipeline pipeline);
}
