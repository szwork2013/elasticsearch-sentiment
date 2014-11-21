package zx.soft.es.web.application;

import java.io.IOException;

import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.delete.Delete;
import zx.soft.es.model.CountResult;
import zx.soft.es.model.SearchParameters;
import zx.soft.es.search.Count;
import zx.soft.es.search.SearchingData;
import zx.soft.es.web.resource.CountResource;
import zx.soft.es.web.resource.SearchServerResource;

public class SearchApplication extends Application {

	private static Logger logger = LoggerFactory.getLogger(SearchApplication.class);
	private final SearchingData searchingData;
	private final Count count;
	private Delete delete;

	public SearchApplication() {
		searchingData = new SearchingData();
		count = new Count();
		delete = new Delete();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router();
		router.attach("/search", SearchServerResource.class);
		router.attach("/search/count", CountResource.class);
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

	public CountResult doCount(SearchParameters searchParameters) {
		CountResult countResult = null;
		try {
			countResult = count.getMatchCount(searchParameters);
		} catch (IOException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return countResult;
	}

	public DeleteByQueryResponse doDeleteByQuery(SearchParameters searchParameters) {
		DeleteByQueryResponse response = null;
		try {
			response = delete.deleteByQuery(searchParameters);
		} catch (IOException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}
