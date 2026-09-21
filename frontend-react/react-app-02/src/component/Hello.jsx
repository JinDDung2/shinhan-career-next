import Message from "./Message";

export default function Hello() {
  return (
    <div>
      <h1> Hello</h1>
      <Message name="홍길동" id={1} />
      <Message name="김영자" id={2} />
      <Message />
    </div>
  );
}
