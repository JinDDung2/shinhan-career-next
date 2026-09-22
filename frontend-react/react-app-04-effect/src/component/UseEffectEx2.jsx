import React, { useState, useEffect } from "react";

export default function UseEffectEx2() {
  const [count, setCount] = useState(0);
  const [age, setAge] = useState(0);

  useEffect(() => {
    console.log(" component가  처음 mount 됬을 때만 실행 "); // 최초에 딱 한번만 실행
  }, []);

  useEffect(() => {
    console.log("화면이 랜더링될 때마다 실행");
  });

  useEffect(() => {
    console.log("count 가 바뀔 때마다 실행 ");
  }, [count]);

  return (
    <div>
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>add Count</button>
      <p> 현재 나이는 {age} 입니다.</p>
      <button onClick={() => setAge(age + 1)}>add Age</button>
    </div>
  );
}
