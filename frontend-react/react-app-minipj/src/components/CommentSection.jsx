import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { createComment, deleteComment, listComments } from "../api/comments";

export default function CommentSection({ boardId }) {
  const { member } = useAuth();
  const [comments, setComments] = useState([]);
  const [content, setContent] = useState("");
  const [error, setError] = useState("");
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    listComments(boardId)
      .then(setComments)
      .catch((err) => setError(err.message));
  }, [boardId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!content.trim()) return;

    setSubmitting(true);
    setError("");
    try {
      const comment = await createComment(boardId, content.trim());
      setComments((prev) => [...prev, comment]);
      setContent("");
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async (commentId) => {
    if (!window.confirm("이 댓글을 삭제할까요?")) return;
    try {
      await deleteComment(commentId);
      setComments((prev) => prev.filter((c) => c.id !== commentId));
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <section className="comment-section">
      <h2>댓글 {comments.length}</h2>

      <ul className="comment-list">
        {comments.map((comment) => (
          <li key={comment.id} className="comment-item">
            <div className="comment-meta">
              <span className="comment-nickname">{comment.nickname}</span>
              <span>{new Date(comment.createdAt).toLocaleString()}</span>
            </div>
            <p className="comment-content">{comment.content}</p>
            {member?.id === comment.memberId && (
              <button type="button" className="comment-delete" onClick={() => handleDelete(comment.id)}>
                삭제
              </button>
            )}
          </li>
        ))}
      </ul>

      {comments.length === 0 && <p className="empty">아직 댓글이 없어요.</p>}

      {error && <p className="error">{error}</p>}

      {member ? (
        <form className="comment-form" onSubmit={handleSubmit}>
          <input
            type="text"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="댓글을 입력하세요"
          />
          <button type="submit" className="btn-primary" disabled={submitting}>
            등록
          </button>
        </form>
      ) : (
        <p className="hint">댓글을 작성하려면 로그인해주세요.</p>
      )}
    </section>
  );
}
