import { get, post } from "./client";

export const signup = (loginId, password) => post("/auth/signup", { loginId, password });
export const login = (loginId, password) => post("/auth/login", { loginId, password });
export const logout = () => post("/auth/logout");
export const me = () => get("/auth/me");
