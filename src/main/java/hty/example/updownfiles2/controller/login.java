package hty.example.updownfiles2.controller;
import hty.example.updownfiles2.entity.Result.Result;
import hty.example.updownfiles2.entity.Result.ResultCode;
import hty.example.updownfiles2.mapper.FileInfoMapper;
import hty.example.updownfiles2.mapper.UserInfoMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hty.example.updownfiles2.entity.person.user;

import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class login {
    @Autowired
    private UserInfoMapper uim;
    @RequestMapping(value = "/mylogin",method = RequestMethod.POST)
    public String mylogin(HttpServletResponse response,String name, String password)
    {
        Random r = new Random();
        int suffix =  r.nextInt(11)+10;
        String uid = name+suffix;
        System.out.println(name + ":" + password);
        user usr = new user(uid,name,password);
        int idx = uim.insert(usr);
        if(idx > 0)
        {
            //return new Result(ResultCode.SUCCESS.code, "SUCCESS",idx);
            return "SUCCESS";
        }
        //return new Result(ResultCode.SUCCESS.code, "FAILED",idx);
        return "FAILED";
    }
}
