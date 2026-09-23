import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { listBoards } from "../api/boards";

export default function BoardListPage() {
  const [items, setItems] = useState([]);
  const [nextCursor, setNextCursor] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    listBoards()
      .then((data) => {
        setItems(data.items);
        setNextCursor(data.nextCursor);
      })
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  const loadMore = async () => {
    setLoading(true);
    try {
      const data = await listBoards({ cursor: nextCursor });
      setItems((prev) => [...prev, ...data.items]);
      setNextCursor(data.nextCursor);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="page">
      <div className="page-header">
        <h1>게시글 목록</h1>
        <Link to="/boards/new" className="btn-primary">
          글쓰기
        </Link>
      </div>

      {error && <p className="error">{error}</p>}

      <ul className="board-list">
        {items.map((board) => (
          <li key={board.id} className="board-list-item">
            <Link to={`/boards/${board.id}`} className="board-title">
              {board.title}
            </Link>
            <div className="board-meta">
              <span>{board.nickname}</span>
              <span>조회 {board.viewCount}</span>
              <span>{new Date(board.createdAt).toLocaleDateString()}</span>
            </div>
          </li>
        ))}
      </ul>

      {items.length === 0 && !loading && <p className="empty">등록된 글이 없어요.</p>}

      {nextCursor != null && (
        <button type="button" className="btn-secondary load-more" onClick={loadMore} disabled={loading}>
          {loading ? "불러오는 중..." : "더보기"}
        </button>
      )}
    </div>
  );
}
