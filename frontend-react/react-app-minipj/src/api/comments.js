import { get, post, del } from "./client";

export const listComments = (boardId) => get(`/boards/${boardId}/comments`);
export const createComment = (boardId, content) => post(`/boards/${boardId}/comments`, { content });
export const deleteComment = (id) => del(`/comments/${id}`);
