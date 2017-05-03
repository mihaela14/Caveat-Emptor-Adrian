package user.login.impl;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.UserMapper;
import repository.entities.User;
import repository.queries.NamedQueryData;
import repository.queries.impl.NamedQueryDataImpl;
import repository.queries.parameters.user.UserParameters;
import repository.repositories.user.UserRepository;
import user.login.LoginService;
import dto.UserDTO;
import exceptions.UserException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(LoginService.class)
public class LoginServiceImpl implements LoginService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private UserRepository userRepository;

	@Override
	public boolean isValidUserLoginData(String accountName, String password)
			throws UserException {

		UserDTO userDTO = findUser(accountName);

		return hasValidPassword(userDTO, password);
	}

	private UserDTO findUser(String accountName) throws UserException {

		NamedQueryData queryData = getQueryData(accountName);

		User user = userRepository.getSingleEntityByQueryData(queryData,
				entityManager);

		if (!user.isActivated()) {
			throw new UserException(
					ExceptionMessages.USER_NOT_ACTIVATED.getDetails());
		}

		return UserMapper.getUserDTO(user);
	}

	private boolean hasValidPassword(UserDTO userDTO, String password) {

		return userDTO.getPassword().equals(password);
	}

	private NamedQueryDataImpl getQueryData(String accountName) {

		UserParameters userParameters = new UserParameters.Builder()
				.withAccountName(accountName).build();

		Map<String, Object> parameters = userParameters.getParameters();

		return new NamedQueryDataImpl(User.QUERY_FIND_USER_WITH_ACCOUNT_NAME,
				parameters);
	}

}
