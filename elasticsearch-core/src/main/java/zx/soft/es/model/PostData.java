package zx.soft.es.model;

import java.util.List;

/**
 * POST 的索引数据：int num,List<Weibo>records
 * @author fgq
 *
 */
public class PostData {

	private List<Weibo> records;
	private int num;

	public List<Weibo> getRecords() {
		return records;
	}

	public void setRecords(List<Weibo> records) {
		this.records = records;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
