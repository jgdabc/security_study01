package jgdabc.controller;

import jgdabc.util.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @RequestMapping("/index")
    public @ResponseBody ResultResponse index()
    {
        System.out.println("q111");
        return ResultResponse.success("欢迎访问主页");
    }
    @PreAuthorize(value = "hasAnyAuthority('user:query')")
    @RequestMapping("/api/query")
    public @ResponseBody ResultResponse query(){
        System.out.println("qqqq");
       return new ResultResponse<>("user/query","sussess");

    }
    @PreAuthorize("hasAnyAuthority('user:add')")
    @RequestMapping("/api/add")
    public @ResponseBody ResultResponse add()
    {
        return  new ResultResponse<>("user/add");
    }






}
