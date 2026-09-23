import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { deleteBoard, getBoard } from "../api/boards";
import { useAuth } from "../context/AuthContext";

export default function BoardDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { member } = useAuth();

  const [board, setBoard] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    getBoard(id)
      .then(setBoard)
      .catch((err) => setError(err.message));
  }, [id]);

  const handleDelete = async () => {
    if (!window.confirm("이 글을 삭제할까요?")) return;
    try {
      await deleteBoard(id);
      navigate("/");
    } catch (err) {
      setError(err.message);
    }
  };

  if (error) {
    return (
      <div className="page">
        <p className="error">{error}</p>
        <Link to="/">목록으로</Link>
      </div>
    );
  }

  if (!board) {
    return <div className="page">불러오는 중...</div>;
  }

  const isOwner = member?.id === board.memberId;

  return (
    <div className="page">
      <h1>{board.title}</h1>
      <div className="board-meta">
        <span>{board.nickname}</span>
        <span>조회 {board.viewCount}</span>
        <span>{new Date(board.createdAt).toLocaleString()}</span>
      </div>
      <p className="board-content">{board.content}</p>

      {isOwner && (
        <div className="button-row">
          <Link to={`/boards/${board.id}/edit`} className="btn-secondary">
            수정
          </Link>
          <button type="button" className="btn-danger" onClick={handleDelete}>
            삭제
          </button>
        </div>
      )}

      <Link to="/" className="back-link">
        목록으로
      </Link>
    </div>
  );
}
