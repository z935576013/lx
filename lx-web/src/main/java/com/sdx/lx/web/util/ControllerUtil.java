package com.sdx.lx.web.util;


public class ControllerUtil {


	public static PageParam getPageParam(Long totleCount,
			MobilePageParam mobilePageParam) {
		return getPageParam(totleCount, mobilePageParam.getStartIndex(),
				mobilePageParam.getMaxCount());

	}

	public static PageParam getPageParam(Long totleCount, Long startIndex,
			Long maxCount) {
		if (startIndex == null) {
			startIndex = 0L;
		}
		if (maxCount == null) {
			maxCount = 20L;
		}
		PageParam page = new PageParam();
		page.setRecordCount(totleCount);

		page.setPageCount(totleCount / maxCount + 1);

		page.setPageSize(maxCount.intValue());
		page.setStartNum(startIndex);

		page.setPageIndex(startIndex / maxCount + 1);

		return page;
	}

}
