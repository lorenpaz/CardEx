package es.ucm.fdi.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Método para la configuración del acceso (dado por el profesor)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	
	/*The default configuration above:
		Ensures that any request to our application requires the user to be authenticated
		Allows users to authenticate with form based login*/
		
	.antMatchers("/static/**").permitAll()		// user can access a request if the URL starts with "/static/"
	.antMatchers("/admin/**").hasRole("ADMIN") //Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN"
	.anyRequest().authenticated() //Any URL that has not already been matched on only requires that the user be authenticated
	.and()
	.formLogin()
	.loginPage("/index")	//Localización de la página del LogIn
	.permitAll() //The formLogin().permitAll() method allows granting access to all users for all URLs associated with form based log in.
	.and()
	.logout()
	.logoutUrl("/logout")
	.permitAll();
}
	
	//Método con configuraciones globales (dado por el profesor)
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	throws Exception {
	auth.inMemoryAuthentication()
	.withUser("user").password("password").roles("USER")
	.and()
	.withUser("admin").password("password").roles("USER", "ADMIN");
	}
	
	/*		ESTO DE ABAJO SI LO USAMOS, HABRÍA QUE QUITAR EL MÉTODO ANTERIOR
	 * //You can define custom authentication with this method, por ejemplo con JPA
	@Bean
	public IwUserDetailsService springDataUserDetailsService() {
	// debe implementar UserDetailsService:
	// public UserDetails loadUserByUsername(String username)
	return new IwUserDetailsService();
	}
	// interfaz UserDetails: principales métodos
	// getUsername()
	// getPassword()
	// getAuthorities() -> lista de roles
	*/
}