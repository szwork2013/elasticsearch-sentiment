package zx.soft.es.search;

import java.io.IOException;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import zx.soft.es.client.BuildClient;
import zx.soft.es.model.CountResult;
import zx.soft.es.model.SearchParameters;

public class Count {

	public CountResult getMatchCount(SearchParameters searchParameters) throws IOException {
		CountResult result = new CountResult();
		CountResponse response = null;
		if (searchParameters.getQ() != "") {
			QueryBuilder query = QueryBuilders.queryString(searchParameters.getQ())
					.defaultField(searchParameters.getDf()).defaultOperator(searchParameters.getDefault_operator())
					.analyzer(searchParameters.getAnalyzer())
					.lowercaseExpandedTerms(searchParameters.isLowercase_expanded_terms())
					.analyzeWildcard(searchParameters.isAnalyze_wildcard());
			response = BuildClient.buildClient().prepareCount("spiderindextest").setQuery(query).execute().actionGet();
		}
		result.setCount(response.getCount());
		result.setFailedShards(response.getFailedShards());
		result.setSuccessfulShards(response.getSuccessfulShards());
		result.setTotalShards(response.getTotalShards());
		return result;
	}

	public static void main(String[] args) {
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setQ(" 大家快乐");
		searchParameters.setTimeout(new TimeValue(1000000));
		//searchParameters.setSize(5);
		searchParameters.setFields("_source,content,nickname,read_count");
		searchParameters.setLowercase_expanded_terms(Boolean.FALSE);
		//searchParameters.setTrack_scores(false);
		searchParameters.setFq("read_count:185,195");
		//searchParameters.setFrom(1);
		//searchParameters.setExplain(true);
		//searchParameters.setDefault_operator(Operator.OR);
		//System.out.println(getMatchCount(searchParameters).toString());
	}
}
