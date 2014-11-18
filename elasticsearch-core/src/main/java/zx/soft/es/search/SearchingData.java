package zx.soft.es.search;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.client.BuildClient;
import zx.soft.es.model.SearchParameters;

public class SearchingData {
	private static Logger logger = LoggerFactory.getLogger(SearchingData.class);

	public SearchResponse doSearch(SearchParameters searchParameters) throws IOException {
		SearchRequestBuilder searchRequest = BuildClient.buildClient().prepareSearch("spiderindextest")
				.setTypes("spidertypetest");
		logger.info("index=spiderindextest");

		QueryStringQueryBuilder qb = QueryBuilders.queryString(searchParameters.getQ());
		if (searchParameters.getAnalyzer() != "ik") {
			qb.analyzer(searchParameters.getAnalyzer());
			logger.info("analyzer=" + searchParameters.getAnalyzer());
		}

		if (searchParameters.getDf() != "") {
			qb.defaultField(searchParameters.getDf());
			logger.info("default_field=" + searchParameters.getDf());
		}

		if (searchParameters.getDefault_operator() != Operator.OR) {
			qb.defaultOperator(searchParameters.getDefault_operator());
			logger.info("operator=" + searchParameters.getDefault_operator());
		}

		if (searchParameters.isLowercase_expanded_terms() != true) {
			qb.lowercaseExpandedTerms(searchParameters.isLowercase_expanded_terms());
			logger.info("Lowercase_expanded_terms=" + searchParameters.isLowercase_expanded_terms());
		}

		if (searchParameters.isAnalyze_wildcard() != true) {
			qb.analyzeWildcard(searchParameters.isAnalyze_wildcard());
			logger.info("analyze_wildcard=" + searchParameters.isAnalyze_wildcard());
		}
		searchRequest.setQuery(qb);
		logger.info("set query succeed!");

		if (searchParameters.getExplain() != false)
			searchRequest.setExplain(searchParameters.getExplain());

		if (searchParameters.getFrom() != 0)
			searchRequest.setFrom(searchParameters.getFrom());

		if (searchParameters.getSort() != "") {
			for (String sort : searchParameters.getSort().split(",")) {
				searchRequest.addSort(sort.split(":")[0], "desc".equalsIgnoreCase(sort.split(":")[1]) ? SortOrder.DESC
						: SortOrder.ASC);
			}
		}
		if (searchParameters.getSize() != 10)
			searchRequest.setSize(searchParameters.getSize());

		if (searchParameters.getTimeout() != new TimeValue(0))
			searchRequest.setTimeout(searchParameters.getTimeout());

		if (searchParameters.isTrack_scores() != true)
			searchRequest.setTrackScores(searchParameters.isTrack_scores());

		if (searchParameters.getSearch_type() != "query_then_fetch")
			searchRequest.setSearchType(searchParameters.getSearch_type());
		if (searchParameters.getFields() != "") {
			String[] fields = searchParameters.getFields().split(",");
			searchRequest.addFields(fields);
		}
		if (searchParameters.is_source() != false)
			searchRequest.setFetchSource(searchParameters.is_source());

		return searchRequest.execute().actionGet();
	}

	public static void main(String[] args) throws IOException {
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setQ(" 天气不错  武汉");
		searchParameters.setTimeout(new TimeValue(100000));
		searchParameters.setSize(5);
		searchParameters.setFields("_source,content,nickname");
		//searchParameters.setLowercase_expanded_terms(Boolean.FALSE);
		searchParameters.setTrack_scores(false);
		//searchParameters.setFrom(1);
		//searchParameters.setExplain(true);
		//searchParameters.setDefault_operator(Operator.OR);
		//System.out.println(doSearch(searchParameters));
	}
}
