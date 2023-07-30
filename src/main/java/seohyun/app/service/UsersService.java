package seohyun.app.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import seohyun.app.models.Users;
import seohyun.app.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
// 컨트롤러에서 가져다 쓸 코드 미리 선언하는 곳.
// 레포지토리에 접근하는 로직을 짜는 곳. 복잡한 로직을 짜는 곳. 컨트롤러에서 최대한 뺄 수 있으면 뺀 내용을 여기에다가 쓰는 것. 재사용성을 위해.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    // UserRepository 에서 가져온 값들을 userRepository 라는 이름으로 사용하겠다.
    private final UsersRepository usersRepository;
    private final UsersRepositoryImpl usersRepositoryImpl;

    public List<Users> getUsersAll() throws Exception {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Users getUser(String id) throws Exception {
        try {
            return usersRepository.findOneById(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Boolean CheckUserId(Users users) throws Exception {
        try {
            return usersRepository.existsByUserId(users.getUserId());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional // create, update, delete 할때는 넣기.
    public void createUser(Users users) throws Exception {
        try{
            usersRepository.save(users);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
    public void signUp(Users users) throws Exception {
        try{
            usersRepository.save(users);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Transactional
    public Users signIn(Users req) throws Exception {
        try{
            return usersRepository.findOneByUserId(req.getUserId());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public Users CheckUserIdAndPassword(Users users) throws Exception {
        try{
            return usersRepository.findByUserIdAndPassword(users.getUserId(), users.getPassword());
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Transactional
    public void update(Users users) throws Exception {
        try{
            usersRepository.save(users);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Transactional
    public void unRegister(Users users) throws Exception {
        try{
            usersRepository.deleteById(users.getId());
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
