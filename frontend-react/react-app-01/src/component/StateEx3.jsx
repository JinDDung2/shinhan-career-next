import { useState } from "react";

export default function StateEx3() {
  const NewOne = {
    name: "김영수",
    age: 30,
  };

  const [user, setUser] = useState(NewOne);

  function clickHandler3_1() {
    NewOne.age = 31;
    setUser(NewOne); // 화면의 값이 갱신되었는가? Yes or No
    // 리엑트는 이전 상태값과, 이후 상태값을 비교해서 다른 경우만 ui 갱신해줌
  }

  function clickHandler3_2() {
    setUser((prevState) => {
      NewOne.age = 31;
      console.log(prevState === NewOne); // true.객체 내부가 바꾸어도 모름.
      return NewOne;
    });
  }

  function clickHandler3_3() {
    setUser((prevState) => {
      NewOne.age = 31;
      const NewNewOne = { ...NewOne, age: 31 }; // 새 객체
      console.log(prevState === NewNewOne); // false
      return NewNewOne;
    });
  }

  return (
    <div>
      {/* {user.name} {user.age}{" "}
      <button onClick={clickHandler3_1}> age 변경 </button> */}
      {user.name} {user.age}{" "}
      <button onClick={clickHandler3_3}> age 변경 </button>
    </div>
  );
}
