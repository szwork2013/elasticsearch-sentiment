package zx.soft.es.client;

import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import zx.soft.es.utils.ConfigUtil;

public class BuildClient {
	public static TransportClient buildClient() {
		TransportClient client = null;
		try {
			Properties props = ConfigUtil.getProps("elastic_search.properties");
			Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", props.getProperty("es.name"))
					.build();
			client = new TransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(props.getProperty("es.ip"), Integer
					.parseInt(props.getProperty("es.port"))));
			return client;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
