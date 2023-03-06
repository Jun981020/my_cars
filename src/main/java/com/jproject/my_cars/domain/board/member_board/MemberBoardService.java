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

    //모든 게시글 Page 리턴
    public Page<MemberBoard> memberBoardList(PageRequest pageRequest){
        return memberBoardRepository.findAll(pageRequest);
    }

    @Transactional
    //게시글 저장
    public void save(MemberWriteBoardDto dto) {
        MemberBoard memberBoard = MemberBoard.writeMemberBoard(dto);
        Member member = memberRepository.findById((long) dto.getNum()).get();
        memberBoard.setMember(member);
        memberBoardRepository.save(memberBoard);
    }

    //게시글 번호로 게시글 조회
    public MemberBoard findByNum(long num) {
        return memberBoardRepository.findById(num).get();
    }

    @Transactional
    //게시글 수정
    public void modifyBoard(long num, MemberBoardModifyDto dto) {
        MemberBoard memberBoard = memberBoardRepository.findById(num).get();
        memberBoard.modifyMemberBoard(dto);
    }

    @Transactional
    //게시글 삭제
    public void deleteBoard(long num) {
        memberBoardRepository.deleteById(num);
    }

    //회원 페이지이 작성한 게시글 리스트로 리턴
    public List<MemberBoard> getMemberBoardList() {
        return memberBoardRepository.findAll();
    }

    //비밀글 비밀번호와 일치하는 정보 확인
    public Long checkPrivateContentPassword(Long id,String password) {
        return memberBoardRepository.countByMemberBoardIdAndMemberBoardSecretPassword(id,password);
    }
    //제목으로 검색하는 메서드
    public Page<MemberBoard> getLikesTitleList(String title,PageRequest pageRequest){
        return memberBoardRepository.findByLikeTitle(title,pageRequest);
    }
}
