package zx.soft.es.web.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import zx.soft.es.model.PostData;
import zx.soft.es.utils.IndexResponse;
import zx.soft.es.web.application.IndexApplication;

/**
 * 微博数据索引资源类
 * @author fgq
 *
 */
public class IndexServerResource extends ServerResource {
	private IndexApplication application;

	@Override
	public void doInit() {
		application = (IndexApplication) getApplication();
	}

	@Post("json")
	public Object doIndex(final PostData data) {

		if (getReference().getRemainingPart().length() != 0)
			return new IndexResponse.Builder(1000, "error url!").build();
		else if (data == null)
			return new IndexResponse.Builder(1001, "no data!").build();
		else {
			application.createIndex(data.getRecords());
			return new IndexResponse.Builder(0, "index succeed!").build();
		}
	}

	@Get("json")
	public String getData() {
		return "ok ";
	}
	/*@Get
	public Representation toJson() {
		Weibo weibo = new Weibo();
		weibo.setContent("hello every body!");
		return new JacksonRepresentation<Weibo>(weibo);
	}*/

}
