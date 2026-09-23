import { Router } from "express";
import { readDb, writeDb } from "../db.js";
import { requireAuth } from "../authUtils.js";
import { nextId } from "../nextId.js";

export const boardCommentsRouter = Router({ mergeParams: true });
export const commentRouter = Router();

function nicknameOf(db, memberId) {
  const member = db.members.find((m) => m.id === memberId);
  return member ? member.nickname : "탈퇴한 회원";
}

function toComment(db, comment) {
  return {
    id: comment.id,
    content: comment.content,
    nickname: nicknameOf(db, comment.memberId),
    memberId: comment.memberId,
    createdAt: comment.createdAt,
  };
}

boardCommentsRouter.get("/", (req, res) => {
  const boardId = Number(req.params.boardId);
  const db = readDb();
  const board = db.boards.find((b) => b.id === boardId && !b.isDeleted);

  if (!board) {
    return res.status(404).json({ message: "게시글을 찾을 수 없습니다." });
  }

  const comments = db.comments
    .filter((c) => c.boardId === boardId && !c.isDeleted)
    .sort((a, b) => a.id - b.id)
    .map((c) => toComment(db, c));

  res.json(comments);
});

boardCommentsRouter.post("/", requireAuth, (req, res) => {
  const boardId = Number(req.params.boardId);
  const db = readDb();
  const board = db.boards.find((b) => b.id === boardId && !b.isDeleted);

  if (!board) {
    return res.status(404).json({ message: "게시글을 찾을 수 없습니다." });
  }

  const { content } = req.body ?? {};
  if (typeof content !== "string" || !content.trim()) {
    return res.status(400).json({ message: "댓글 내용을 입력해주세요." });
  }

  const comment = {
    id: nextId(db.comments),
    boardId,
    memberId: req.memberId,
    content: content.trim(),
    createdAt: new Date().toISOString(),
    isDeleted: false,
    deletedAt: null,
  };

  db.comments.push(comment);
  writeDb(db);

  res.status(201).json(toComment(db, comment));
});

commentRouter.delete("/:id", requireAuth, (req, res) => {
  const id = Number(req.params.id);
  const db = readDb();
  const comment = db.comments.find((c) => c.id === id && !c.isDeleted);

  if (!comment) {
    return res.status(404).json({ message: "댓글을 찾을 수 없습니다." });
  }

  if (comment.memberId !== req.memberId) {
    return res.status(403).json({ message: "본인 댓글만 삭제할 수 있습니다." });
  }

  comment.isDeleted = true;
  comment.deletedAt = new Date().toISOString();
  writeDb(db);

  res.status(204).end();
});
