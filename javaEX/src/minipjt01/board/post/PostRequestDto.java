package minipjt01.board.post;

import minipjt01.board.member.Member;

public class PostRequestDto {
    private String title; //글제목
    private String content;  //글내용

    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toPost(Member member) {
        return new Post(member, title, content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
