package zx.soft.es.search;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import zx.soft.es.client.BuildClient;
import zx.soft.es.model.Weibo;

public class Index {

	private final String index = "spiderindextest";
	private final String type = "spidertypetest";

	public void createIndex(List<Weibo> weibos) {
		XContentBuilder doc;
		Client client = BuildClient.buildClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (Weibo weibo : weibos) {
			try {
				doc = XContentFactory.jsonBuilder().startObject().field("platform", weibo.getPlatform())
						.field("mid", weibo.getMid()).field("username", weibo.getUsername())
						.field("nickname", weibo.getNickname()).field("original_id", weibo.getOriginal_id())
						.field("original_uid", weibo.getOriginal_uid())
						.field("original_name", weibo.getOriginal_name())
						.field("original_title", weibo.getOriginal_title())
						.field("original_url", weibo.getOriginal_url()).field("url", weibo.getUrl())
						.field("home_url", weibo.getUrl()).field("title", weibo.getTitle())
						.field("type", weibo.getType()).field("isharmful", weibo.isIsharmful())
						.field("content", weibo.getContent()).field("comment_count", weibo.getComment_count())
						.field("read_count", weibo.getRead_count()).field("favorite_count", weibo.getFavorite_count())
						.field("attitude_count", weibo.getAttitude_count())
						.field("repost_count", weibo.getRepost_count()).field("video_url", weibo.getVideo_url())
						.field("pic_url", weibo.getPic_url()).field("voice_url", weibo.getVoice_url())
						.field("timestamp", weibo.getTimestamp()).field("source_id", weibo.getSource_id())
						.field("lasttime", weibo.getLasttime()).field("server_id", weibo.getServer_id())
						.field("identify_id", weibo.getIdentify_id()).field("identify_md5", weibo.getIdentify_md5())
						.field("keyword", weibo.getKeyword()).field("first_time", weibo.getFirst_time())
						.field("update_time", weibo.getUpdate_time()).field("ip", weibo.getIp())
						.field("location", weibo.getLocation()).field("geo", weibo.getGeo())
						.field("receive_addr", weibo.getReceive_addr()).field("append_addr", weibo.getAppend_addr())
						.field("send_addr", weibo.getSend_addr()).field("source_name", weibo.getSource_name())
						.field("source_type", weibo.getSource_type()).field("country_code", weibo.getCountry_code())
						.field("location_code", weibo.getLocation_code())
						.field("province_code", weibo.getProvince_code()).field("city_code", weibo.getCity_code())
						.endObject();

				bulkRequest.add(client.prepareIndex(index, type, weibo.getId()).setSource(doc));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		bulkRequest.execute().actionGet();
		client.close();
	}
}
