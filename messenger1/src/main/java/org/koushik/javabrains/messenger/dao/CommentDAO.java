package org.koushik.javabrains.messenger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.koushik.javabrains.messenger.model.Comment;


public class CommentDAO {
	
	public void addComment(Comment beanc){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addComment(session,beanc);        
        tx.commit();
        session.close();
        
    }
    
    private void addComment(Session session, Comment beanc){
        Comment comment = new Comment();
        
        comment.setMessage(beanc.getMessage());
        comment.setCreated(beanc.getCreated());
        comment.setAuthor(beanc.getAuthor());
        
        session.save(comment);
    }
    
    public List<Comment> getComment(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from comments");
        List<Comment> comments =  query.list();
        return comments;
    }
 
    public int deleteComment(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from comments where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateComment(int id, Comment cmt){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update comments set message = :message, author=:author, created =: created where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("message",cmt.getMessage());
            query.setDate("created",cmt.getCreated());
            query.setString("author",cmt.getAuthor());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }

}
