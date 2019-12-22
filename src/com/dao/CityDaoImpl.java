package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.model.CityBean;

@Repository
public class CityDaoImpl implements CityDao{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
    
	public List<CityBean> getAllCity(){
		List<CityBean> city_list=new ArrayList<CityBean>();
		
		Session session=sessionFactory.openSession();
		Criteria ctr=session.createCriteria(CityBean.class);
		city_list=(List<CityBean>)ctr.list();
		session.close();
		ctr.setCacheable(true);
		return city_list;
	 }
	
	
	public List<CityBean> getCityByState(int state_id){
		List<CityBean> city_list=new ArrayList<CityBean>();
		
		Session session=sessionFactory.openSession();
		Criteria ctr=session.createCriteria(CityBean.class);
		ctr.add(Restrictions.eq("state_id", state_id));
		city_list=(List<CityBean>)ctr.list();
		session.close();
		
		return city_list;
	}
	
	
	
		
}






















