package com.mindgate.main.serviceimpl;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Login;
import com.mindgate.main.repository.LoginRepository;
import com.mindgate.main.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

	@Override
	public Login login(Login login) {
		Login login1 = loginRepository.login(login);
		System.out.println("Inside Login Service");
		return login1;
	}

}
