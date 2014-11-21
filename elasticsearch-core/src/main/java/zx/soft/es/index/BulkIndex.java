package zx.soft.es.index;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.client.BuildClient;
import zx.soft.es.model.Weibo;

public class BulkIndex {

	private final static String index = "spiderindextest";
	private final static String type = "spidertypetest";
	private static Logger logger = LoggerFactory.getLogger(BulkIndex.class);
	private static TransportClient client = BuildClient.buildClient();
	private static BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {

		@Override
		public void beforeBulk(long executionId, BulkRequest request) {
			logger.info("Going to execute new bulk composed of " + request.numberOfActions() + " actions");
		}

		@Override
		public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
			logger.info("Executed bulk composed of " + request.numberOfActions() + " actions");

		}

		@Override
		public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
			logger.warn("Error executing bulk", failure);

		}

	}).setBulkActions(500).setBulkSize(new ByteSizeValue(10, ByteSizeUnit.KB))
	.setFlushInterval(TimeValue.timeValueSeconds(60)).setConcurrentRequests(8).build();

	public void doIndex(List<Weibo> weibos) {
		XContentBuilder doc = null;
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
				IndexRequest indexRequest = new IndexRequest(index, type, weibo.getId()).source(doc);
				bulkProcessor.add(indexRequest);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void closeBulkProcessor() {
		bulkProcessor.close();
	}

}
