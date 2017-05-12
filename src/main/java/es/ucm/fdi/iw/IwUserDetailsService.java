package es.ucm.fdi.iw;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import es.ucm.fdi.iw.model.Usuario;

//You can define custom authentication with this method, por ejemplo con JPA
public class IwUserDetailsService implements UserDetailsService {

	private static Logger log = Logger.getLogger(IwUserDetailsService.class);

    private EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }

    public UserDetails loadUserByUsername(String username){
		log.info("Looking up: " + username);

    	try {
	        Usuario u = (Usuario) entityManager.createNamedQuery("userByUserField")
					.setParameter("userParam", username).getSingleResult();
	       
	        // build UserDetails object
	        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
	        for (String r : u.getRoles().split("[,]")) {
	        	roles.add(new SimpleGrantedAuthority("ROLE_" + r));
		        log.info("Roles for " + username + " include " + roles.get(roles.size()-1));
	        }
	        return new org.springframework.security.core.userdetails.User(
	        		u.getUsuario(), u.getContraseña(), roles); 
	    } catch (Exception e) {
    		log.info("No such user: " + username);
    		throw new UsernameNotFoundException("No such user: " + username);
    	}
    }
}