package org.koushik.javabrains.messenger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.koushik.javabrains.messenger.model.Profile;

public class ProfileDAO {
	
	
	 public void addProfile(Profile beanp){
	        Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        addProfile(session,beanp);        
	        tx.commit();
	        session.close();
	        
	    }
	    
	    private void addProfile(Session session, Profile beanp){
	        Profile profile = new Profile();
	        
	        profile.setProfileName(beanp.getProfileName());
	        profile.setFirstName(beanp.getFirstName());
	        profile.setLastName(beanp.getLastName());
	        profile.setCreated(beanp.getCreated());
	        
	        session.save(profile);
	    }
	    
	    public List<Profile> getProfiles(){
	        Session session = SessionUtil.getSession();    
	        Query query = session.createQuery("from profiles");
	        List<Profile> profiles =  query.list();
	        return profiles;
	    }
	 
	    public int deleteProfile(int id) {
	        Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "delete from profiles where id = :id";
	        Query query = session.createQuery(hql);
	        query.setInteger("id",id);
	        int rowCount = query.executeUpdate();
	        System.out.println("Rows affected: " + rowCount);
	        tx.commit();
	        session.close();
	        return rowCount;
	    }
	    
	    public int updateProfile(int id, Profile prl){
	         if(id <=0)  
	               return 0;  
	         Session session = SessionUtil.getSession();
	            Transaction tx = session.beginTransaction();
	            String hql = "update profiles set profileName = :profileName, firstName=:firstName, lastName=:lastName, created =: created where id = :id";
	            Query query = session.createQuery(hql);
	            query.setInteger("id",id);
	            query.setString("profileName",prl.getProfileName());
	            query.setString("FirstName",prl.getFirstName());
	            query.setString("author",prl.getLastName());
	            int rowCount = query.executeUpdate();
	            System.out.println("Rows affected: " + rowCount);
	            tx.commit();
	            session.close();
	            return rowCount;
	    }

}
