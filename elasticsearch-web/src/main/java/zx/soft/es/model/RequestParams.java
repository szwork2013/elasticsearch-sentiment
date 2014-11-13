package zx.soft.es.model;

public class RequestParams {

	private String index = "";
	private String type = "";
	private int from = 0;
	private int size = 10;
	private boolean explain = Boolean.FALSE;

	@Override
	public String toString() {
		return "requestParams:{index=" + index + ",type=" + type + ",from=" + from + ",size=" + size + ",explain="
				+ explain + "}";
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public boolean isExplain() {
		return explain;
	}

	public void setExplain(boolean explain) {
		this.explain = explain;
	}

}
