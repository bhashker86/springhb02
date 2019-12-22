package com.dao;

import java.util.List;
import com.model.StateBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class StateDaoImpl implements StateDao {

	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
	
	
	public List<StateBean>getStates(){
		
		List<StateBean> state_list=new ArrayList<StateBean>();
		Session session=sessionFactory.openSession();
		Criteria ctr=session.createCriteria(StateBean.class);
		return state_list=(List<StateBean>)ctr.list();
	
	}
	
}
