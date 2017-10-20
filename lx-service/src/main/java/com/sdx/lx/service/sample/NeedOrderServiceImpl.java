package com.sdx.lx.service.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.NeedOrderService;
import com.sdx.lx.service.intf.sample.dto.NeedOrder;
import com.sdx.lx.service.intf.sample.dto.NeedOrderDto;
import com.sdx.lx.service.intf.sample.dto.NeedOrderIdGen;
import com.sdx.lx.service.intf.sample.dto.NeedOrderItem;

@Service
public class NeedOrderServiceImpl implements NeedOrderService {

	@Autowired
	DalClient dalClient;

	@Override
	public List<NeedOrderDto> getNeedOrderListDetail(String keyword, Long startIndex, Long num) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		map.put("startIndex", startIndex);
		map.put("maxCount", num);
		List<NeedOrderDto> list = dalClient.queryForList("NEED_ORDER.SELECT_LIST", map, NeedOrderDto.class);
		List<NeedOrderItem> items = getNeedOrderItemList(null);
		for (NeedOrderDto order : list) {
			Long orderId = order.getId();
			for (NeedOrderItem item : items) {
				if (Objects.equals(orderId, item.getNeedOrderId())) {
					order.getItems().add(item);
				}
			}
		}

		return list;
	}

	@Override
	public Long getNeedOrderListCount(String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("keyword", keyword);
		}
		Map<String, Object> rtMap = dalClient.queryForMap("NEED_ORDER.SELECT_LIST_COUNT", map);
		String num = String.valueOf(rtMap.get("NUM"));
		return Long.parseLong(num);
	}

	@Override
	public NeedOrder getNeedOrder(Long id) {
		if (id == null) {
			return null;
		}
		NeedOrder order = new NeedOrder();
		order.setId(id);
		order = dalClient.find(NeedOrder.class, order);
		if (order == null) {
			return null;
		} else {
			order.setId(id);
			return order;
		}
	}

	@Override
	public NeedOrderDto getNeedOrderDetail(Long id) {
		NeedOrder order = getNeedOrder(id);
		if (order == null) {
			return null;
		} else {
			NeedOrderDto result = new NeedOrderDto();
			BeanCopyUtils.copyProperties(order, result);
			List<NeedOrderItem> items = getNeedOrderItemList(id);
			result.setItems(items);
			return result;
		}
	}

	@Override
	public List<NeedOrderItem> getNeedOrderItemList(Long needOrderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("needOrderId", needOrderId);
		return dalClient.queryForList("NEED_ORDER_ITEM.SELECT_BY_FIELDS", map, NeedOrderItem.class);
	}

	@Override
	public Long saveNeedOrder(NeedOrder order, Long[] itemId, String[] productName, String[] spec, Integer[] num,
			String[] itemNote) {
		Long needOrderId = order.getId();
		if (needOrderId == null) {
			order.setId(null);
			order.setCreateTime(new Date());
			order.setDeliverStatus(0);
			order.setNeedOrderCode(genOrderCode());
			needOrderId = dalClient.persist(order).longValue();
		} else {
			NeedOrder old = getNeedOrder(needOrderId);
			BeanCopyUtils.copyProperties(order, old);
			dalClient.merge(old);
		}

		if (productName != null) {
			for (int i = 0; i < productName.length; i++) {
				if (StringUtils.isNotEmpty(productName[i])) {
					NeedOrderItem item = new NeedOrderItem();
					item.setItemNote(itemNote[i]);
					item.setNeedOrderId(needOrderId);
					if (item == null || num[i] == null) {
						item.setNum(1);
					} else {
						item.setNum(num[i]);
					}
					item.setProductName(productName[i]);
					item.setSpec(spec[i]);
					item.setDeliverStatus(0);
					item.setDeliverNum(0);
					if (itemId == null || itemId[i] == null) {
						dalClient.persist(item);
					} else {
						NeedOrderItem old = getNeedOrderItem(itemId[i]);
						BeanCopyUtils.copyProperties(item, old);
						dalClient.merge(old);
					}

				}
			}
		}
		return needOrderId;
	}

	@Override
	public NeedOrderItem getNeedOrderItem(Long id) {
		NeedOrderItem order = new NeedOrderItem();
		order.setId(id);
		order = dalClient.find(NeedOrderItem.class, order);
		if (order == null) {
			return null;
		} else {
			order.setId(id);
			return order;
		}
	}

	@Override
	public void deleteNeedOrder(Long id) {
		NeedOrder needOrder = getNeedOrder(id);
		if (needOrder != null && needOrder.getDeliverStatus() != null && needOrder.getDeliverStatus() != 1) {
			dalClient.remove(needOrder);
			List<NeedOrderItem> list = getNeedOrderItemList(id);
			for (NeedOrderItem item : list) {
				dalClient.remove(item);
			}
		}
	}

	@Override
	public void deleteNeedOrderItem(Long id) {
		NeedOrderItem needOrder = getNeedOrderItem(id);
		if (needOrder != null && needOrder.getDeliverStatus() != null && needOrder.getDeliverStatus() != 1) {
			dalClient.remove(needOrder);
		}
	}

	private String genOrderCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Map<String, Object> map = new HashMap<String, Object>();
		String today = new SimpleDateFormat("yyyy-MM").format(new Date()) + "-01";
		Date todayDate = new Date();
		try {
			todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("date", today);
		List<NeedOrderIdGen> list = dalClient.queryForList("NEED_ORDER_ID_GEN.SELECT_CURDATE", map,
				NeedOrderIdGen.class);
		if (list == null || list.isEmpty()) {
			NeedOrderIdGen gen = new NeedOrderIdGen();
			gen.setOrderCode(1);
			gen.setOrderDate(todayDate);
			dalClient.persist(gen);
			return sdf.format(gen.getOrderDate()) + "001";
		} else {
			NeedOrderIdGen gen = list.get(0);
			gen.setOrderCode(gen.getOrderCode() + 1);
			dalClient.merge(gen);
			String code = gen.getOrderCode().toString();
			if (code.length() == 1) {
				code = "00" + code;
			} else if (code.length() == 2) {
				code = "0" + code;
			}
			return sdf.format(gen.getOrderDate()) + code;

		}

	}

	@Override
	public File exportNeedOrder() {
		List<NeedOrderDto> list = getNeedOrderListDetail(null, null, null);

		XSSFWorkbook workbook = new XSSFWorkbook();
		// 获取List size作为excel行数
		XSSFSheet sheet = workbook.createSheet("发货单");
		// 创建第一栏
		XSSFRow headRow = sheet.createRow(0);
		String[] titleArray = { "需货单号", "需货单位", "订单日期", "需货日期", "收货人", "收货电话", "收货地址", "收货邮编", "品名", "规格", "需货数量",
				"已发货数量" };
		int columeCount = titleArray.length;
		for (int m = 0; m <= columeCount - 1; m++) {
			XSSFCell cell = headRow.createCell(m);
			// 填写数据
			cell.setCellValue(titleArray[m]);
		}

		int index = 0;
		for (NeedOrderDto order : list) {
			// logger.info("写入一行");
			XSSFRow row = sheet.createRow(index + 1);
			for (int n = 0; n <= columeCount - 1; n++) {
				row.createCell(n);
			}
			row.getCell(0).setCellValue(order.getNeedOrderCode());
			row.getCell(1).setCellValue(order.getNeedCompany());
			if (order.getOrderDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sdf.format(order.getOrderDate());
				row.getCell(2).setCellValue(dateStr);
			}
			if (order.getNeedDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr = sdf.format(order.getNeedDate());
				row.getCell(3).setCellValue(dateStr);
			}
			row.getCell(4).setCellValue(order.getReceiveName());
			row.getCell(5).setCellValue(order.getReceiveMobile());
			row.getCell(6).setCellValue(order.getReceiveAddress());
			row.getCell(7).setCellValue(order.getZipCode());
			index++;
			for (NeedOrderItem item : order.getItems()) {
				XSSFRow itemRow = sheet.createRow(index + 1);
				for (int n = 0; n <= columeCount - 1; n++) {
					itemRow.createCell(n);
				}
				itemRow.getCell(8).setCellValue(item.getProductName());
				itemRow.getCell(9).setCellValue(item.getSpec());
				itemRow.getCell(10).setCellValue(item.getNum());
				itemRow.getCell(11).setCellValue(item.getDeliverNum());
				index++;
			}

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
