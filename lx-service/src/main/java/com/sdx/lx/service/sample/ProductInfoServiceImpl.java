package com.sdx.lx.service.sample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.BeanCopyUtils;
import com.sdx.lx.service.intf.sample.ProductInfoService;
import com.sdx.lx.service.intf.sample.dto.ProductInfo;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	DalClient dalClient;

	@Override
	public Long saveProduct(ProductInfo product) {
		Long id = null;
		if (product != null) {
			id = product.getId();
			if (id != null) {
				ProductInfo old = get(id);
				BeanCopyUtils.copyProperties(product, old);

				if (old.getType() != 0) {
					old.setKind(null);
				}
				dalClient.merge(old);
			} else {
				product.setCreateTime(new Date());
				if (product.getType() != 0) {
					product.setKind(null);
				}
				id = dalClient.persist(product).longValue();
			}
		}
		return id;
	}

	@Override
	public ProductInfo get(Long id) {
		if (id == null) {
			return null;
		}
		ProductInfo product = new ProductInfo();
		product.setId(id);
		product = dalClient.find(ProductInfo.class, product);
		if (product == null) {
			return null;
		} else {
			product.setId(id);
			return product;
		}
	}

	@Override
	public List<ProductInfo> getProductList(String keyword, Long startIndex, Long num) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("name", keyword);
		}
		map.put("startIndex", startIndex);
		map.put("maxCount", num);
		return dalClient.queryForList("PRODUCT_INFO.SELECT_LIST", map, ProductInfo.class);
	}

	@Override
	public Long getProductListCount(String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(keyword)) {
			map.put("name", keyword);
		}
		Map<String, Object> rtMap = dalClient.queryForMap("PRODUCT_INFO.SELECT_LIST_COUNT", map);
		String num = String.valueOf(rtMap.get("NUM"));
		return Long.parseLong(num);
	}

	@Override
	public void deleteProduct(Long id) {
		ProductInfo product = get(id);
		if (product != null) {
			dalClient.remove(product);
		}
	}

}
