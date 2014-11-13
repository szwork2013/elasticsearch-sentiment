package zx.soft.es.web.resource;

import java.util.HashMap;

import org.elasticsearch.action.search.SearchResponse;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.model.RequestParams;
import zx.soft.es.model.SearchParams;
import zx.soft.es.utils.IndexResponse;
import zx.soft.es.utils.URLUtils;
import zx.soft.es.web.application.SearchApplication;

public class SearchServerResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(SearchServerResource.class);
	private SearchApplication application;
	private SearchParams searchParams;
	private RequestParams requestParams;

	@Override
	public void doInit() {

		application = (SearchApplication) getApplication();
		searchParams = new SearchParams();
		requestParams = new RequestParams();
		HashMap<String, String> params = new HashMap<>();
		Form form = getRequest().getResourceRef().getQueryAsForm();
		for (Parameter p : form) {
			if (params.get(p.getName()) == null) {
				params.put(p.getName(), p.getValue());
			} else { // 重复参数以最后一个为准
				params.put(p.getName(), p.getValue());
			}
		}
		searchParams.setField(params.get("field") == null ? "content" : params.get("field"));
		searchParams.setKeyword(params.get("keyword") == null ? "" : params.get("keyword"));
		searchParams.setKeywords(params.get("keywords") == null ? "" : params.get("keywords"));
		searchParams.setPrefixword(params.get("prefixword") == null ? "" : params.get("prefixword"));
		searchParams.setId(params.get("id") == null ? "1" : params.get("id"));
		searchParams.setLikethis(params.get("likethis") == null ? "" : params.get("likethis"));
		searchParams.setMaxNum(params.get("maxNum") == null ? 100 : Integer.parseInt(params.get("maxNum")));
		searchParams.setMinFreq(params.get("minFreq") == null ? 0 : Integer.parseInt(params.get("minFreq")));
		searchParams.setLow(params.get("low") == null ? 0 : Integer.parseInt(params.get("low")));
		searchParams.setUp(params.get("up") == null ? 10 : Integer.parseInt(params.get("up")));
		searchParams.setMaxEndPosition(params.get("maxEndPosition") == null ? 1 : Integer.parseInt(params
				.get("maxEndPosition")));
		searchParams.setInfield(params.get("infield") == null ? "" : params.get("infield"));
		searchParams.setExfield(params.get("exfield") == null ? "" : params.get("exfield"));
		searchParams.setInkeyword(params.get("inkeyword") == null ? "" : params.get("inkeyword"));
		searchParams.setExkeyword(params.get("exkeyword") == null ? "" : params.get("exkeyword"));
		searchParams.setWildcard(params.get("wildcard") == null ? "" : params.get("wildcard"));

		requestParams.setIndex(params.get("index") == null ? "spiderindextest" : params.get("index"));
		requestParams.setType(params.get("type") == null ? "spidertypetest" : params.get("type"));
		requestParams.setFrom(params.get("from") == null ? 0 : Integer.parseInt(params.get("from")));
		requestParams.setSize(params.get("size") == null ? 10 : Integer.parseInt(params.get("size")));
		requestParams.setExplain(params.get("explain") == null ? false : Boolean.parseBoolean(params.get("explain")));
		System.out.println(searchParams.toString());
		System.out.println(requestParams.toString());
	}

	@Get("json")
	public Object getSearchResult() {

		final String searchURL = URLUtils.getDecoderURL(getReference().toString(), "utf-8");
		logger.info("search url :" + searchURL);
		if (getReference().getRemainingPart() == null) {
			return new IndexResponse.Builder(-1, "illegal url!");
		}

		SearchResponse response = application.doSearch(searchParams, requestParams);

		return response.toString();
	}
}
