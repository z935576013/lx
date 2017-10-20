package com.sdx.lx.common.utils;

/**
 * 距離工具
 * 
 * @author zhuliang
 */
public class DistanceUtil {

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 距离 单位米
	 * 
	 * @param lat1
	 *            纬度1
	 * @param lot1
	 *            经度1
	 * @param lat2
	 *            纬度2
	 * @param lot2
	 *            经度2
	 * @return 米
	 */
	public static Float getDistance(double lat1, double lot1, double lat2,
			double lot2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lot1) - rad(lot2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS ;
		s = Math.round(s * 10000) / 10;
		return (float) s;
	}
	
	
	public static void main(String[] args){
		Float b=DistanceUtil.getDistance(121.502262, 31.238195, 121.508, 31.234);
		System.out.println(b);
	}

}
