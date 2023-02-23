package com.jproject.my_cars.domain.board.member_board;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.dto.BoardWriteDto;
import com.jproject.my_cars.dto.MemberBoardModifyDto;
import com.jproject.my_cars.dto.MemberWriteBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberBoardService {
    private final MemberBoardRepository memberBoardRepository;
    private final MemberRepository memberRepository;

    public List<MemberBoard> memberBoardList(){
        return memberBoardRepository.findAll();
    }

    @Transactional
    public void save(MemberWriteBoardDto dto) {
        MemberBoard memberBoard = MemberBoard.writeMemberBoard(dto);
        Member member = memberRepository.findById((long) dto.getNum()).get();
        memberBoard.setMember(member);
        memberBoardRepository.save(memberBoard);
    }

    public MemberBoard findByNum(long num) {
        return memberBoardRepository.findById(num).get();
    }

    @Transactional
    public void modifyBoard(long num, MemberBoardModifyDto dto) {
        MemberBoard memberBoard = memberBoardRepository.findById(num).get();
        memberBoard.modifyMemberBoard(dto);
    }

    @Transactional
    public void deleteBoard(long num) {
        memberBoardRepository.deleteById(num);
    }

    public List<MemberBoard> getMemberBoardList() {
        return memberBoardRepository.findAll();
    }
}
