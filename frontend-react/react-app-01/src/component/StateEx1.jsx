import { useState } from "react";

export default function StateEx1() {
  const [month, setMonth] = useState("June");

  function clickHandler1() {
    setMonth("April");
    alert(month); // April 이 나오는가? June이 나오는 가? 그 이유는?
    //  June 경고창에 뜸.  setMonth가 비동기로 처리.
  }

  return (
    <div>
      {month} <button onClick={clickHandler1}> month 변경 </button>
    </div>
  );
}
