package duan.server;

import duan.server.mapper.AdminMapper;
import duan.server.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {
    @Autowired
    private AdminMapper adminMapper;


    @Test
    public void TestMybatis(){
     adminMapper.deleteById(1);

    }



}
