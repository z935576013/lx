package com.sdx.lx.web.util;

import java.io.Serializable;

public class MobilePageParam implements Serializable {

	public final static Long START_INDEX = 0L;

	public final static Long NUM = 8L;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5149718944182792301L;

	Long startIndex;
	Long maxCount;

	Long pageSize;
	Long pageNumber;

	public Long getStartIndex() {

		init();

		return startIndex;
	}

	private void init() {
		if (pageNumber == null && startIndex == null) {
			startIndex = START_INDEX;
			maxCount = NUM;
		} else if (pageNumber != null && startIndex == null) {
			if (pageSize == null) {
				pageSize = NUM;
			}
			startIndex = Long.valueOf(pageSize)
					* (Long.valueOf(pageNumber) - 1);

			maxCount = pageSize;
		}
	}

	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	public Long getMaxCount() {
		init();

		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

}
