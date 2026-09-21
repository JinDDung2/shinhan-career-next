import { useState } from "react";

export default function Message() {
  const [no, setNo] = useState(3); // 배열 형태 [state 속성명, setter]

  function changeNum() {
    setNo(no + 1);
  }

  function onClickHandler() {
    console.log("버튼이 클릭되었습니다.");
    alert("버튼이 클릭되었습니다.");
  }

  //   function onChangeHandler(event) {
  //     console.log("Onchange 이벤트 발생");
  //     console.log(event.target.value);
  //   }
  return (
    <div>
      <h3> Welcome </h3>
      {/* <input type="text" onChange={onChangeHandler}></input> */}
      <input type="text" />
      안녕하세요?
      <span id="no">{no}</span>기<button onClick={changeNum}> +1 Num </button>
      <br />
      {/* <input
        type="text"
        onChange={(event) => {
          console.log(event.target.value);
        }}
      ></input>
      <button onClick={onClickHandler}>Click</button> */}
    </div>
  );
}
