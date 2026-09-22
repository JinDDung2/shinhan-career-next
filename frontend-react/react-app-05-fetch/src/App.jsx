import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import StudentList from "./component/StudentList";
import Student from "./component/Student";
import StudentRegister from "./component/StudentRegister";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
          <Route path="/" element={<StudentList />}></Route>
          <Route path="/students/new" element={<StudentRegister />}></Route>
          <Route path="/students/:id" element={<Student />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
