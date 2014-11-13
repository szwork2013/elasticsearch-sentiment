package zx.soft.es.utils;

/**
 * POST 索引响应类
 * @author fgq
 *
 */
public class IndexResponse {

	private final int Code;
	private final String Info;

	public IndexResponse(Builder builder) {
		this.Code = builder.Code;
		this.Info = builder.Info;
	}

	public int getCode() {
		return Code;
	}

	public String getInfo() {
		return Info;
	}

	public static class Builder {

		private final int Code;
		private final String Info;

		public Builder(int Code, String Info) {
			super();
			this.Code = Code;
			this.Info = Info;
		}

		public IndexResponse build() {
			return new IndexResponse(this);
		}

	}

}
