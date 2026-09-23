import { readFileSync, writeFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import path from "node:path";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const DB_PATH = path.join(__dirname, "..", "db", "db.json");

export function readDb() {
  return JSON.parse(readFileSync(DB_PATH, "utf-8"));
}

export function writeDb(db) {
  writeFileSync(DB_PATH, JSON.stringify(db, null, 2) + "\n", "utf-8");
}
