import { get, post, patch, del } from "./client";

export function listBoards({ cursor, size = 10 } = {}) {
  const params = new URLSearchParams();
  if (cursor != null) params.set("cursor", cursor);
  params.set("size", size);
  return get(`/boards?${params.toString()}`);
}

export const getBoard = (id) => get(`/boards/${id}`);
export const createBoard = ({ title, content }) => post("/boards", { title, content });
export const updateBoard = (id, { title, content }) => patch(`/boards/${id}`, { title, content });
export const deleteBoard = (id) => del(`/boards/${id}`);
