package com.sist.secure_0224.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.secure_0224.vo.MemVO;

@Mapper
public interface MemberMapper {
    int reg(MemVO vo);

    MemVO login(String m_id);
}
