package com.erp.api.security.request;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;


/**
 * @author TA Admin
 *
 * 
 */

@Setter
@Getter
public class SignupRequest  implements Serializable {
	
 private static final long serialVersionUID = 1L;

  private String username; 
  private String email;
  private Set<String> role;
  private String password;


}
