package user.login;

import static exceptions.login.InvalidPasswordException.INVALID_PASSWORD_MESSAGE;
import static exceptions.login.UserNotFoundException.USER_NOT_FOUND_MESSAGE;

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
import repository.repositories.user.IUserRepository;
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

	public LoginService() {
	}

	@Override
	public boolean hasValidCredentials(String accountName, String password)
			throws UserNotFoundException, InvalidPasswordException {

		UserDTO userDTO = findUser(accountName);

		return hasValidPassword(userDTO, password);
	}

	public UserDTO findUser(String accountName) throws UserNotFoundException {

		INamedQueryData queryData = getAccountNameQueryData(accountName);

		User user = iUserRepository.getSingleEntityByQueryData(queryData,
				entityManager);

		if (user == null) {
			throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE);
		} else {
			return new ModelMapper().map(user, UserDTO.class);
		}
	}

	public boolean hasValidPassword(UserDTO userDTO, String password)
			throws InvalidPasswordException {

		boolean isValidPassword = userDTO.getPassword().equals(password);

		if (isValidPassword) {
			return true;
		} else {
			throw new InvalidPasswordException(INVALID_PASSWORD_MESSAGE);
		}
	}

	private NamedQueryData getAccountNameQueryData(String accountName) {

		Map<String, String> parameters = UserQueryParametersBuilder
				.buildWithAccountName(accountName);

		return new NamedQueryData(User.QUERY_FIND_USER_WITH_ACCOUNT_NAME,
				parameters);
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
