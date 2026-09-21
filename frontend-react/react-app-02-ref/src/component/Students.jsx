import { useRef } from "react";

export default function Students() {
  const idRef = useRef(null);
  const nameRef = useRef(null);

  function enroll() {
    console.log(idRef.current.value);
    console.log(nameRef.current.value);
  }

  return (
    <div className="Enrollment-Form">
      <input type="text" placeholder="번호 입력하세요" ref={idRef}></input>
      <br />
      <input type="text" placeholder="이름 입력하세요" ref={nameRef}></input>
      <button onClick={enroll}>등록</button>
    </div>
  );
}
