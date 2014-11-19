package zx.soft.es.model;

public class CountResult {
	private long count;
	private int failedShards;
	private int successfulShards;
	private int totalShards;

	public CountResult() {
		this.count = 0;
		this.failedShards = 0;
		this.successfulShards = 0;
		this.totalShards = 0;
	}

	@Override
	public String toString() {
		return "count=" + count + "; failedShards=" + failedShards + "; successfulShards=" + successfulShards
				+ "; totalShards=" + totalShards;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getFailedShards() {
		return failedShards;
	}

	public void setFailedShards(int failedShards) {
		this.failedShards = failedShards;
	}

	public int getSuccessfulShards() {
		return successfulShards;
	}

	public void setSuccessfulShards(int successfulShards) {
		this.successfulShards = successfulShards;
	}

	public int getTotalShards() {
		return totalShards;
	}

	public void setTotalShards(int totalShards) {
		this.totalShards = totalShards;
	}

}
