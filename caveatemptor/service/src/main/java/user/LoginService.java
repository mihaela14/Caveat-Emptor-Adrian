package user;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;

import repository.entities.User;
import repository.queries.INamedQueryData;
import repository.queries.NamedQueryData;
import repository.queries.parameters.user.UserQueryParametersBuilder;
import repository.repositories.IUserRepository;
import dto.UserDTO;
import exceptions.login.InvalidPasswordException;
import exceptions.login.UserNotFoundException;

@Stateless
@Remote(ILoginService.class)
public class LoginService implements ILoginService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private IUserRepository iUserRepository;

	private final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User not found: invalid account name.";

	private final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "Received invalid password.";

	public LoginService() {
	}

	@Override
	public UserDTO findUser(String accountName) throws UserNotFoundException {

		INamedQueryData queryData = getAccountNameQueryData(accountName);

		User user = iUserRepository.getSingleEntityByQueryData(queryData, entityManager);

		if (user == null) {
			throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
		} else {
			return new ModelMapper().map(user, UserDTO.class);
		}
	}

	private NamedQueryData getAccountNameQueryData(String accountName) {

		Map<String, String> parameters = UserQueryParametersBuilder.buildWithAccountName(accountName);

		return new NamedQueryData(User.QUERY_FIND_USER_WITH_ACCOUNT_NAME, parameters);
	}

	@Override
	public boolean hasValidPassword(UserDTO userDTO, String password) throws InvalidPasswordException {
		boolean isValidPassword = userDTO.getPassword().equals(password);

		if (isValidPassword) {
			return true;
		} else {
			throw new InvalidPasswordException(INVALID_PASSWORD_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public boolean hasValidCredentials(String accountName, String password)
			throws UserNotFoundException, InvalidPasswordException {
		UserDTO userDTO = findUser(accountName);
		return hasValidPassword(userDTO, password);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public IUserRepository getUserRepository() {
		return iUserRepository;
	}

	public void setUserRepository(IUserRepository iRepository) {
		this.iUserRepository = iRepository;
	}

}
