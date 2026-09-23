export function nextId(list) {
  return list.reduce((max, item) => Math.max(max, item.id), 0) + 1;
}
