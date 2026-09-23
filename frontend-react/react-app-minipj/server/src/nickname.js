import { ADJECTIVES } from "./data/adjectives.js";
import { ANIMALS } from "./data/animals.js";

const adjectives = [...new Set(ADJECTIVES)];
const animals = [...new Set(ANIMALS)];

function randomItem(list) {
  return list[Math.floor(Math.random() * list.length)];
}

/**
 * 형용사 + 동물(곤충) 조합으로 닉네임을 생성한다. existingNicknames와 겹치면 재추첨하고,
 * 조합 풀(형용사 수 x 동물 수)이 거의 소진된 경우에만 숫자 접미사를 붙여 재시도한다.
 */
export function generateUniqueNickname(existingNicknames = []) {
  const existing = new Set(existingNicknames);
  const maxAttempts = 100;

  for (let i = 0; i < maxAttempts; i += 1) {
    const nickname = `${randomItem(adjectives)} ${randomItem(animals)}`;
    if (!existing.has(nickname)) {
      return nickname;
    }
  }

  for (let i = 0; i < maxAttempts; i += 1) {
    const suffix = Math.floor(Math.random() * 9000) + 1000;
    const nickname = `${randomItem(adjectives)} ${randomItem(animals)}${suffix}`;
    if (!existing.has(nickname)) {
      return nickname;
    }
  }

  throw new Error("닉네임 생성에 실패했습니다. 잠시 후 다시 시도해주세요.");
}
