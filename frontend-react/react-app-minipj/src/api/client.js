const BASE_URL = "/api";

async function request(path, options = {}) {
  const res = await fetch(`${BASE_URL}${path}`, {
    credentials: "include",
    headers: { "Content-Type": "application/json", ...options.headers },
    ...options,
  });

  if (res.status === 204) {
    return null;
  }

  const data = await res.json().catch(() => null);

  if (!res.ok) {
    throw new Error(data?.message || "요청 처리 중 오류가 발생했습니다.");
  }

  return data;
}

export function get(path) {
  return request(path);
}

export function post(path, body) {
  return request(path, { method: "POST", body: JSON.stringify(body) });
}

export function patch(path, body) {
  return request(path, { method: "PATCH", body: JSON.stringify(body) });
}

export function del(path) {
  return request(path, { method: "DELETE" });
}
