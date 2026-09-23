import { createContext, useContext, useEffect, useState } from "react";
import * as authApi from "../api/auth";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [member, setMember] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    authApi
      .me()
      .then(setMember)
      .catch(() => setMember(null))
      .finally(() => setLoading(false));
  }, []);

  const login = async (loginId, password) => {
    const data = await authApi.login(loginId, password);
    setMember(data);
    return data;
  };

  const signup = (loginId, password) => authApi.signup(loginId, password);

  const logout = async () => {
    await authApi.logout();
    setMember(null);
  };

  return (
    <AuthContext.Provider value={{ member, loading, login, signup, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error("useAuth는 AuthProvider 내부에서만 사용할 수 있습니다.");
  }
  return ctx;
}
