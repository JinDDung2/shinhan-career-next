import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function Header() {
  const { member, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = async () => {
    await logout();
    navigate("/");
  };

  return (
    <header className="app-header">
      <Link to="/" className="brand">
        삼성전자 주식 커뮤니티
      </Link>
      <nav className="nav">
        {member ? (
          <>
            <span className="nickname">{member.nickname}님</span>
            <Link to="/boards/new">글쓰기</Link>
            <button type="button" onClick={handleLogout}>
              로그아웃
            </button>
          </>
        ) : (
          <>
            <Link to="/login">로그인</Link>
            <Link to="/signup">회원가입</Link>
          </>
        )}
      </nav>
    </header>
  );
}
