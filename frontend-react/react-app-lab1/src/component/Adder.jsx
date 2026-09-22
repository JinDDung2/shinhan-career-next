import { useState } from "react";

export default function Adder() {
  const [num1, setNum1] = useState(0); // 박스1 입력값
  const [num2, setNum2] = useState(0); // 박스2 입력값
  const [plusSum, setplusSum] = useState(0); // 합이 저장되는 state

  const [num3, setNum3] = useState(0); // 박스3 입력값
  const [num4, setNum4] = useState(0); // 박스4 입력값
  const [minusSum, setminusSum] = useState(0); // 합이 저장되는 state

  function plusHandler() {
    setplusSum(Number(num1) + Number(num2)); // input 값은 문자열이라 Number로 변환
  }

  function minusHandler() {
    setminusSum(Number(num3) - Number(num4)); // input 값은 문자열이라 Number로 변환
  }

  return (
    <div>
      <input
        type="number"
        value={num1}
        onChange={(e) => setNum1(e.target.value)}
      />
      <button onClick={plusHandler}> + </button>
      <input
        type="number"
        value={num2}
        onChange={(e) => setNum2(e.target.value)}
      />
      <span> = {plusSum}</span>
      <br />

      <input
        type="number"
        value={num3}
        onChange={(e) => setNum3(e.target.value)}
      />
      <button onClick={minusHandler}> - </button>
      <input
        type="number"
        value={num4}
        onChange={(e) => setNum4(e.target.value)}
      />
      <span> = {minusSum}</span>
    </div>
  );
}
