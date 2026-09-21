import { useRef } from "react";
import { useState } from "react";

export default function Students(props) {
  const list = [];

  // state 선언
  const [studentList, setStudentList] = useState(list);

  const idRef = useRef(null);
  const nameRef = useRef(null);

  function enroll() {
    console.log(nameRef.current.value);
    console.log(idRef.current.value);

    // state 가 reference type 일 때는 객체를 새로 생성해야 함.
    let newList = [...studentList]; //배열 복제

    //새로 등록한 학생 정보를 배열 끝에 추가

    newList.push(
      <li key={idRef.current.value}>
        Welcome! {idRef.current.value}번 {nameRef.current.value}
      </li>,
    );

    // state 값 변경
    setStudentList(newList);
  }

  return (
    <div>
      <ul>{studentList}</ul>

      <div className="Enrollment-Form">
        <input type="text" placeholder="번호입력하세요" ref={idRef} />
        <input type="text" placeholder="이름입력하세요" ref={nameRef} />
        <button onClick={enroll}> 등록 </button>
      </div>
    </div>
  );
}
