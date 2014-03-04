package jp.slm.business.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.slm.business.bean.User;
import jp.slm.business.dao.UserDao;
import jp.slm.business.dao.generic.impl.GenericLongIdBeanDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The Interface UserDao.
 */
@Repository
@SuppressWarnings("serial")
public class UserDaoImpl extends GenericLongIdBeanDaoImpl<User> implements UserDao
{
	private final static Map<String, User> DB = new HashMap<String, User>();
	static{
		User u = new User();
		u.setAdmin(true);
		u.setUncryptedPassword("admin");
		u.setEmail("admin");
		u.setBirthdate(new Date());
		u.setCreationDate(new Date());
		u.setFirstName("admin");
		u.setLastName("admin");
		u.setEnabled(true);
		u.setDescription("admin");
		u.setWebsiteUrl("www.admin.com");
		u.setPwdReset(false);
		u.setGender(true);
		u.setNickname("admin");
		DB.put("admin", u);
	}
	
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory, User.class);
    }
    

    @Override
	public User findByProperty(String propertyName, Object value) {
    	if(DB.containsKey(value)){
    		return DB.get(value);
    	}
		return super.findByProperty(propertyName, value);
	}
}
