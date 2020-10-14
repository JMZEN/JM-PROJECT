package jm.stockx.api.dao;

import jm.stockx.dto.user.UserDto;
import jm.stockx.entity.User;

import javax.persistence.Tuple;
import java.util.List;

public interface UserDAO extends GenericDao<User, Long> {
    UserDto getUserDtoByUserUsername(String name);

    UserDto getUserDtoByUserEmail(String name);

    UserDto getUserDtoByUserAppleId(String appleId);

    UserDto getUserDtoByUserId(Long id);

    User getUserByUsername(String username);

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<Tuple> getPurchaseStatisticsByUserId(Long id);
}
