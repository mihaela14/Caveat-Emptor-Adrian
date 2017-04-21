package user.login;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.UserMapper;
import repository.entities.User;
import repository.queries.INamedQueryData;
import repository.queries.NamedQueryData;
import repository.queries.parameters.user.UserParameters;
import repository.repositories.user.IUserRepository;
import constants.ErrorMessages;
import dto.UserDTO;
import exceptions.user.UserException;

@Stateless
@Remote(ILoginService.class)
public class LoginService implements ILoginService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private IUserRepository iUserRepository;

	public LoginService() {
	}

	@Override
	public boolean isValidUserLoginData(String accountName, String password)
			throws UserException {

		UserDTO userDTO = findUser(accountName);

		return hasValidPassword(userDTO, password);
	}

	public UserDTO findUser(String accountName) throws UserException {

		INamedQueryData queryData = getQueryData(accountName);

		User user = iUserRepository.getSingleEntityByQueryData(queryData,
				entityManager);

		if (user == null) {
			throw new UserException(ErrorMessages.USER_NOT_FOUND.getDetails());
		} else {
			return UserMapper.getUserDTO(user);
		}
	}

	public boolean hasValidPassword(UserDTO userDTO, String password) {

		return userDTO.getPassword().equals(password);
	}

	private NamedQueryData getQueryData(String accountName) {

		UserParameters userParameters = new UserParameters.Builder()
				.withAccountName(accountName).build();

		Map<String, Object> parameters = userParameters.getParameters();

		return new NamedQueryData(User.QUERY_FIND_USER_WITH_ACCOUNT_NAME,
				parameters);
	}

}
