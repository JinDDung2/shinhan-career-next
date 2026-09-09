package minipjt01.board.member;

import java.io.Serializable;

public class Member implements Serializable {

    private static Long sequence = 0L; // 사용자에 입력받지 않음, 프로그램 유일 값
    private Long id;
    private String email;
    private String password;
    private String nickname;

    public Member(String email, String password, String nickname) {
        this.id = ++sequence;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return nickname;
    }
}
