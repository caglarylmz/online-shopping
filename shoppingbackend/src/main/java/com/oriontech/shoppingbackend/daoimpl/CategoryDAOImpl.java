package com.oriontech.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oriontech.shoppingbackend.dao.CategoryDAO;
import com.oriontech.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
		//get list from category by is_active=true
	@Override
	public List<Category> list() {
		/*SQL değil HQL(Hibernate Query Language) kullanıyoruz
		HQL Db'de tablo adı yerine O tablo ile ilişkilendirilmiş class adını, Sütun adı yerine ise o class'da ü sütun için kullanılan property adını kullanır
		Yani Category.java classı table name , active property'si is_active sütununu temsil eder. 
		active= :active -> active değişkenine Category içerisindeki active property sini parametre olarak verdik*/
		String selectActiveCategory="FROM Category WHERE active=:active";
		//String ifadeyi query'e parametre olarak veriyoruz
		Query query =  sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		//parametre atıyoruz
		query.setParameter("active",true);
		//sorgu sonucunu döndürüyoruz. Category tipinde bir liste dönecektir.
		return query.getResultList();
	}
	//Get Single Category
	@Override
	public Category get(int id) {		
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}
	//add category
	@Override
	public boolean add(Category category) {
		try {
			//add category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	//Update single category
	@Override
	public boolean update(Category category) {
		try {
			//add category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//Category'nin silinmesi demek active=false yapılması demektir. db'den satırı silmek yerine active durumu false yapılır.
	//YAni yine update işlemi gereklidir.
	@Override
	public boolean delete(Category category) {
		try {
			category.setActive(false);
			//add category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
}
