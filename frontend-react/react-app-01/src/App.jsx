import { useState } from "react";
import heroImg from "./assets/hero.png";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";
import Hello from "./component/Hello";
import User from "./component/User";
import StateEx1 from "./component/StateEx1";
import StateEx2 from "./component/StateEx2";
import StateEx3 from "./component/StateEx3";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="App">
        {/* <p className="csstest"> AppCssTest </p>
        <Hello /> */}
        {/* <User /> */}
        {/* <StateEx1 /> */}
        {/* <StateEx2 /> */}
        <StateEx3 />
      </div>
    </>
  );
}

export default App;
