package com.sist.secure_0224.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sist.secure_0224.service.MemberService;
import com.sist.secure_0224.vo.MemVO;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class MemberController {
    
    //DB 활용을 위한 service객체
    @Autowired
    private MemberService m_Service;

    // 로그인처리를 위한 세션
    @Autowired
    private HttpSession session;

    @RequestMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("reg")
    public ModelAndView reg(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reg");
        return mv;
    }

    @PostMapping("reg")
    public ModelAndView register(MemVO vo) {
        // 서비스를 이용하여 회원등록!
        int cnt = m_Service.regMember(vo);
        ModelAndView mv = new ModelAndView();

        if (cnt > 0) {
            mv.setViewName("index");
        } else {
            mv.setViewName("reg");
        }
        
        return mv;
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @PostMapping("reqLogin")
    public ModelAndView login(MemVO vo) {
        ModelAndView mv = new ModelAndView();
        MemVO mvo = m_Service.login(vo);
        
        //mvo가 null이면 아이디 또는 비밀번호가 틀린경우!
        if (mvo != null) {
            session.setAttribute("mvo", mvo);
        } else {
            mv.addObject("status", 1);
        }
        
        mv.setViewName("redirect:/");
        return mv;
    }
    
    
    
    
}
