package minipjt01.board.member;

public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;

    public MemberRequestDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Member toMember() {
        return new Member(email, password, nickname);
    }

    public String getEmail() {
        return email;
    }

    // 정규식으로 이메일 맞는지 확인
    // 비밀번호 정책에 맞는지 확인

}
