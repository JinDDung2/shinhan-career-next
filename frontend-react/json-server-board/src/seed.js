import bcrypt from "bcryptjs";
import { writeFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import path from "node:path";
import { generateUniqueNickname } from "./nickname.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const DB_PATH = path.join(__dirname, "..", "db", "db.json");

const now = (offsetMinutes = 0) =>
  new Date(Date.now() + offsetMinutes * 60_000).toISOString();

function hash(password) {
  return bcrypt.hashSync(password, 10);
}

const nicknames = [];
function nickname() {
  const n = generateUniqueNickname(nicknames);
  nicknames.push(n);
  return n;
}

// 시드 계정 로그인 정보 (로컬 테스트용)
// samsung1 / Passw0rd!  |  samsung2 / Passw0rd!  |  samsung3 / Passw0rd!
const members = [
  { id: 1, loginId: "samsung1", password: hash("Passw0rd!"), nickname: nickname(), createdAt: now(-300), isDeleted: false, deletedAt: null },
  { id: 2, loginId: "samsung2", password: hash("Passw0rd!"), nickname: nickname(), createdAt: now(-200), isDeleted: false, deletedAt: null },
  { id: 3, loginId: "samsung3", password: hash("Passw0rd!"), nickname: nickname(), createdAt: now(-100), isDeleted: false, deletedAt: null },
];

const boards = [
  {
    id: 1,
    memberId: 1,
    title: "삼성전자 3분기 실적 어떻게 보세요?",
    content: "어제 발표된 잠정 실적 보니 메모리 반등이 확실히 보이네요. 다들 목표가 어느 정도로 잡으시나요?",
    viewCount: 12,
    createdAt: now(-90),
    updatedAt: now(-90),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 2,
    memberId: 2,
    title: "HBM 점유율 관련 뉴스 정리",
    content: "최근 HBM4 관련 로드맵 기사 모아봤습니다. 경쟁사 대비 진행 속도가 궁금하네요.",
    viewCount: 8,
    createdAt: now(-80),
    updatedAt: now(-80),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 3,
    memberId: 3,
    title: "배당 기준일 다가오는데 다들 어떻게 대응하세요",
    content: "이번 분기도 배당 챙기고 갈지, 아니면 다른 종목으로 옮길지 고민 중입니다.",
    viewCount: 5,
    createdAt: now(-60),
    updatedAt: now(-60),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 4,
    memberId: 1,
    title: "외국인 수급 동향 공유",
    content: "최근 5거래일 연속 순매수 중이네요. 수급만 보면 나쁘지 않은 흐름입니다.",
    viewCount: 20,
    createdAt: now(-30),
    updatedAt: now(-30),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 5,
    memberId: 2,
    title: "장기 보유 vs 단기 매매, 여러분의 전략은?",
    content: "저는 3년 이상 보고 모아가는 중인데 다른 분들 전략도 궁금합니다.",
    viewCount: 3,
    createdAt: now(-10),
    updatedAt: now(-10),
    isDeleted: false,
    deletedAt: null,
  },
];

const comments = [
  { id: 1, boardId: 1, memberId: 2, content: "저도 기대하고 있습니다. 목표가 9만원 정도 보고 있어요.", createdAt: now(-85), isDeleted: false, deletedAt: null },
  { id: 2, boardId: 1, memberId: 3, content: "메모리 업사이클 초입이라는 의견이 많더라고요.", createdAt: now(-84), isDeleted: false, deletedAt: null },
  { id: 3, boardId: 2, memberId: 1, content: "좋은 정리 감사합니다!", createdAt: now(-79), isDeleted: false, deletedAt: null },
  { id: 4, boardId: 4, memberId: 3, content: "수급 좋네요, 저도 체크하고 있었습니다.", createdAt: now(-29), isDeleted: false, deletedAt: null },
];

const db = { members, boards, comments };

writeFileSync(DB_PATH, JSON.stringify(db, null, 2) + "\n", "utf-8");

console.log(`시드 데이터 생성 완료: ${DB_PATH}`);
console.log("로그인 테스트 계정: samsung1 / samsung2 / samsung3, 비밀번호 공통 Passw0rd!");
