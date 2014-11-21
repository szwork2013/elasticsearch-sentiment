package zx.soft.es.web.server;

import java.util.Properties;

import org.restlet.Component;
import org.restlet.data.Protocol;

import zx.soft.es.utils.ConfigUtil;
import zx.soft.es.web.application.SearchApplication;
import zx.soft.es.web.jackson.ReplaceConvert;

/**
 * 搜索服务类
 * 搜索示例：
 *http://192.168.3.22:8085/spider/search  GET
 * 统计总数示例：
 *http://192.168.6.126:8085/spider/search/count GET
 *删除示例：
 *http://192.168.6.126:8085/spider/search DELETE
 *  @author fgq
 */
public class ESSearchServer {

	private final Component component;
	private final SearchApplication searchApplication;
	private final int PORT;

	public ESSearchServer() {
		Properties props = ConfigUtil.getProps("web-server.properties");
		component = new Component();
		searchApplication = new SearchApplication();
		PORT = Integer.parseInt(props.getProperty("searchapi.port"));
	}

	public static void main(String[] leftArgs) {
		ESSearchServer server = new ESSearchServer();
		server.start();
	}

	private void start() {

		try {
			component.getServers().add(Protocol.HTTP, PORT);
			component.getDefaultHost().attach("/spider", searchApplication);
			ReplaceConvert.configureJacksonConverter();
			component.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			component.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
