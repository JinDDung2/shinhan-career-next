import jwt from "jsonwebtoken";

export const COOKIE_NAME = "token";
const JWT_SECRET = process.env.JWT_SECRET || "dev-secret-please-change";
const COOKIE_MAX_AGE_MS = 7 * 24 * 60 * 60 * 1000; // 7일

if (!process.env.JWT_SECRET) {
  console.warn("[auth] JWT_SECRET 환경변수가 없어 개발용 기본 시크릿을 사용합니다.");
}

export function issueToken(memberId) {
  return jwt.sign({ memberId }, JWT_SECRET, { expiresIn: "7d" });
}

export function setAuthCookie(res, token) {
  res.cookie(COOKIE_NAME, token, {
    httpOnly: true,
    sameSite: "lax",
    maxAge: COOKIE_MAX_AGE_MS,
  });
}

export function clearAuthCookie(res) {
  res.clearCookie(COOKIE_NAME);
}

export function requireAuth(req, res, next) {
  const token = req.cookies?.[COOKIE_NAME];
  if (!token) {
    return res.status(401).json({ message: "로그인이 필요합니다." });
  }

  try {
    const payload = jwt.verify(token, JWT_SECRET);
    req.memberId = payload.memberId;
    next();
  } catch {
    return res.status(401).json({ message: "로그인이 필요합니다." });
  }
}
