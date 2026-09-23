import bcrypt from "bcryptjs";
import { writeFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import path from "node:path";
import { generateUniqueNickname } from "./nickname.js";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const DB_PATH = path.join(__dirname, "..", "..", "..", "json-server-board", "db", "db.json");

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
  {
    id: 6,
    memberId: 3,
    title: "파운드리 수주 소식 정리해봤습니다",
    content: "최근 대형 고객사 수주 기사가 몇 개 나왔네요. 내년 실적에 얼마나 반영될지 궁금합니다.",
    viewCount: 9,
    createdAt: now(-5),
    updatedAt: now(-5),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 7,
    memberId: 1,
    title: "52주 신고가 갱신했네요",
    content: "오늘 장중 신고가 찍었는데 거래량도 평소보다 많아서 눈에 띄네요.",
    viewCount: 27,
    createdAt: now(0),
    updatedAt: now(0),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 8,
    memberId: 2,
    title: "환율이 실적에 미치는 영향 질문드려요",
    content: "원달러 환율 변동이 분기 실적에 어느 정도 영향을 주는지 아시는 분 계신가요?",
    viewCount: 6,
    createdAt: now(5),
    updatedAt: now(5),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 9,
    memberId: 3,
    title: "자사주 매입 공시 나왔네요",
    content: "규모가 생각보다 크던데 주가에 어느 정도 호재로 작용할지 다들 어떻게 보시나요.",
    viewCount: 15,
    createdAt: now(10),
    updatedAt: now(10),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 10,
    memberId: 1,
    title: "적립식으로 매수하시는 분 계신가요",
    content: "매달 일정 금액씩 분할매수 중인데, 다른 분들은 어떤 주기로 매수하시는지 궁금합니다.",
    viewCount: 4,
    createdAt: now(15),
    updatedAt: now(15),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 11,
    memberId: 2,
    title: "반도체 업황 사이클 관련 좋은 글 공유",
    content: "이번 사이클이 예년과 다르게 흘러가는 이유를 잘 정리한 글이 있어서 공유합니다.",
    viewCount: 18,
    createdAt: now(20),
    updatedAt: now(20),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 12,
    memberId: 3,
    title: "목표주가 상향 리포트 나왔네요",
    content: "오늘 증권사 몇 곳에서 목표주가 상향 리포트를 냈던데 근거가 다들 비슷하네요.",
    viewCount: 11,
    createdAt: now(25),
    updatedAt: now(25),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 13,
    memberId: 1,
    title: "주총 일정 공유드립니다",
    content: "이번 정기 주주총회 일정이랑 안건 간단히 정리해서 올려요.",
    viewCount: 2,
    createdAt: now(30),
    updatedAt: now(30),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 14,
    memberId: 2,
    title: "신규 라인 투자 발표 어떻게 보세요",
    content: "대규모 설비투자 발표가 났는데 단기적으로는 비용 부담, 장기적으로는 호재 같은데 의견 궁금합니다.",
    viewCount: 7,
    createdAt: now(35),
    updatedAt: now(35),
    isDeleted: false,
    deletedAt: null,
  },
  {
    id: 15,
    memberId: 3,
    title: "오늘 급등 이유 아시는 분",
    content: "특별한 뉴스는 못 찾았는데 장중에 갑자기 거래량 터지면서 올랐네요. 이유 아시는 분 계신가요?",
    viewCount: 1,
    createdAt: now(40),
    updatedAt: now(40),
    isDeleted: false,
    deletedAt: null,
  },
];

// 게시글 수량 확인용으로 16~50번은 주제 목록 + 템플릿 조합으로 생성한다.
const EXTRA_TOPICS = [
  "인적분할 이슈 어떻게 보세요",
  "R&D 투자 규모 정리",
  "노조 임단협 소식",
  "ESG 경영 관련 공시",
  "AI 반도체 수요 전망",
  "메모리 가격 반등 조짐",
  "TSMC와 점유율 비교",
  "미국 반도체법 영향 분석",
  "인력 구조조정 뉴스",
  "차세대 공정 로드맵",
  "주가 조정 구간 매수 타이밍",
  "저PBR 매력 부각",
  "기관 매수세 유입",
  "공매도 잔고 변화",
  "실적 컨센서스 상회 여부",
  "신제품 출시 일정",
  "특허 소송 이슈",
  "환경 규제 대응 현황",
  "해외 공장 증설 소식",
  "신용등급 관련 뉴스",
  "주식 분할 가능성",
  "임원 인사 발표",
  "협력사 공급 계약 체결",
  "채용 규모 확대 소식",
  "지수 편입 비중 변화",
  "베타 지수 관련 질문",
  "밸류업 프로그램 참여",
  "금리 인하가 주가에 미칠 영향",
  "옵션 만기일 변동성 주의",
  "실적 발표 컨퍼런스콜 요약",
  "신규 상장 자회사 이슈",
  "탄소중립 투자 계획",
  "글로벌 반도체 수요 회복세",
  "리스크 요인 점검해봐요",
  "커뮤니티 운영 관련 건의사항",
];

const CONTENT_TEMPLATES = [
  (topic) => `${topic} 관련해서 다들 어떻게 보고 계신가요? 의견 나눠요.`,
  (topic) => `${topic} 얘기가 나와서 정리해봤습니다. 참고하세요.`,
  (topic) => `${topic}에 대해 궁금한 점이 있어서 질문 남겨요.`,
  (topic) => `오늘 ${topic} 관련 뉴스 보고 생각나서 글 남깁니다.`,
];

EXTRA_TOPICS.forEach((topic, idx) => {
  const template = CONTENT_TEMPLATES[idx % CONTENT_TEMPLATES.length];
  boards.push({
    id: 16 + idx,
    memberId: (idx % 3) + 1,
    title: topic,
    content: template(topic),
    viewCount: (idx * 7 + 3) % 40,
    createdAt: now(45 + idx * 3),
    updatedAt: now(45 + idx * 3),
    isDeleted: false,
    deletedAt: null,
  });
});

// 기본 댓글 4개(질문/답변 형태로 직접 작성)에 이어, 일부 게시글에만 댓글이 몰리도록
// 나머지는 계획(commentPlan)에 따라 생성해 "댓글 없는 글/3~4개인 글"이 섞이게 한다.
const comments = [
  { id: 1, boardId: 1, memberId: 2, content: "저도 기대하고 있습니다. 목표가 9만원 정도 보고 있어요.", createdAt: now(-85), isDeleted: false, deletedAt: null },
  { id: 2, boardId: 1, memberId: 3, content: "메모리 업사이클 초입이라는 의견이 많더라고요.", createdAt: now(-84), isDeleted: false, deletedAt: null },
  { id: 3, boardId: 2, memberId: 1, content: "좋은 정리 감사합니다!", createdAt: now(-79), isDeleted: false, deletedAt: null },
  { id: 4, boardId: 4, memberId: 3, content: "수급 좋네요, 저도 체크하고 있었습니다.", createdAt: now(-29), isDeleted: false, deletedAt: null },
];

const REPLY_TEXTS = [
  "좋은 정보 감사합니다.",
  "저도 같은 생각이에요.",
  "흥미로운 관점이네요.",
  "데이터 출처가 궁금해요.",
  "동의합니다, 저도 지켜보고 있어요.",
  "생각해볼 만한 포인트네요.",
  "참고할게요, 감사합니다.",
  "저는 조금 다르게 보고 있어요.",
  "실적 발표 전까지는 지켜봐야 할 것 같아요.",
  "좋은 글 잘 봤습니다.",
];

const commentPlan = [
  { boardId: 1, count: 1 },
  { boardId: 3, count: 2 },
  { boardId: 5, count: 1 },
  { boardId: 7, count: 4 },
  { boardId: 11, count: 2 },
  { boardId: 13, count: 1 },
  { boardId: 16, count: 3 },
  { boardId: 20, count: 1 },
  { boardId: 22, count: 2 },
  { boardId: 25, count: 4 },
  { boardId: 28, count: 1 },
  { boardId: 31, count: 2 },
  { boardId: 35, count: 1 },
  { boardId: 40, count: 1 },
];

let nextCommentId = comments.length + 1;

commentPlan.forEach(({ boardId, count }) => {
  for (let i = 0; i < count; i += 1) {
    comments.push({
      id: nextCommentId,
      boardId,
      memberId: ((boardId + i) % 3) + 1,
      content: REPLY_TEXTS[(boardId + i) % REPLY_TEXTS.length],
      createdAt: now(boardId + i),
      isDeleted: false,
      deletedAt: null,
    });
    nextCommentId += 1;
  }
});

const db = { members, boards, comments };

writeFileSync(DB_PATH, JSON.stringify(db, null, 2) + "\n", "utf-8");

console.log(`시드 데이터 생성 완료: ${DB_PATH}`);
console.log(`게시글 ${boards.length}개, 댓글 ${comments.length}개`);
console.log("로그인 테스트 계정: samsung1 / samsung2 / samsung3, 비밀번호 공통 Passw0rd!");
