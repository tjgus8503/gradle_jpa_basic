package seohyun.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seohyun.app.models.Users;
import seohyun.app.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
// 컨트롤러에서 가져다 쓸 코드 미리 선언하는 곳.
// 레포지토리에 접근하는 로직을 짜는 곳. 복잡한 로직을 짜는 곳. 컨트롤러에서 최대한 뺄 수 있으면 뺀 내용을 여기에다가 쓰는 것. 재사용성을 위해.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    // UserRepository 에서 가져온 값들을 userRepository 라는 이름으로 사용하겠다.
    private final UsersRepository usersRepository;

    public List<Users> getUsersAll() {
        return usersRepository.findAll();
    }
}
