package zx.soft.es.model;

public class SearchParams {

	private String field = "";
	private String keyword = "";
	private String keywords = "";
	private String prefixword = "";
	private String id = "1";
	private String likethis = "";
	private int maxNum = 100;
	private int minFreq = 0;
	private long low = 0;
	private long up = 10;
	private int maxEndPosition = 1;
	private String infield = "";
	private String exfield = "";
	private String inkeyword = "";
	private String exkeyword = "";
	private String wildcard = "";

	@Override
	public String toString() {
		return "searchParams:{field=" + field + ",keyword=" + keyword + ",keywords=" + keywords + ",prefixword="
				+ prefixword + ",id=" + id + ",likethis=" + likethis + ",maxNum=" + maxNum + ",minFreq=" + minFreq
				+ ",low=" + low + ",up=" + up + ",maxEndPosition=" + maxEndPosition + ",infield=" + infield
				+ ",exfield=" + exfield + ",inkeyword=" + keyword + ",exkeyword=" + exkeyword + ",wildcard=" + wildcard
				+ "}";
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPrefixword() {
		return prefixword;
	}

	public void setPrefixword(String prefixword) {
		this.prefixword = prefixword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLikethis() {
		return likethis;
	}

	public void setLikethis(String likethis) {
		this.likethis = likethis;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getMinFreq() {
		return minFreq;
	}

	public void setMinFreq(int minFreq) {
		this.minFreq = minFreq;
	}

	public long getLow() {
		return low;
	}

	public void setLow(long low) {
		this.low = low;
	}

	public long getUp() {
		return up;
	}

	public void setUp(long up) {
		this.up = up;
	}

	public int getMaxEndPosition() {
		return maxEndPosition;
	}

	public void setMaxEndPosition(int maxEndPosition) {
		this.maxEndPosition = maxEndPosition;
	}

	public String getInfield() {
		return infield;
	}

	public void setInfield(String infield) {
		this.infield = infield;
	}

	public String getExfield() {
		return exfield;
	}

	public void setExfield(String exfield) {
		this.exfield = exfield;
	}

	public String getInkeyword() {
		return inkeyword;
	}

	public void setInkeyword(String inkeyword) {
		this.inkeyword = inkeyword;
	}

	public String getExkeyword() {
		return exkeyword;
	}

	public void setExkeyword(String exkeyword) {
		this.exkeyword = exkeyword;
	}

	public String getWildcard() {
		return wildcard;
	}

	public void setWildcard(String wildcard) {
		this.wildcard = wildcard;
	}

}
