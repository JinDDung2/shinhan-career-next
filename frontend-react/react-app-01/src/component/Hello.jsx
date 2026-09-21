import Message from "./Message";
// import "./Hello.css";
import style from "./Hello.module.css";

export default function Hello() {
  return (
    <div>
      <h1 className={style.csstest}>Hello</h1>
      <Message />
      <br />
    </div>
  );
}
