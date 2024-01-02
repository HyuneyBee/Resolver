package sample.resolver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sample.resolver.customresolver.DecodeHeader;
import sample.resolver.customresolver.UserInfo;

@Controller
public class ResolverController {

    @GetMapping("/test")
    public ResponseEntity<String> resolverTest(@DecodeHeader UserInfo userInfo){
        System.out.println("user Id: " + userInfo.getId());
        System.out.println("user Name: " + userInfo.getName());
        return ResponseEntity.ok("ok");
    }
}
