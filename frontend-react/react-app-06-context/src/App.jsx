import { createContext } from "react";
import GrandParent from "./component1/GrandParent";
export const MyContext = createContext();

import "./App.css";

function App() {
  return (
    //MyContext.Provider 하위 컴포넌트는 context의 value를 모두 사용가능
    <MyContext.Provider value="Hello World">
      {" "}
      // 전역적인 저장 공간에 저장
      <GrandParent />
    </MyContext.Provider>
  );
}

export default App;
