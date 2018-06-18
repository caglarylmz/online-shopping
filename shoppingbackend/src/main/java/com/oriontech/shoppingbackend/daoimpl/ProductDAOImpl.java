package com.oriontech.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oriontech.shoppingbackend.dao.ProductDAO;
import com.oriontech.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//get single product item by id
	@Override
	public Product get(int productid) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class,Integer.valueOf(productid)) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	//get all products 
	@Override
	public List<Product> list() {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("FROM Product",Product.class)
					.getResultList() ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	//add product
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	//update product
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	//active propert is set "false". 
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	//get list all active products(active=true)
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active =:active";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProducts,Product.class)
				.setParameter("active",true)
				.getResultList();			
	}
	//get list all active products by category(active=true)
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active=:active AND categoryId=:categoryId";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductsByCategory,Product.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId)
				.getResultList();
		
	}
	//get list all products Order By count
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product WHERE active=:active ORDER BY id",Product.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	}

}
