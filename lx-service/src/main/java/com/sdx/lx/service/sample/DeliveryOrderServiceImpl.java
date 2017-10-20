package com.sdx.lx.service.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.DeliveryOrderService;
import com.sdx.lx.service.intf.sample.NeedOrderService;
import com.sdx.lx.service.intf.sample.dto.DeliveryOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrderItem;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

	@Autowired
	DalClient dalClient;

	@Autowired
	NeedOrderService needOrderService;

	@Override
	public List<DeliveryOrder> getDeliveryOrderList(Long needOrderItemId, String keyword, Long startIndex, Long num) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		map.put("needOrderItemId", needOrderItemId);
		map.put("startIndex", startIndex);
		map.put("maxCount", num);
		return dalClient.queryForList("DELIVERY_ORDER.SELECT_LIST", map, DeliveryOrder.class);
	}

	@Override
	public Long getDeliveryOrderListCount(Long needOrderItemId, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		map.put("needOrderItemId", needOrderItemId);
		Map<String, Object> rtMap = dalClient.queryForMap("DELIVERY_ORDER.SELECT_LIST_COUNT", map);
		String num = String.valueOf(rtMap.get("NUM"));
		return Long.parseLong(num);
	}

	@Override
	public DeliveryOrder getDeliveryOrder(Long id) {
		DeliveryOrder order = new DeliveryOrder();
		order.setId(id);
		order = dalClient.find(DeliveryOrder.class, order);
		if (order == null) {
			return null;
		} else {
			order.setId(id);
			return order;
		}
	}

	@Override
	@Transactional
	public void saveDeliveryOrder(DeliveryOrder deliveryOrder) {
		Long needOrderItemId = deliveryOrder.getNeedOrderItemId();
		NeedOrderItem item = needOrderService.getNeedOrderItem(needOrderItemId);
		NeedOrder needOrder = needOrderService.getNeedOrder(item.getNeedOrderId());

		deliveryOrder.setId(null);
		deliveryOrder.setNeedOrderId(needOrder.getId());
		deliveryOrder.setProductName(item.getProductName());
		deliveryOrder.setNeedOrderCode(needOrder.getNeedOrderCode());
		deliveryOrder.setNeedCompany(needOrder.getNeedCompany());
		deliveryOrder.setCreateTime(new Date());
		dalClient.persist(deliveryOrder);

		try {
			item.setDeliverNum(item.getDeliverNum() + Integer.valueOf(deliveryOrder.getNum()));
		} catch (Exception e) {
			// ignore
		}
		if (item.getNum().intValue() == item.getDeliverNum().intValue()) {
			item.setDeliverStatus(1);
		}
		dalClient.merge(item);

		needOrder.setDeliverStatus(1);
		dalClient.merge(needOrder);

	}

	@Override
	public void updateDeliveryOrder(DeliveryOrder deliveryOrder) {
		if (deliveryOrder.getId() != null) {
			DeliveryOrder old = getDeliveryOrder(deliveryOrder.getId());
			BeanCopyUtils.copyProperties(deliveryOrder, old);
			dalClient.merge(old);
		}
	}

	@Override
	public File exportDeliverOrder() {
		List<DeliveryOrder> list = getDeliveryOrderList(null, null, null, null);
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 获取List size作为excel行数
		XSSFSheet sheet = workbook.createSheet("发货单");
		// 创建第一栏
		XSSFRow headRow = sheet.createRow(0);
		String[] titleArray = { "品名", "规格", "需货单号", "需货单位", "发往地", "发货日期" };
		int columeCount = titleArray.length;
		for (int m = 0; m <= columeCount - 1; m++) {
			XSSFCell cell = headRow.createCell(m);
			// 填写数据
			cell.setCellValue(titleArray[m]);
		}

		int index = 0;
		for (DeliveryOrder order : list) {
			// logger.info("写入一行");
			XSSFRow row = sheet.createRow(index + 1);
			for (int n = 0; n <= columeCount - 1; n++) {
				row.createCell(n);
			}
			row.getCell(0).setCellValue(order.getProductName());
			row.getCell(1).setCellValue(order.getSpec());
			row.getCell(2).setCellValue(order.getNeedOrderCode());
			row.getCell(3).setCellValue(order.getNeedCompany());
			row.getCell(4).setCellValue(order.getDeliverCity());
			if (order.getDeliverDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sdf.format(order.getDeliverDate());
				row.getCell(5).setCellValue(dateStr);
			}
			index++;
		}

		File file = null;
		try {
			file = File.createTempFile("exprot", ".xlsx");
		} catch (IOException e1) {
			throw new RuntimeException();
		}
		try (FileOutputStream fileOutputStream = new FileOutputStream(file);) {
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

}
