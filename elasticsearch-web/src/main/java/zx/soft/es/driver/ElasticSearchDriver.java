package zx.soft.es.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.es.web.server.ESIndexServer;
import zx.soft.es.web.server.ESSearchServer;

/**
 * 驱动类
 *
 * @author fgq
 *
 */
public class ElasticSearchDriver {

	private static Logger logger = LoggerFactory.getLogger(ElasticSearchDriver.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("Usage: Driver <class-name>");
			System.exit(-1);
		}
		String[] leftArgs = new String[args.length - 1];
		System.arraycopy(args, 1, leftArgs, 0, leftArgs.length);

		switch (args[0]) {
		case "esIndexServer":
			logger.info("索引接口： ");
			ESIndexServer.main(leftArgs);
			break;
		case "esSearchServer":
			logger.info("搜索接口：");
			ESSearchServer.main(leftArgs);
		default:
			return;
		}

	}

}
