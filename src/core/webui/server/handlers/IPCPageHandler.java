package core.webui.server.handlers;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.nio.protocol.HttpAsyncExchange;
import org.apache.http.protocol.HttpContext;

import core.webcommon.HttpServerUtilities;
import core.webui.server.ResourceManager;

public class IPCPageHandler extends AbstractUIHttpHandler {
	public IPCPageHandler(ResourceManager resourceManager) {
		super(resourceManager);
	}

	@Override
	protected void handleWithBackend(HttpRequest request, HttpAsyncExchange exchange, HttpContext context)
			throws HttpException, IOException {
		String content = resourceManager.getResourceContent("templates/ipc.html");
		HttpServerUtilities.prepareHttpResponse(exchange, HttpStatus.SC_OK, content);
	}
}
