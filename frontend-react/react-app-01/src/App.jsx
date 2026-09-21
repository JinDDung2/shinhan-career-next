import { useState } from "react";
import heroImg from "./assets/hero.png";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";
import Hello from "./component/Hello";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="App">
        <p className="csstest"> AppCssTest </p>
        <Hello />
      </div>
    </>
  );
}

export default App;
