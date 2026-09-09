package minipjt01.board.post;

import minipjt01.board.member.Member;

import java.io.Serializable;
import java.time.LocalDateTime;

//게시판 글
public class Post implements Serializable {

    private static Long sequence = 0L;
    private Long id;     //게시글 번호
    private Member writer; //글쓴이 회원번호
    private String title; //글제목
    private String content;  //글내용
    private int viewCount; // 조회수
    private int likeCount; // 좋아요수
    private LocalDateTime createdAt; // 글등록 일시
    private LocalDateTime updatedAt; // 글수정 일시
    private PostStatus status = PostStatus.NORMAL;

    public Post(Member writer, String title, String content) {
        this.id = ++sequence;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
        this.likeCount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Long getId() {
         return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s %s", id, title, writer, createdAt);
    }
}
