package zx.soft.es.web.server;

import java.util.Properties;

import org.restlet.Component;
import org.restlet.data.Protocol;

import zx.soft.es.utils.ConfigUtil;
import zx.soft.es.web.application.IndexApplication;
import zx.soft.es.web.jackson.ReplaceConvert;

/**
 * 微博数据服务类
 * 索引示例：
 *  http://192.168.3.21:8084/spider/index  POST
 * @author fgq
 *
 */
public class ESIndexServer {
	private final Component component;
	private final IndexApplication indexApplication;
	private final int PORT;

	public ESIndexServer() {
		Properties pros = ConfigUtil.getProps("web-server.properties");
		component = new Component();
		indexApplication = new IndexApplication();
		PORT = Integer.parseInt(pros.getProperty("indexapi.port"));
	}

	public static void main(String[] args) {
		ESIndexServer server = new ESIndexServer();
		server.start();
	}

	protected void start() {
		component.getServers().add(Protocol.HTTP, PORT);
		try {
			component.getDefaultHost().attach("/spider", indexApplication);
			ReplaceConvert.configureJacksonConverter();
			component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void stop() {
		try {
			component.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
