import { useState } from "react";

export default function StateEx2() {
  const [point, setPoint] = useState(0);

  function clickHandler2() {
    setPoint(point + 1);
    setPoint(point + 1);
    setPoint(point + 1);
    setPoint(point + 1); // point 변경 버튼 클릭 시 point가 4값씩 증가되는가?     이유는?
  } // 하나만 처리됨. 비동기라서. 아래와 같이 하면 됨

  function clickHandler3() {
    setPoint((prevState) => prevState + 1); //prevState는 항상 최신 값을 가지고 옴.
    setPoint((prevState) => prevState + 1);
    setPoint((prevState) => prevState + 1);
    setPoint((prevState) => prevState + 1);
  } // point 변경 버튼 클릭 시 point가 4값씩 증가되는가?     이유는?

  return (
    <div>
      {point} <button onClick={clickHandler2}> point 변경 </button>
      {point} <button onClick={clickHandler3}> point 변경 </button>
    </div>
  );
}
