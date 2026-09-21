import { useState } from "react";

export default function User() {
  const [user, setUser] = useState({ id: 1, name: "kim" });

  function test() {
    // user.name = "lee";
    // setUser(user); //user로 하면 화면 갱신 안됨.
    // user 객체의 주소값이 동일하여 변화가 없는 것으로 인식

    // 아래와 같이 수정하면 화면이 갱신됩니다.
    let NewUser = { ...user, name: "lee" }; //새로운 객체 생성!!!
    setUser(NewUser);
  }
  return <button onClick={test}> {user.name} </button>;
}
