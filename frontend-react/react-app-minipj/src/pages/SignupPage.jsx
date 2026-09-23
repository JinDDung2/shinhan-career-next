import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function SignupPage() {
  const { signup } = useAuth();
  const navigate = useNavigate();
  const [loginId, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [submitting, setSubmitting] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSubmitting(true);
    try {
      const member = await signup(loginId, password);
      navigate("/login", { state: { signupNickname: member.nickname } });
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="page page-narrow">
      <h1>회원가입</h1>
      <form className="form" onSubmit={handleSubmit}>
        <label className="field">
          <span>아이디</span>
          <input
            type="text"
            value={loginId}
            onChange={(e) => setLoginId(e.target.value)}
            placeholder="영문 소문자 + 숫자, 6~24자"
            autoComplete="username"
            required
          />
        </label>
        <label className="field">
          <span>비밀번호</span>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="8~24자, 대/소문자·숫자·특수문자 중 2종류 이상"
            autoComplete="new-password"
            required
          />
        </label>
        <p className="hint">닉네임은 가입 시 자동으로 생성돼요.</p>
        {error && <p className="error">{error}</p>}
        <button type="submit" className="btn-primary" disabled={submitting}>
          {submitting ? "가입 중..." : "회원가입"}
        </button>
      </form>
    </div>
  );
}
