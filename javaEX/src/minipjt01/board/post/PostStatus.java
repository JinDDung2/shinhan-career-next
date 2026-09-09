package minipjt01.board.post;

public enum PostStatus {
    NORMAL("정상"),
    DELETED("삭제됨"),
    BLINDED("블라인드처리"); // 신고 누적 등으로 숨김

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }
    public boolean isVisible() {
        return this == NORMAL;
    }
}
