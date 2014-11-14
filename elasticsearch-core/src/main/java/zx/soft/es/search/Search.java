package zx.soft.es.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.client.BuildClient;
import zx.soft.es.model.RequestParams;
import zx.soft.es.model.SearchParams;

/**
 * 检索，查询
 * @author fgq
 *
 */
public class Search {

	private static Logger logger = LoggerFactory.getLogger(Search.class);

	public SearchResponse doSearch(SearchParams searchParams, RequestParams requestParams) {

		//设置查询index，默认spiderindextest
		SearchRequestBuilder searchrequestbuilder = BuildClient.buildClient().prepareSearch(requestParams.getIndex());
		QueryBuilder query = QueryBuilders.matchAllQuery();
		//termQuery
		if (searchParams.getField() != "" && searchParams.getKeyword() != "") {
			query = QueryBuilders.termQuery(searchParams.getField(), searchParams.getKeyword());
		}
		//termsQuery
		if (searchParams.getField() != "" && searchParams.getKeywords() != "") {
			String[] keys = searchParams.getKeywords().split(",");
			query = QueryBuilders.termsQuery(searchParams.getField(), keys);
		}

		//Fuzzy like this Query
		if (searchParams.getField() != "" && searchParams.getLikethis() != "") {
			query = QueryBuilders.fuzzyQuery(searchParams.getField(), searchParams.getLikethis());
		}

		//prefix Query
		if (searchParams.getField() != "" && searchParams.getPrefixword() != "") {
			query = QueryBuilders.prefixQuery(searchParams.getField(), searchParams.getPrefixword());
		}

		//wildcard Query，？匹配一个字符，*匹配字符序列
		if (searchParams.getField() != "" && searchParams.getWildcard() != "") {
			query = QueryBuilders.wildcardQuery(searchParams.getField(), searchParams.getWildcard());
		}
		searchrequestbuilder.setQuery(query);

		if (requestParams.getType() != "")//设置查询类型，默认weibodatatype
			searchrequestbuilder.setTypes(requestParams.getType());
		if (requestParams.getFrom() != 0)//设置返回结果开始显示位置
			searchrequestbuilder.setFrom(requestParams.getFrom());
		if (requestParams.getSize() != 0)//应当考虑想要查看获得记录的默认数量，默认10;
			searchrequestbuilder.setSize(requestParams.getSize());
		if (requestParams.isExplain())//是否显示详细查询信息，默认不显示;
			searchrequestbuilder.setExplain(requestParams.isExplain());

		return searchrequestbuilder.execute().actionGet();
	}

}
