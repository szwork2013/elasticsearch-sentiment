package zx.soft.es.web.application;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.model.SearchParameters;
import zx.soft.es.search.SearchingData;
import zx.soft.es.web.resource.SearchServerResource;

public class SearchApplication extends Application {

	private static Logger logger = LoggerFactory.getLogger(SearchApplication.class);
	private final SearchingData searchingData;

	public SearchApplication() {
		searchingData = new SearchingData();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router();
		router.attach("/search", SearchServerResource.class);
		return router;
	}

	public SearchResponse doSearch(SearchParameters searchParameters) {
		SearchResponse response = null;
		try {
			response = searchingData.doSearch(searchParameters);
		} catch (IOException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}
