package zx.soft.es.web.application;

import org.elasticsearch.action.search.SearchResponse;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.es.model.RequestParams;
import zx.soft.es.model.SearchParams;
import zx.soft.es.search.Search;
import zx.soft.es.web.resource.SearchServerResource;

public class SearchApplication extends Application {

	private final Search search;

	public SearchApplication() {
		search = new Search();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router();
		router.attach("/search", SearchServerResource.class);
		return router;
	}

	public SearchResponse doSearch(SearchParams searchParams, RequestParams requestParams) {
		SearchResponse response = search.doSearch(searchParams, requestParams);
		return response;
	}

}
