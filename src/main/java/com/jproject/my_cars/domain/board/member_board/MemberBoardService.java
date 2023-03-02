package com.jproject.my_cars.domain.board.member_board;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.dto.MemberBoardModifyDto;
import com.jproject.my_cars.dto.MemberWriteBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberBoardService {
    private final MemberBoardRepository memberBoardRepository;
    private final MemberRepository memberRepository;

    public Page<MemberBoard> memberBoardList(PageRequest pageRequest){
        return memberBoardRepository.findAll(pageRequest);
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

    public Long checkPrivateContentPassword(Long id,String password) {
        return memberBoardRepository.countByMemberBoardIdAndMemberBoardSecretPassword(id,password);
    }
    public Page<MemberBoard> getLikesTitleList(String title,PageRequest pageRequest){
        return memberBoardRepository.findByLikeTitle(title,pageRequest);
    }
//    @Transactional
//    public void saveTest(int num){
//        MemberBoard.builder()
//                .member()
//    }
}
