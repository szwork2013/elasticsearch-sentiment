package test.elasticsearch_core;

import static org.junit.Assert.assertNotNull;

import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;

import zx.soft.es.client.BuildClient;

public class BuildClientTest {
	/**
	 * 测试连接es的客户端是否建立成功;
	 * 经过测试发现引入的elasticsearch包与搭建的es版本不一致，会导致链接失败
	 */
	@Test
	public void shouldReturnTransportClient() {
		TransportClient client = null;
		client = BuildClient.buildClient();
		assertNotNull(client);
	}
}
