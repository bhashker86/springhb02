package com.dao;

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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.model.MmvBean;

@Repository
public class MmvDaoImpl implements MmvDao{

	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
    
    public List<MmvBean> getMakeYear(){
    	List<MmvBean> make_year_list=new ArrayList<MmvBean>();
    	Session session=sessionFactory.openSession();
    	Criteria ctr=session.createCriteria(MmvBean.class);
    	
    	ProjectionList prj_list=Projections.projectionList();
    	//prj_list.add(Projections.property("year"));
    	
    	prj_list.add(Projections.groupProperty("year"),"year");
    	ctr.setResultTransformer(Transformers.aliasToBean(MmvBean.class));
    	ctr.setProjection(prj_list);
    	
    	make_year_list=(List<MmvBean>)ctr.list();
    	session.close();
    	return make_year_list;
    	
    }
    
   public List<MmvBean> getMakeModelByYear(int year){
	   
	   List<MmvBean> make_model_list=new ArrayList<MmvBean>();
   	   Session session=sessionFactory.openSession();
   	   Criteria ctr=session.createCriteria(MmvBean.class);
   	   
	   ProjectionList prj_list=Projections.projectionList();
	   prj_list.add(Projections.groupProperty("make"),"make");
	   prj_list.add(Projections.property("model"),"model");
	   ctr.setResultTransformer(Transformers.aliasToBean(MmvBean.class));
	   ctr.setProjection(prj_list);
	   ctr.add(Restrictions.eq("year", year));
	  
	   make_model_list=(List<MmvBean>)ctr.list();
       session.close();
   	   return make_model_list;
	   
   }
    
    public List<MmvBean> getModelByMake(String make){
    	
    	List<MmvBean> model_list=new ArrayList<MmvBean>();
    	Session session=sessionFactory.openSession();
    	Criteria ctr=session.createCriteria(MmvBean.class);
    	
    	   ProjectionList prj_list=Projections.projectionList();
	  	   prj_list.add(Projections.groupProperty("model"),"model");
	  	   ctr.setResultTransformer(Transformers.aliasToBean(MmvBean.class));
	  	   ctr.setProjection(prj_list);
	  	   ctr.add(Restrictions.eq("make", make));
  	  
	  	   model_list=(List<MmvBean>)ctr.list();
           session.close();
     	   return model_list;
    }
	
}















