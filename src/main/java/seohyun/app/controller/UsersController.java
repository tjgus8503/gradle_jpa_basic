package seohyun.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import seohyun.app.models.Users;
import seohyun.app.service.UsersService;

import java.util.*;
import java.nio.ByteBuffer;

// Api 통신하는 로직 만드는  ,Controller 는 최대한 복잡한 로직을 뺄 수 있으면 빼야하고 필요한 내용만 있는게 좋음.
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/hello")
    public ResponseEntity<Object> Hello() throws Exception {
        try{
            Map<String, String> map = new HashMap<>();
            map.put("result", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch(Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
    // 디비에 있는 데이터들을 전부다 포스트 맨으로

    // read
    @GetMapping("/getallusers")
    public ResponseEntity<Object> GetAllUsers() throws Exception {
        try{
            List<Users> usersList = usersService.getUsersAll();
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/getuser")
    public ResponseEntity<Object> GetUser(
            @RequestParam String id) throws Exception {
        try{
            Users user = usersService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    // create id=4 user username= hihi, password=yoyo create
    @PostMapping("/createuser")
    public ResponseEntity<Object> CreateUser(
            @RequestBody Users users
    ) throws Exception {
        try{
            Map<String, String> map = new HashMap<>();

//            // id 생성
//            UUID uuid = UUID.randomUUID();
//            long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
//            String shortUUID = Long.toString(l, Character.MAX_RADIX);
//            // id값 varchar(20)으로 늘려놈
//            users.setId(shortUUID);

            Boolean asd = usersService.CheckUserId(users);
             if(asd == true){
                map.put("result", "failed 이미 있는 아이디 입니다. 다른 아이디를 입력해주세요.");
            }else{
                usersService.createUser(users);
                map.put("result", "success 성공적으로 등록이 완료되었습니다.");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(
            @RequestBody Users users
    ) throws Exception {
        try{
            Map<String, String> map = new HashMap<>();

            UUID uuid = UUID.randomUUID();
            users.setId(uuid.toString());

            Boolean user = usersService.CheckUserId(users);
            if (user == true) {
                map.put("result", "failed 이미 존재하는 아이디 입니다. 다른 아이디로 입력해주세요.");
            } else {
                usersService.signup(users);
                map.put("result", "success 성공적으로 등록이 완료되었습니다.");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(
            @RequestBody Users users) throws Exception {
        try{
            Map<String, String> map = new HashMap<>();
            Users user = usersService.signin(users);
            if (user != null) {
                map.put("result", "success 로그인 성공");
            } else {
                map.put("result", "failed 일치하는 정보가 없습니다.");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    // 회원정보 수정
    // 비밀번호를 다시한번 확인 해 아이디와 비밀번호가 일치하면 정보 수정 가능.
    @PostMapping("/update")
    public ResponseEntity<Object> update(
            @RequestBody Users users) throws Exception {
        try{
            Map<String, String> map = new HashMap<>();

            Users user = usersService.CheckUserIdAndPassword(users);
            if (user != null) {
                usersService.update(users);
                map.put("result", "success 수정이 완료되었습니다.");
            } else {
                map.put("result", "failed 비밀번호를 정확하게 입력해주세요.");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    // 회원탈퇴
    // 비밀번호를 다시한번 확인 해 아이디와 비밀번호가 일치하면 회원 탈퇴 가능.
    @PostMapping("/withdrawal")
    public ResponseEntity<Object> withdrawal(
            @RequestBody Users users) throws Exception {
        try{
            Map<String, String> map = new HashMap<>();

            Users user = usersService.CheckUserIdAndPassword(users);
            if (user != null) {
                usersService.withdrawal(users);
                map.put("result", "success 탈퇴가 완료되었습니다.");
            } else {
                map.put("result", "failed 비밀번호를 정확하게 입력해주세요.");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }












    // update id = 1 username = hi, password = hello

    // delete id = 2 delete

}
