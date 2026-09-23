import { Router } from "express";
import bcrypt from "bcryptjs";
import { readDb, writeDb } from "../db.js";
import { validateLoginId, validatePassword } from "../validators.js";
import { generateUniqueNickname } from "../nickname.js";
import { issueToken, setAuthCookie, clearAuthCookie, requireAuth } from "../authUtils.js";
import { nextId } from "../nextId.js";

const router = Router();

function toPublicMember(member) {
  return { id: member.id, loginId: member.loginId, nickname: member.nickname, createdAt: member.createdAt };
}

router.post("/signup", (req, res) => {
  const { loginId, password } = req.body ?? {};

  const loginIdCheck = validateLoginId(loginId);
  if (!loginIdCheck.valid) {
    return res.status(400).json({ message: loginIdCheck.message });
  }

  const passwordCheck = validatePassword(password);
  if (!passwordCheck.valid) {
    return res.status(400).json({ message: passwordCheck.message });
  }

  const db = readDb();

  const duplicate = db.members.some((member) => member.loginId === loginId);
  if (duplicate) {
    return res.status(409).json({ message: "이미 사용 중인 아이디입니다." });
  }

  const nickname = generateUniqueNickname(db.members.map((member) => member.nickname));

  const newMember = {
    id: nextId(db.members),
    loginId,
    password: bcrypt.hashSync(password, 10),
    nickname,
    createdAt: new Date().toISOString(),
    isDeleted: false,
    deletedAt: null,
  };

  db.members.push(newMember);
  writeDb(db);

  res.status(201).json(toPublicMember(newMember));
});

router.post("/login", (req, res) => {
  const { loginId, password } = req.body ?? {};

  if (typeof loginId !== "string" || typeof password !== "string") {
    return res.status(400).json({ message: "아이디와 비밀번호를 입력해주세요." });
  }

  const db = readDb();
  const member = db.members.find((m) => m.loginId === loginId && !m.isDeleted);

  if (!member || !bcrypt.compareSync(password, member.password)) {
    return res.status(401).json({ message: "아이디 또는 비밀번호가 올바르지 않습니다." });
  }

  const token = issueToken(member.id);
  setAuthCookie(res, token);

  res.json(toPublicMember(member));
});

router.post("/logout", requireAuth, (_req, res) => {
  clearAuthCookie(res);
  res.status(204).end();
});

router.get("/me", requireAuth, (req, res) => {
  const db = readDb();
  const member = db.members.find((m) => m.id === req.memberId && !m.isDeleted);

  if (!member) {
    return res.status(401).json({ message: "로그인이 필요합니다." });
  }

  res.json(toPublicMember(member));
});

export default router;
