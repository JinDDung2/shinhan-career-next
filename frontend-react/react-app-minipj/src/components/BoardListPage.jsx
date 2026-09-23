import { useCallback, useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { listBoards } from "../api/boards";

export default function BoardListPage() {
  const [items, setItems] = useState([]);
  const [nextCursor, setNextCursor] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const sentinelRef = useRef(null);

  useEffect(() => {
    listBoards()
      .then((data) => {
        setItems(data.items);
        setNextCursor(data.nextCursor);
      })
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  const loadMore = useCallback(async () => {
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
  }, [nextCursor]);

  useEffect(() => {
    if (!sentinelRef.current || nextCursor == null) return undefined;

    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting) {
          loadMore();
        }
      },
      { rootMargin: "200px" },
    );

    observer.observe(sentinelRef.current);
    return () => observer.disconnect();
  }, [nextCursor, loadMore]);

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

      <div ref={sentinelRef} className="scroll-sentinel">
        {loading && items.length > 0 && <p className="loading-more">불러오는 중...</p>}
        {nextCursor == null && items.length > 0 && <p className="end-of-list">마지막 글이에요.</p>}
      </div>
    </div>
  );
}
