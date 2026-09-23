import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createBoard, getBoard, updateBoard } from "../api/boards";
import { useAuth } from "../context/AuthContext";

export default function BoardFormPage() {
  const { id } = useParams();
  const isEdit = Boolean(id);
  const navigate = useNavigate();
  const { member } = useAuth();

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(isEdit);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    if (!isEdit) return;
    getBoard(id)
      .then((board) => {
        setTitle(board.title);
        setContent(board.content);
      })
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, [id, isEdit]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSubmitting(true);
    try {
      if (isEdit) {
        await updateBoard(id, { title, content });
        navigate(`/boards/${id}`);
      } else {
        const board = await createBoard({ title, content });
        navigate(`/boards/${board.id}`);
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  };

  if (!member) {
    return (
      <div className="page page-narrow">
        <p>로그인이 필요합니다.</p>
      </div>
    );
  }

  if (loading) {
    return <div className="page page-narrow">불러오는 중...</div>;
  }

  return (
    <div className="page page-narrow">
      <h1>{isEdit ? "글 수정" : "글쓰기"}</h1>
      <form className="form" onSubmit={handleSubmit}>
        <label className="field">
          <span>제목</span>
          <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
        </label>
        <label className="field">
          <span>내용</span>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows={10}
            required
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit" className="btn-primary" disabled={submitting}>
          {submitting ? "저장 중..." : "저장"}
        </button>
      </form>
    </div>
  );
}
