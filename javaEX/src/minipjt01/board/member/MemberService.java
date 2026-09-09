package minipjt01.board.member;


import java.util.List;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService() {
        this(new MemberRepository());
    }

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 등록
    public Long createMember(MemberRequestDto req) {
        if (memberRepository.findByEmail(req.getEmail()) != null) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = req.toMember();
        if (!memberRepository.save(member)) {
            return null;
        }

        return member.getId();
    }

    // 회원번호로 회원 검색
    public Member getMember(Long postId) {
        return memberRepository.findById(postId);
    }

    // 회원 이메일 로 회원 검색
    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }

    // 전체 회원 검색
    public List<Member> getMember() {
        return memberRepository.findAll();
    }

    public boolean saveToFile() {
        return memberRepository.saveToFile();
    }

    public boolean loadToFile() {
        return memberRepository.loadToFile();
    }

}
