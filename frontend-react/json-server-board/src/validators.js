const LOGIN_ID_REGEX = /^[a-z0-9]{6,24}$/;

export function validateLoginId(loginId) {
  if (typeof loginId !== "string" || !LOGIN_ID_REGEX.test(loginId)) {
    return { valid: false, message: "아이디는 영문 소문자와 숫자로 6~24자여야 합니다." };
  }
  return { valid: true };
}

export function validatePassword(password) {
  if (typeof password !== "string" || password.length < 8 || password.length > 24) {
    return { valid: false, message: "비밀번호는 8~24자여야 합니다." };
  }

  const categories = [
    /[A-Z]/.test(password),
    /[a-z]/.test(password),
    /[0-9]/.test(password),
    /[^A-Za-z0-9]/.test(password),
  ];
  const satisfied = categories.filter(Boolean).length;

  if (satisfied < 2) {
    return {
      valid: false,
      message: "비밀번호는 대문자/소문자/숫자/특수문자 중 2가지 이상을 포함해야 합니다.",
    };
  }

  return { valid: true };
}
