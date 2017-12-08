package edu.scut.emos.tsp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author emos
 */
public class PropertiesUtil {

	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static final String URL_HDFS_PROPERTIES = "hdfs://";
	private static FileSystem hdfs = null;
	private static Path hdfsPropertiesPath = new Path(URL_HDFS_PROPERTIES);
	private static long hdfsPropertiesModifiedTime = 0L;
	
	private static Properties prop = new Properties();
	
	static {
		try {
			hdfs = FileSystem.get(new Configuration());
		} catch (IOException e) {
			LOG.error("hdfs properties init error", e);
		}
	}
	
	static {
		try {
			setPropertiesFile();
		} catch (IOException e) {
			LOG.error("Properties file set error", e);
		}
	}
	
	/**
	 * 判断HDFS上的Properties文件是否存在
	 * 
	 * @return
	 * @throws IOException
	 */
	private static boolean isUrlHdfsPropertiesExist() throws IOException {
		return hdfs.exists(hdfsPropertiesPath);
	}
	
	/**
	 * 判断HDFS上的Properties文件是否更新
	 * 
	 * @return
	 * @throws IOException
	 */
	private static boolean isUrlHdfsPropertiesModified() throws IOException {
		FileStatus file = hdfs.getFileStatus(hdfsPropertiesPath);
		Long newHdfsPropertiesModifiedTime = file.getModificationTime();
		// 若更新时间与本地更新时间不一致
		if(newHdfsPropertiesModifiedTime != hdfsPropertiesModifiedTime) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取HDFS的Properties文件，并更新时间戳
	 * 
	 * @return
	 * @throws IOException
	 */
	private static void getUrlHdfsProperties() throws IOException {
		FSDataInputStream hdfsInStream = hdfs.open(hdfsPropertiesPath);
		FileStatus file = hdfs.getFileStatus(hdfsPropertiesPath);
		prop.load(hdfsInStream);
		hdfsPropertiesModifiedTime = file.getModificationTime();
		return ;
	}
	
	/**
	 * 设置Properties文件
	 * 
	 * @throws IOException
	 */
	private static void setPropertiesFile() throws IOException {
		// 若HDFS上的Properties文件存在，则获取
		if(isUrlHdfsPropertiesExist()) {
			if(isUrlHdfsPropertiesModified()) {
				getUrlHdfsProperties();
			}
			return ;
		}

		// 读取本地Properties文件
		InputStream localis = ClassLoader.getSystemResourceAsStream("tsp.properties");
		prop.load(localis);
	}
	
	/**
	 * 默认参数类
	 * 
	 * @author emos
	 *
	 */
	private class DefaultProperties {
		/**
		 * 选取车辆范围
		 */
		public static final String VEHICLE_SCOPE = "50";
		/**
		 * 查询车辆模式
		 */
		public static final String TYPE_OF_GETVEHICLE = "square";
	    /**
	     * 路线中两个任务点之间的最大距离(公里)
	     */
	    public static final String MAX_DISTANCE = "200";
	    /**
	     * 车辆在任务点的等待的最大时间(小时)
	     */
	    public static final String MAX_WAIT_TIME = "8";
	    /**
	     * 车辆到达任务点延迟的最大时间(小时)
	     */
	    public static final String MAX_DELAY_TIME = "1";
	    /**
	     * 车辆运输货物时掏货所花费的时间(小时)
	     */
	    public static final String MAX_DRAW_OUT_TIME = "1";
	    /**
	     * 路径权重
	     */
	    public static final String DISTANCE_WEIGHT = "0.1";
	    /**
	     * 等待时间权重
	     */
	    public static final String WAIT_TIME_WEIGHT = "0.1";
	    /**
	     * 延迟时间权重
	     */
	    public static final String DELAY_TIME_WEIGHT = "0.5";
	    /**
	     * 掏货时间权重
	     */
	    public static final String DRAW_OUT_TIME_WEIGHT = "0.3";
	    /**
	     * 最大代价
	     */
	    public static final String MAX_COST = "0.8";
	    /**
	     * 百度地图私人AK
	     */
	    public static final String AK = "xFXLbDh24OBgnGluD8dqgDV2lvbg4pyL";
	}
	
	/*
	 * 以下是Properties文件调取方法
	 */
	
	public static double vehicleScope() {
		return Double.valueOf(prop.getProperty("tsp.vehicleScope", DefaultProperties.VEHICLE_SCOPE));
	}
	
	public static String way2GetVehicle() {
		return prop.getProperty("tsp.typeOfGetVehicle", DefaultProperties.TYPE_OF_GETVEHICLE);
	}
	
	public static double maxDistance() {
		return Double.valueOf(prop.getProperty("tsp.maxDistance", DefaultProperties.MAX_DISTANCE));
	}
	
	public static double maxWaitTime() {
		return Double.valueOf(prop.getProperty("tsp.maxWaitTime", DefaultProperties.MAX_WAIT_TIME));
	}
	
	public static double maxDelayTime() {
		return Double.valueOf(prop.getProperty("tsp.maxDelayTime", DefaultProperties.MAX_DELAY_TIME));
	}
	
	public static double maxDrawOutTime() {
		return Double.valueOf(prop.getProperty("tsp.maxDrawOutTime", DefaultProperties.MAX_DRAW_OUT_TIME));
	}
	
	public static double distanceWeight() {
		return Double.valueOf(prop.getProperty("tsp.distanceWeight", DefaultProperties.DISTANCE_WEIGHT));
	}
	
	public static double waitTimeWeight() {
		return Double.valueOf(prop.getProperty("tsp.waitTimeWeight", DefaultProperties.WAIT_TIME_WEIGHT));
	}
	
	public static double delayTimeWeight() {
		return Double.valueOf(prop.getProperty("tsp.delayTimeWeight", DefaultProperties.DELAY_TIME_WEIGHT));
	}
	
	public static double drawOutTimeWeight() {
		return Double.valueOf(prop.getProperty("tsp.drawOutTimeWeight", DefaultProperties.DRAW_OUT_TIME_WEIGHT));
	}
	
	public static double maxCost() {
		return Double.valueOf(prop.getProperty("tsp.maxCost", DefaultProperties.MAX_COST));
	}
	
	public static String ak() {
		return prop.getProperty("tsp.ak", DefaultProperties.AK);
	}
}
