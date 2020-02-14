package com.easy.systems.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.InvoiceProductDetails;

@Repository
public class DailySaleReportDao extends AbstractDao<Integer, InvoiceProductDetails> {
	
	private static final SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public List<InvoiceProductDetails> getDailySaleReport(String date) throws ParseException {

		
		
		
		Date d = new Date();
		
		
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productCode"));
		criteria.add(Restrictions.eq("createDate", sdfmt1.parse(date)));
		ProjectionList p1 = Projections.projectionList();
		p1.add(Projections.groupProperty("productCode"));
		p1.add(Projections.sum("unitSold"));
		p1.add(Projections.sum("discountedPiece"));
		p1.add(Projections.sum("subTotal"));
		p1.add(Projections.sum("caseSold"));
		criteria.setProjection(p1);

		List<?> productList = new ArrayList<>();
		productList = criteria.setResultTransformer(Transformers.TO_LIST).list();
		return getRport(productList);

	}

	private List<InvoiceProductDetails> getRport(List<?> productList) {

		int len = productList.size();
		List<InvoiceProductDetails> products = new ArrayList<>();
		InvoiceProductDetails productDetails;
		for (int i = 0; i < len; i++) {			
			List<?> l1= (List<?>) productList.get(i);
			productDetails = new InvoiceProductDetails();
			for(int j=0;j<l1.size();j++){
				String product = (String) l1.get(j);
				long units=(long)l1.get(j+1);
				long freeUnits=(long)l1.get(j+2);
				BigDecimal totalVal= (BigDecimal)l1.get(j+3);
				
						
						Long caseS=((long)l1.get(j+4));
						int caseSold=caseS.intValue(); 	
						
				productDetails.setProductCode(product);
				productDetails.setUnitSold(units+freeUnits);
				productDetails.setSubTotal(totalVal);
				productDetails.setCaseSold(caseSold);
				break;
			}
			products.add(productDetails);
			/*Object obArr =  productList.get(i).toString();
			String product = (String) obArr;
			

			System.out.println(productDetails.toString());
			products.add(productDetails);*/
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceProductDetails> getProduct(String invoiceCode) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productCode"));
		criteria.add(Restrictions.eq("invoiceCode", invoiceCode));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<InvoiceProductDetails> products = (List<InvoiceProductDetails>) criteria.list();
		return products;

	}

}
