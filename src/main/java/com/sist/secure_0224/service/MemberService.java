package com.sist.secure_0224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.secure_0224.mapper.MemberMapper;
import com.sist.secure_0224.vo.MemVO;

@Service
public class MemberService {
    @Autowired
    private MemberMapper m_Mapper;

    @Autowired
    private PasswordEncoder passwordEncoder; // SHA-256를 내장하고 있음
    // 복호화 기능이 없음
    
    // 회원등록 
    public int regMember(MemVO vo){
        // reg.jsp에서 전달되는 m_id, m_pw, m_name이 컨트롤러에서 vo로 
        // 받은 것을 그대로 인자로 받아 현재 메서드로 전달된다.
        // 이중 비밀번호를 암호화 하자!
        String pw = passwordEncoder.encode(vo.getM_pw());
        vo.setM_pw(pw);
        // vo.setM_pw((passwordEncoder.encode(vo.getM_pw()))); 이렇게 한줄로 줄일 수 있음.
        return m_Mapper.reg(vo);

    }

    public MemVO login(MemVO mv){
        // DB로로부터 mv에 있는 m_id를 보내어 
        // 해당 MemVO를 받아서 반환 받는다.
        // 이때 비밀번호가 일치하는지는 
        // passwordEncode에게 물어봐야 한다.
        MemVO mvo = m_Mapper.login(mv.getM_id());

        // 사용자가 입력한 아이디가 잘 못 되었다면
        // mvo에는 null이 저장된다.
        if (mvo != null) {
            // 여기서는 아이디는 잘 입력되었는지 이제는
            // 비밀번호가 일치한지?를 물어보야 한다.
            if (passwordEncoder.matches(mv.getM_pw(), mvo.getM_pw())) 
                return mvo;
            
        }
        // mvo가 null이거나 비밀번호가 일치하지 않을 경우에 수행하는 곳
        return null;
    }

    
}
