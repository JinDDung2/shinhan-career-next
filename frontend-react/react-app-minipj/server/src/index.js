import express from "express";
import cors from "cors";
import cookieParser from "cookie-parser";
import { readDb } from "./db.js";
import authRouter from "./routes/auth.js";
import boardsRouter from "./routes/boards.js";
import { boardCommentsRouter, commentRouter } from "./routes/comments.js";

const PORT = process.env.PORT || 4000;

const app = express();
app.use(cors({ origin: true, credentials: true }));
app.use(express.json());
app.use(cookieParser());

app.use("/api/auth", authRouter);
app.use("/api/boards", boardsRouter);
app.use("/api/boards/:boardId/comments", boardCommentsRouter);
app.use("/api/comments", commentRouter);

app.get("/api/health", (_req, res) => {
  const db = readDb();
  res.json({
    status: "ok",
    membersCount: db.members.length,
    boardsCount: db.boards.length,
    commentsCount: db.comments.length,
  });
});

app.listen(PORT, () => {
  console.log(`json-server-board API listening on http://localhost:${PORT}`);
});
