package zx.soft.es.model;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;

public class SearchParameters {

	//查询字符串
	private String q;

	//查询语句中未定义字段时，采用此处设置的field
	private String df = "_all";

	//解析查询字符串时使用的分词器;
	private String analyzer = "ik";

	//逻辑操作:AND,OR 默认是 OR.
	private Operator default_operator = Operator.AND;

	//For each hit, contain an explanation of how scoring of the hits was computed.
	private boolean explain = Boolean.FALSE;

	//设置为false，禁止返回_source字段
	//You can also retrieve part of the document by using _source_include & _source_exclude
	private boolean _source = Boolean.FALSE;

	//选择想要返回的字段，以逗号分开，如果未指定字段则不返回任何字段
	private String fields = "";

	//排序：可以是字段名 fieldName,或者 fieldName:asc/fieldName:desc.
	//字段名可以是真实的一个字段。也可以是特殊的_score，表明是基于得分的排序
	// There can be several sort parameters (order is important).
	private String sort = "";

	//设置为true，可以将得分作为返回值的一部分返回
	private boolean track_scores = Boolean.TRUE;

	//设置搜索超时时间，默认no timeout
	private TimeValue timeout = new TimeValue(0);

	//记录的返回点，默认从0开始返回
	private int from = 0;

	//返回记录数，默认为10
	private int size = 10;

	//搜索操作类型， 默认 query_then_fetch.有以下几种
	//dfs_query_then_fetch,
	//dfs_query_and_fetch,
	//query_then_fetch,
	//query_and_fetch,
	//count,
	//scan.
	private String search_type = "query_then_fetch";

	//设置字符是否自动转换为小写，默认为true;
	private boolean lowercase_expanded_terms = Boolean.TRUE;

	//设置通配符和前缀查询能否被解析，默认为true
	private boolean analyze_wildcard = Boolean.TRUE;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	public String getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(String analyzer) {
		this.analyzer = analyzer;
	}

	public Operator getDefault_operator() {
		return default_operator;
	}

	public void setDefault_operator(Operator default_operator) {
		this.default_operator = default_operator;
	}

	public boolean getExplain() {
		return explain;
	}

	public void setExplain(boolean explain) {
		this.explain = explain;
	}

	public boolean is_source() {
		return _source;
	}

	public void set_source(boolean _source) {
		this._source = _source;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isTrack_scores() {
		return track_scores;
	}

	public void setTrack_scores(boolean track_scores) {
		this.track_scores = track_scores;
	}

	public TimeValue getTimeout() {
		return timeout;
	}

	public void setTimeout(TimeValue timeout) {
		this.timeout = timeout;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

	public boolean isLowercase_expanded_terms() {
		return lowercase_expanded_terms;
	}

	public void setLowercase_expanded_terms(boolean lowercase_expanded_terms) {
		this.lowercase_expanded_terms = lowercase_expanded_terms;
	}

	public boolean isAnalyze_wildcard() {
		return analyze_wildcard;
	}

	public void setAnalyze_wildcard(boolean analyze_wildcard) {
		this.analyze_wildcard = analyze_wildcard;
	}

}
