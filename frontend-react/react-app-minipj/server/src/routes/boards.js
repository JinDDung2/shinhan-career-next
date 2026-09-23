import { Router } from "express";
import { readDb, writeDb } from "../db.js";
import { requireAuth } from "../authUtils.js";
import { nextId } from "../nextId.js";

const router = Router();

function nicknameOf(db, memberId) {
  const member = db.members.find((m) => m.id === memberId);
  return member ? member.nickname : "탈퇴한 회원";
}

function toListItem(db, board) {
  return {
    id: board.id,
    title: board.title,
    nickname: nicknameOf(db, board.memberId),
    viewCount: board.viewCount,
    createdAt: board.createdAt,
  };
}

function toDetail(db, board) {
  return {
    id: board.id,
    title: board.title,
    content: board.content,
    nickname: nicknameOf(db, board.memberId),
    memberId: board.memberId,
    viewCount: board.viewCount,
    createdAt: board.createdAt,
    updatedAt: board.updatedAt,
  };
}

router.get("/", (req, res) => {
  const size = Math.min(Math.max(parseInt(req.query.size, 10) || 10, 1), 50);
  const cursor = req.query.cursor !== undefined ? parseInt(req.query.cursor, 10) : null;

  const db = readDb();
  let boards = db.boards.filter((b) => !b.isDeleted).sort((a, b) => b.id - a.id);

  if (cursor !== null && !Number.isNaN(cursor)) {
    boards = boards.filter((b) => b.id < cursor);
  }

  const page = boards.slice(0, size);
  const nextCursor = page.length === size ? page[page.length - 1].id : null;

  res.json({
    items: page.map((b) => toListItem(db, b)),
    nextCursor,
  });
});

router.get("/:id", (req, res) => {
  const id = Number(req.params.id);
  const db = readDb();
  const board = db.boards.find((b) => b.id === id && !b.isDeleted);

  if (!board) {
    return res.status(404).json({ message: "게시글을 찾을 수 없습니다." });
  }

  board.viewCount += 1;
  writeDb(db);

  res.json(toDetail(db, board));
});

router.post("/", requireAuth, (req, res) => {
  const { title, content } = req.body ?? {};

  if (typeof title !== "string" || !title.trim() || typeof content !== "string" || !content.trim()) {
    return res.status(400).json({ message: "제목과 내용을 입력해주세요." });
  }

  const db = readDb();
  const now = new Date().toISOString();
  const board = {
    id: nextId(db.boards),
    memberId: req.memberId,
    title: title.trim(),
    content: content.trim(),
    viewCount: 0,
    createdAt: now,
    updatedAt: now,
    isDeleted: false,
    deletedAt: null,
  };

  db.boards.push(board);
  writeDb(db);

  res.status(201).json(toDetail(db, board));
});

router.patch("/:id", requireAuth, (req, res) => {
  const id = Number(req.params.id);
  const db = readDb();
  const board = db.boards.find((b) => b.id === id && !b.isDeleted);

  if (!board) {
    return res.status(404).json({ message: "게시글을 찾을 수 없습니다." });
  }

  if (board.memberId !== req.memberId) {
    return res.status(403).json({ message: "본인 글만 수정할 수 있습니다." });
  }

  const { title, content } = req.body ?? {};
  if (typeof title !== "string" || !title.trim() || typeof content !== "string" || !content.trim()) {
    return res.status(400).json({ message: "제목과 내용을 입력해주세요." });
  }

  board.title = title.trim();
  board.content = content.trim();
  board.updatedAt = new Date().toISOString();
  writeDb(db);

  res.json({ id: board.id, title: board.title, content: board.content, updatedAt: board.updatedAt });
});

router.delete("/:id", requireAuth, (req, res) => {
  const id = Number(req.params.id);
  const db = readDb();
  const board = db.boards.find((b) => b.id === id && !b.isDeleted);

  if (!board) {
    return res.status(404).json({ message: "게시글을 찾을 수 없습니다." });
  }

  if (board.memberId !== req.memberId) {
    return res.status(403).json({ message: "본인 글만 삭제할 수 있습니다." });
  }

  board.isDeleted = true;
  board.deletedAt = new Date().toISOString();
  writeDb(db);

  res.status(204).end();
});

export default router;
