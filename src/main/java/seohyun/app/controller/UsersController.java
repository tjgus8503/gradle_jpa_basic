package seohyun.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import seohyun.app.models.Users;
import seohyun.app.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Api 통신하는 로직 만드는  ,Controller 는 최대한 복잡한 로직을 뺄 수 있으면 빼야하고 필요한 내용만 있는게 좋음.
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UsersService usersservice;

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
    // 디비에 있는 데이터들을 전부다 포스트 맨으로 보내기
    @GetMapping("/getallusers")
    public ResponseEntity<Object> GetAllUsers() throws Exception {
        try{
            List<Users> usersList = usersservice.getUsersAll();
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("error", e.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }


}
